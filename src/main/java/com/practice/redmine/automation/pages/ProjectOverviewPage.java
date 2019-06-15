package com.practice.redmine.automation.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class ProjectOverviewPage extends Page<ProjectOverviewPage> {
    public ProjectOverviewPage() {
        super(ProjectOverviewPage.class);

    }

    public ProjectOverviewPage setProjectId(String projectId) {
        url = "http://localhost:10083/projects/" + projectId;
        return this;
    }

    public void clickClose() {
        $("a.icon-lock").click();
    }

    public void clickReopen() {
        $("a.icon-unlock").click();
    }
}
