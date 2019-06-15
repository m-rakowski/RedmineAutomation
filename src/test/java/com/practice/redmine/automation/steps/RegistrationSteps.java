package com.practice.redmine.automation.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.practice.redmine.automation.entities.User;
import com.practice.redmine.automation.pages.AccountPage;
import com.practice.redmine.automation.pages.RegistrationPage;
import com.practice.redmine.automation.utils.TestUtils;
import com.practice.redmine.automation.utils.UserDataGenerator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static org.junit.Assert.assertEquals;

public class RegistrationSteps {

    RegistrationPage registrationPage;
    AccountPage accountPage;
    User user;

    @Given("a new user \"([^\"]*)\" with password \"([^\"]*)\"")
    public void aNewUserWithPassword(String username, String password) {
        user = new User(
                username,
                password,
                "FirstName",
                "LastName",
                "email" + new Random().nextInt(10) + "@email.com",
                "English");
    }

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

        user = new User(
                "UserWhichExists",
                "Password",
                "UserWhichExists",
                "User",
                "user@email.com",
                "English");
    }

    @When("^user is being registered$")
    public void userIsBeingRegistered() throws Throwable {

        accountPage = registrationPage.tryRegisteringUser(user);
    }

    @Then("^user will not be registered$")
    public void userWillNotBeRegistered() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), Configuration.timeout / 1000).until(ExpectedConditions.urlToBe(registrationPage.getUrl()));

        registrationPage.registrationErrors().get(0).shouldHave(text("Login has already been taken"));
    }

    @Then("^user registered successfully$")
    public void userRegisteredSuccessfully() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), Configuration.timeout / 1000).until(ExpectedConditions.urlToBe(accountPage.getUrl()));

        accountPage.loggedAs().shouldHave(text("Logged in as " + user.username));
        accountPage.message().shouldHave(text("Your account has been activated. You can now log in."));
    }

    @Then("logout")
    public void logout() {
        TestUtils.logout();
    }
}
