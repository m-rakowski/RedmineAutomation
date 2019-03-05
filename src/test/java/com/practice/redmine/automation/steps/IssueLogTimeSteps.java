package com.practice.redmine.automation.steps;

import com.practice.redmine.automation.pages.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class IssueLogTimeSteps {

    Long time = System.currentTimeMillis();
    String existingProject;
    String issue = "issue" + time;
    String issueId;

    @Given("I am logged in as \"(.*)\" \"(.*)\"")
    public void loggedIn(String username, String password) {
        new LoginPage().goTo().tryLoggingInAs(username, password);
    }

    @Given("project exists")
    public void projectExists() {
        new NewProjectPage()
                .goTo()
                .createProject(
                        "PrivateProject" + time,
                        "description " + time,
                        "id_" + time.toString(),
                        false);

        this.existingProject = "id_" + time.toString();

    }

    @Given("issue exists")
    public void issueExists() {
        new NewIssuePage().setProjectId(existingProject)
                .goTo()
                .createIssue(issue, issue);
        $("#flash_notice a").shouldHave(text("#"));
        issueId = $("#flash_notice a").text().replace("#","");

    }

    @When("I log {int} hours of time under issue")
    public void logTime(Integer hours) {

        TimeLogPage timeLogPage = new ViewIssuePage()
                .logTimePage();

        timeLogPage.log(hours, 1);
    }

    @Then("time is logged")
    public void timeLogged() {
        new ViewIssuePage()
                .setIssueId(issueId)
                .goTo()
                .timeSpent().shouldHave(text("1.00 hour"));
    }
}
