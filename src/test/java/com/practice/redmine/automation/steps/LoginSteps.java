package com.practice.redmine.automation.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.practice.redmine.automation.pages.LoginPage;
import com.practice.redmine.automation.pages.MainPage;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Condition.text;

public class LoginSteps {

    LoginPage loginPage;
    MainPage mainPage;

    String username, password;


    @Given("^login page open$")
    public void loginPageOpen() {

        loginPage = new LoginPage();
        loginPage.goTo();
    }

    @Given("^a user which exists$")
    public void userExists() throws Exception {

        username = "TestUser1";
        password = "TestUser1Password";
    }

    @Given("^incorrect user credentials$")
    public void incorrectUserCredentials() throws Throwable {

        username = "TestUser1";
        password = "wrong password";
    }

    @When("^user logs in$")
    public void userLogsIn() throws Exception {
        mainPage = loginPage.tryLoggingInAs(username, password);
    }

    @Then("^user logged in successfully$")
    public void userLoggedInSuccessfully() throws Exception {
        mainPage.loggedAs().shouldHave(text("Logged in as " + "TestUser1"));
    }


    @Then("^user did not log in$")
    public void loginFailed() throws Throwable {
        loginPage.errorMessage().shouldHave(text("Invalid user or password"));
    }

}
