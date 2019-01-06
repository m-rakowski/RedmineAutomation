package com.practice.redmine.automation.steps;

import com.practice.redmine.automation.pages.LoginPage;
import com.practice.redmine.automation.pages.MainPage;
import com.practice.redmine.automation.utils.UserDataGenerator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeFalse;

public class DeleteTestUsersSteps {


    LoginPage loginPage;
    MainPage mainPage;

    @Given("^a login page open$")
    public void loginPageOpen() {

        loginPage = new LoginPage();
        loginPage.goTo();
    }

    @Given("^logged in as \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void loggedInAsUsernameWithPasswordPassword(String username, String password) {

        mainPage = loginPage.tryLoggingInAs(username, password);

        //if user had been created before, open their account page; else just move on to the next user
        assumeFalse(loginPage.errorMessage().exists() && "Invalid user or password".equals(loginPage.errorMessage().text()));
    }

    @When("^deleting account$")
    public void deletingAccount() {
        open("http://demo.redmine.org/my/account/destroy");
        $("#confirm").setSelected(true);
        $(byName("commit")).click();
    }

    @Then("^the account is deleted$")
    public void theAccountIsDeleted() {
        mainPage.getNotice().shouldHave(text("Your account has been permanently deleted."));
    }


    @When("^user counter generator is reset$")
    public void reset() throws IOException {
        UserDataGenerator.resetCounter();
    }

    @Then("^user counter file contains the number (\\d+)$")
    public void counterIs(String count) throws IOException {
        assertEquals(count, UserDataGenerator.readLastCountFromFile());
    }

}
