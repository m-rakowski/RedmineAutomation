package com.practice.redmine.automation.steps;


import com.codeborne.selenide.Selenide;
import com.practice.redmine.automation.pages.AdminProjectListPage;
import com.practice.redmine.automation.pages.LoginPage;
import com.practice.redmine.automation.pages.NewProjectPage;
import com.practice.redmine.automation.utils.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PrivateProjectSteps {

    Long time = System.currentTimeMillis();

    String projectName = "PrivateProjectTest";
    String projectDescription = "description " + time;
    String projectId = "id_" + time;

    @Given("^I am logged in$")
    public void IamloggedIn() {
        new LoginPage().goTo().tryLoggingInAs("UserWhichExists", "Password");
    }

    @When("^I create a private project$")
    public void createAPrivateProject() {

        new NewProjectPage()
                .goTo()
                .createProject(
                        projectName,
                        projectDescription,
                        projectId,
                        false);

        TestUtils.logout();
    }

    @Then("^people who don't have access to my project won't be able to see it$")
    public void noAccessCantSee() {

        new LoginPage()
                .goTo()
                .tryLoggingInAs("OtherUser", "Password");

        Selenide.open("http://localhost:10083/projects/" + "id_" + time);

        //<p id="errorExplanation">You are not authorized to access this page.</p>
        $("#errorExplanation").shouldHave(text("You are not authorized to access this page."));
    }

    @When("I create a private project with name {string}")
    public void iCreateAPrivateProjectWithName(String projectName) {
        new NewProjectPage()
                .goTo()
                .createProject(
                        projectName,
                        projectName,
                        "id_" + projectName,
                        false);
//        TestUtils.logout();
    }

    @When("I delete the private project")
    public void iDeleteThePrivateProject() {
        new AdminProjectListPage()
                .goTo()
                .searchForProject(projectName);

        new AdminProjectListPage().deleteProject(projectName);
    }

    @Then("it is no longer displayed on the list of projects")
    public void itIsNoLongerDisplayedOnTheListOfProject() {
        new AdminProjectListPage()
                .goTo()
                .searchForProject(projectName);

        $("p.nodata").shouldHave(text("No data to display"));
    }
}
