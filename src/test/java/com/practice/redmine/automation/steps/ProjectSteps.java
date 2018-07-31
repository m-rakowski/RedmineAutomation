package com.practice.redmine.automation.steps;


import com.practice.redmine.automation.pages.LoginPage;
import com.practice.redmine.automation.pages.NewProjectPage;
import com.practice.redmine.automation.pages.ProjectSettingsPage;
import com.practice.redmine.automation.utils.RandomStringGenerator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Condition.text;

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
        loginPage.tryLoggingInAs("TestUser1", "TestUser1Password");

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

        projectSettingsPage = newProjectPage.createProject(projectName, projectDescription, projectId);
    }


    @Then("^project gets created successfully$")
    public void projectGetsCreatedSuccesfully() {

        projectSettingsPage.message().shouldHave(text("Successful creation."));
        projectSettingsPage.projectDropDown().getSelectedOption().shouldHave(text(projectName));
    }

}
