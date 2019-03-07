package com.practice.redmine.automation.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends Page<LoginPage> {

    public LoginPage() {
        super(LoginPage.class);
        url = "http://demo.redmine.org/login";
    }

    public MainPage tryLoggingInAs(String username, String password) {
        $("#username").setValue(username);
        $("#password").setValue(password);
        $(byName("login")).click();

        return page(MainPage.class);
    }

    public SelenideElement errorMessage() {
//        <div class="flash error" id="flash_error">Invalid user or password</div>
        return $("#flash_error");
    }
}
