package com.practice.redmine.automation.pages;

public class IssuesView extends Page<IssuesView> {
    public IssuesView() {
        super(IssuesView.class);
    }

    public IssuesView setProjectId(String projectId) {
        url = "http://demo.redmine.org/projects/" + projectId + "/issues";
        return this;
    }
}
