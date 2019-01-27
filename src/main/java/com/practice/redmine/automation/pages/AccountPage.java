package com.practice.redmine.automation.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AccountPage extends Page<AccountPage> {

    public AccountPage() {
        super(AccountPage.class);
        url = "http://demo.redmine.org/my/account";
    }


    public SelenideElement loggedAs() {
        return $("#loggedas");
    }

    public SelenideElement message() {
        return $("#flash_notice");
    }
}
