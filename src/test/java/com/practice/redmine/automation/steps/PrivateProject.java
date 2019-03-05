package com.practice.redmine.automation.steps;


import com.codeborne.selenide.Selenide;
import com.practice.redmine.automation.pages.LoginPage;
import com.practice.redmine.automation.pages.NewProjectPage;
import com.practice.redmine.automation.utils.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PrivateProject {

    Long time = System.currentTimeMillis();


    @Given("^I am logged in$")
    public void IamloggedIn() {
        new LoginPage().goTo().tryLoggingInAs("UserWhichExists", "Password");
    }

    @When("^I create a private project$")
    public void createAPrivateProject() {
        new NewProjectPage()
                .goTo()
                .createProject(
                        "PrivateProject" + time,
                        "description " + time,
                        "id_" + time.toString(),
                        false);
        TestUtils.logout();
    }

    @Then("^people who don't have access to my project won't be able to see it$")
    public void noAccessCantSee() {

        new LoginPage()
                .goTo()
                .tryLoggingInAs("OtherUser", "Password");

        Selenide.open("http://demo.redmine.org/projects/" + "id_" + time);

        //<p id="errorExplanation">You are not authorized to access this page.</p>
        $("#errorExplanation").shouldHave(text("You are not authorized to access this page."));
    }
}
