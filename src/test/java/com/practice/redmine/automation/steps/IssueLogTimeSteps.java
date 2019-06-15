package com.practice.redmine.automation.steps;

import com.codeborne.selenide.Selenide;
import com.practice.redmine.automation.pages.LoginPage;
import com.practice.redmine.automation.pages.NewIssuePage;
import com.practice.redmine.automation.pages.TimeLogPage;
import com.practice.redmine.automation.pages.ViewIssuePage;
import com.practice.redmine.automation.utils.Utils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class IssueLogTimeSteps {

    Long time = System.currentTimeMillis();
    String existingProjectId;
    String issue = "issue" + time;
    String issueId;


    @Given("I am logged in as \"(.*)\" with password \"(.*)\"")
    public void iAmLoggedInAsWithPassword(String username, String password) {
        new LoginPage().goTo().tryLoggingInAs(username, password);
    }

    @Given("project exists")
    public void projectExists() {

        this.existingProjectId = "id_myveryprivateproject";
    }

    @Given("issue exists")
    public void issueExists() {
        new NewIssuePage().setProjectId(existingProjectId)
                .goTo()
                .createIssue(issue, issue);
        $("#flash_notice a").shouldHave(text("#"));
        issueId = $("#flash_notice a").text().replace("#", "");

    }

    @When("I log {int} hours of time under issue")
    public void logTime(Integer hours) {

        TimeLogPage timeLogPage = new ViewIssuePage()
                .logTimePage();

        timeLogPage.log(hours, 0);
    }

    @Then("time is logged")
    public void timeLogged() {

        ViewIssuePage viewIssuePage = new ViewIssuePage().setIssueId(issueId);
        viewIssuePage.goTo();

//        Selenide.sleep(1000);
//        Selenide.refresh();
//        Selenide.sleep(1000);

        viewIssuePage.timeSpent().shouldHave(text("1.00 h"));
    }

}
