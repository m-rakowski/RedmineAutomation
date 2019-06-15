package com.practice.redmine.automation.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AccountPage extends Page<AccountPage> {

    public AccountPage() {
        super(AccountPage.class);
        url = "http://localhost:10083/my/account";
    }


    public SelenideElement loggedAs() {
        return $("#loggedas");
    }

    public SelenideElement message() {
        return $("#flash_notice");
    }

    public AccountDeletionPage clickDeleteMyAccount() {
        $(byText("Delete my account")).click();
        return page(AccountDeletionPage.class);
    }

}
