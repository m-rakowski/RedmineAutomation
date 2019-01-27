package com.practice.redmine.automation.pages;

import static com.codeborne.selenide.Selenide.open;

public class Page<T> {

    protected String url;
    private Class<T> pageType;

    public Page(Class<T> pageType) {
        this.pageType = pageType;
    }

    public T goTo() {
        return open(url, pageType);
    }

    public String getUrl() {
        return url;
    }

}
