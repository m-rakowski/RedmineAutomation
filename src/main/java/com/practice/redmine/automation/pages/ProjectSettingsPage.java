package com.practice.redmine.automation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProjectSettingsPage extends Page<ProjectSettingsPage> {

    public ProjectSettingsPage() {
        super(ProjectSettingsPage.class);
    }


    public SelenideElement projectDropDown() {
        return $("#project_quick_jump_box");
    }

    public SelenideElement message() {
        return $("#flash_notice");
    }

}
