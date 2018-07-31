package com.practice.redmine.automation.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.practice.redmine.automation.entities.User;
import com.practice.redmine.automation.pages.AccountPage;
import com.practice.redmine.automation.pages.RegistrationPage;
import com.practice.redmine.automation.utils.UserDataGenerator;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {

    RegistrationPage registrationPage;
    AccountPage accountPage;
    User user;


    @Given("^signup page open$")
    public void signupPageOpen() {
        registrationPage = new RegistrationPage();
        registrationPage.goTo();
    }

    @Given("^a new user$")
    public void newUser() throws Throwable {

        user = UserDataGenerator.getNewUser();
    }

    @Given("^a user which already exists$")
    public void userWhichAlreadyExists() throws Throwable {

        user = UserDataGenerator.getLastUserGenerated();
    }

    @When("^user is being registered$")
    public void userIsBeingRegistered() throws Throwable {

        accountPage = registrationPage.tryRegisteringUser(user);
    }

    @Then("^user will not be registered$")
    public void userWillNotBeRegistered() {

        assertEquals(registrationPage.getUrl(), com.codeborne.selenide.WebDriverRunner.url());
        registrationPage.registrationErrors().shouldHave(size(2));
        registrationPage.registrationErrors().get(0).shouldHave(text("Email has already been taken"));
        registrationPage.registrationErrors().get(1).shouldHave(text("Login has already been taken"));
    }

    @Then("^user registered successfully$")
    public void userRegisteredSuccessfully() {

        assertEquals(accountPage.getUrl(), com.codeborne.selenide.WebDriverRunner.url());
        accountPage.loggedAs().shouldHave(text("Logged in as " + user.username));
        accountPage.message().shouldHave(text("Your account has been activated. You can now log in."));
    }

}
