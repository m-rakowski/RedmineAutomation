package com.practice.redmine.automation.steps;


import com.codeborne.selenide.Selenide;
import com.practice.redmine.automation.pages.*;
import com.practice.redmine.automation.utils.RandomStringGenerator;
import com.practice.redmine.automation.utils.Utils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectSteps {

    LoginPage loginPage;
    NewProjectPage newProjectPage;
    ProjectSettingsPage projectSettingsPage;

    String projectName;
    String projectDescription;
    String projectId;

    @Given("^user logged in$")
    public void loggedInUser() {

        loginPage = new LoginPage();
        loginPage.goTo();
        loginPage.tryLoggingInAs("UserWhichExists", "Password");

        newProjectPage = new NewProjectPage();
        newProjectPage.goTo();
    }


    @Given("^a project which does not yet exist$")
    public void projectWhichDoesNotYetExist() {

        String randomString = RandomStringGenerator.randomAlphaNumeric(10);

        projectName = "ProjectName" + randomString;
        projectDescription = "ProjectDescription" + randomString;
        projectId = "projectname" + randomString;
    }

    @When("^user tries to create the project$")
    public void userTriesToCreateTheProject() {

        projectSettingsPage = new NewProjectPage().createProject(projectName, projectDescription, projectId, true);
    }


    @Then("^project gets created successfully$")
    public void projectGetsCreatedSuccessfully() {

        projectSettingsPage.message().shouldHave(text("Successful creation."));
    }

    @And("a project which exists")
    public void aProjectWhichExists() {
        projectId = "projectname4dfa3mx6t2";
    }

    @When("closing the project")
    public void closesTheProject() {
        new ProjectOverviewPage()
                .setProjectId(projectId)
                .goTo();
//        Utils.screenshot();
        new ProjectOverviewPage().clickClose();
        Selenide.confirm();

    }

    @Then("the project becomes read-only")
    public void theProjectBecomesReadOnly() {

        new NewIssuePage().setProjectId(projectId).goTo();

        Utils.waitAndRefreshUntilPresent(10, $("#errorExplanation"));

        $("#errorExplanation").shouldHave(text("You are not authorized to access this page."));
        //<p id="errorExplanation">You are not authorized to access this page.</p>
    }

    @When("reopens the project")
    public void reopensTheProject() {
        new ProjectOverviewPage()
                .setProjectId(projectId)
                .goTo();
//        Utils.screenshot();
        new ProjectOverviewPage().clickReopen();
        Selenide.confirm();
    }

    @Then("new issues can be added to the project")
    public void newIssuesCanBeAddedToTheProject() {

        new NewIssuePage().setProjectId(projectId).goTo();

        Utils.waitAndRefreshUntilPresent(10, $("#issue_subject"));

        $("#errorExplanation").shouldNotBe(visible);
    }

    @And("a project with id (.*) which exists")
    public void aProjectWithIdId_WhichExists(String projectId) {
        this.projectId = projectId;
    }

    @When("creating a private project with name \"(.*)\" if it does not exists yet")
    public void creatingAPrivateProjectWithNameIfItDoesNotExistsYet(String projectName) {

        projectId = "id_" + projectName;

        new NewProjectPage()
                .goTo()
                .createProject(projectName, projectDescription, projectId, false);
    }
}
