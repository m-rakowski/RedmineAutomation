package com.practice.redmine.automation.pages;

public class ProjectsPage extends Page<ProjectsPage> {

    public ProjectsPage() {
        super(ProjectsPage.class);
        url = "http://localhost:10083/projects";
    }


}
