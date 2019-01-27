package com.practice.redmine.automation.pages;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class NewProjectPage extends Page<NewProjectPage> {

    public NewProjectPage() {
        super(NewProjectPage.class);
        url = "http://demo.redmine.org/projects/new";
    }

    public ProjectSettingsPage createProject(String projectName, String projectDescription, String projectId) {
        $("#project_name").setValue(projectName);
        $("#project_description").setValue(projectDescription);
        $("#project_identifier").setValue(projectId);
        $(byName("commit")).click();

        //<input size="60" type="text" value="" name="project[name]" id="project_name">
        //<textarea rows="8" class="wiki-edit" name="project[description]" id="project_description"></textarea>
        //<input size="60" maxlength="100" type="text" name="project[identifier]" id="project_identifier">
        //<input size="60" type="text" value="" name="project[homepage]" id="project_homepage">
        //<input type="submit" name="commit" value="Create">

        return page(ProjectSettingsPage.class);
    }


}
