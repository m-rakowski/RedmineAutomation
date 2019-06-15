package com.practice.redmine.automation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ViewIssuePage extends Page<ViewIssuePage> {

    public ViewIssuePage() {
        super(ViewIssuePage.class);
    }

    public ViewIssuePage setIssueId(String issueId) {
        url = "http://localhost:10083/issues/" + issueId;
        return this;
    }

    public TimeLogPage logTimePage() {
        $("a.icon.icon-time-add").click();
        return new TimeLogPage();
    }

    public SelenideElement timeSpent() {
        return $("div.spent-time.attribute div.value");
    }
}
