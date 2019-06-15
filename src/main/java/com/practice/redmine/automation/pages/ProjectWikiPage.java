package com.practice.redmine.automation.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class ProjectWikiPage extends Page<ProjectWikiPage> {

    private String projectId = "ProjectIdWasNotSpecified";

    public ProjectWikiPage() {
        super(ProjectWikiPage.class);
        url = "http://localhost:10083/projects/" + projectId + "/wiki/Wiki/edit";
    }

    public ProjectWikiPage setProjectId(String projectId) {
        this.projectId = projectId;
        url = "http://localhost:10083/projects/" + projectId + "/wiki/Wiki/edit";
        return this;
    }

    public void shouldBeLoaded() {
        $("h2").shouldHave(Condition.text("Wiki"));
    }

    public void uploadFile(String path) {
        $("input[type=file]").sendKeys(path);
        $(".icon-del").shouldBe(Condition.visible);
        $("input[type=submit]").click();
    }
}