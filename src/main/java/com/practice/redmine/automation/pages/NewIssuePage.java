package com.practice.redmine.automation.pages;

import static com.codeborne.selenide.Selenide.$;

public class NewIssuePage extends Page<NewIssuePage> {

    private String projectId = "";

    public NewIssuePage() {
        super(NewIssuePage.class);
    }

    public NewIssuePage setProjectId(String projectId) {
        this.projectId = projectId;
        url = "http://demo.redmine.org/projects/" + this.projectId + "/issues/new";
        return this;
    }

//    public NewIssuePage(String project) {
//        super(NewIssuePage.class);
//        url = "http://demo.redmine.org/projects/" + project + "/issues/new";
//    }

    public void createIssue(String issueSubject, String issueDescription) {
        $("#issue_subject").setValue(issueSubject);
        $("#issue_description").setValue(issueDescription);
        $("input[name=commit]").click();
    }

}