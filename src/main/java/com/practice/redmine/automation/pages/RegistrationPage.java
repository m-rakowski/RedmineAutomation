package com.practice.redmine.automation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.practice.redmine.automation.entities.User;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPage extends Page<RegistrationPage> {

    public RegistrationPage() {
        super(RegistrationPage.class);
        url = "http://demo.redmine.org/account/register";
    }

    public void tryRegisteringUser(String userLogin, String userPassword, String firstName, String lastName, String email, String language) {

        $("#user_login").sendKeys(userLogin);
        $("#user_password").sendKeys(userPassword);
        $("#user_password_confirmation").sendKeys(userPassword);
        $("#user_firstname").sendKeys(firstName);
        $("#user_lastname").sendKeys(lastName);
        $("#user_mail").sendKeys(email);
        $("#user_language").selectOption(language);

        $(byName("commit")).click();
    }


    public ElementsCollection registrationErrors() {
        return $$("#errorExplanation li");
    }

    public AccountPage tryRegisteringUser(User user) {
        tryRegisteringUser(user.username, user.password, user.firstName, user.lastName, user.email, user.language);

        return page(AccountPage.class);
    }
}
