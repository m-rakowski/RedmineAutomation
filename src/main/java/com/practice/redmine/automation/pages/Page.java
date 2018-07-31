package com.practice.redmine.automation.pages;

import static com.codeborne.selenide.Selenide.open;

public class Page {

    protected String url;

    public void goTo() {
        open(url, this.getClass());
    }

    public String getUrl() {
        return url;
    }

}
