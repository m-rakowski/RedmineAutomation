package com.practice.redmine.automation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProjectWikiFilesPage extends Page<ProjectWikiFilesPage> {

    private String projectId = "ProjectIdWasNotSpecified";

    public ProjectWikiFilesPage() {
        super(ProjectWikiFilesPage.class);
        url = "http://localhost:10083/projects/" + projectId + "/wiki/Wiki";
    }

    public ProjectWikiFilesPage setProjectId(String projectId) {
        this.projectId = projectId;
        url = "http://localhost:10083/projects/" + projectId + "/wiki/Wiki";
        return this;
    }


    public ProjectWikiFilesPage expandFilesList() {
        $("legend").click();
        return this;
    }

    public SelenideElement getFirstAttachment() {
        return $("a.icon.icon-attachment");
    }
}
