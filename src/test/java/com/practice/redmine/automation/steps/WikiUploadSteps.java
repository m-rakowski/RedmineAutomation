package com.practice.redmine.automation.steps;


import com.practice.redmine.automation.pages.ProjectWikiFilesPage;
import com.practice.redmine.automation.pages.ProjectWikiPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Condition.text;

public class WikiUploadSteps {

    @Given("^edit wiki page is open$")
    public void privateProjectWikiPageIsOpen() {
        new ProjectWikiPage()
                .setProjectId("id_myveryprivateproject")
                .goTo()
                .shouldBeLoaded();
    }

    @When("^I try to upload a file$")
    public void iTryToUploadAFile() {
        new ProjectWikiPage()
                .uploadFile("C:\\Users\\Admin\\Downloads\\picture.jpg");
    }

    @Then("^the file gets updated$")
    public void theFileGetsUpdated() {

        new ProjectWikiFilesPage()
                .setProjectId("id_myveryprivateproject")
                .goTo()
                .expandFilesList()
                .getFirstAttachment().shouldHave(text("picture.jpg"));
    }
}
