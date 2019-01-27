package com.practice.redmine.automation.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends Page<MainPage> {

    public MainPage() {
        super(MainPage.class);
        url = "http://demo.redmine.org";
    }


    public SelenideElement loggedAs() {
        return $("#loggedas");
    }

    public SelenideElement getNotice() {
        return $("#flash_notice");
    }
}
