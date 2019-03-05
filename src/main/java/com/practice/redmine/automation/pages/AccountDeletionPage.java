package com.practice.redmine.automation.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AccountDeletionPage extends Page<AccountDeletionPage> {

    public SelenideElement yesCheckbox = $("#confirm");
    public SelenideElement deleteButton = $("button[name='commit']");

    public AccountDeletionPage() {
        super(AccountDeletionPage.class);
        url = "http://demo.redmine.org/my/account/destroy";
    }


    public void delete() {
        yesCheckbox.setSelected(true);
        deleteButton.click();
        $(byText("Your account has been permanently deleted.")).shouldBe(Condition.visible);
    }
}
