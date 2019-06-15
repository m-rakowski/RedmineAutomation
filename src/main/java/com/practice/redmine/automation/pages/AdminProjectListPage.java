package com.practice.redmine.automation.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class AdminProjectListPage extends Page<AdminProjectListPage> {

    public AdminProjectListPage() {
        super(AdminProjectListPage.class);
        url = "http://localhost:10083/admin/projects";
    }

    public void searchForProject(String projectName) {
        $("#name").setValue(projectName);
        $("input[type=submit]").click();
    }

    public void deleteProject(String projectName) {
        $(By.xpath("//a[text()[contains(., '" + projectName + "')]]/../../..//a[contains(@class, 'icon-del')]")).click();
        $("#confirm").setSelected(true);
        $(byName("commit")).click();
    }
}
