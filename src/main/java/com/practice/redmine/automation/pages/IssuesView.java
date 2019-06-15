package com.practice.redmine.automation.pages;

public class IssuesView extends Page<IssuesView> {
    public IssuesView() {
        super(IssuesView.class);
    }

    public IssuesView setProjectId(String projectId) {
        url = "http://localhost:10083/projects/" + projectId + "/issues";
        return this;
    }
}
