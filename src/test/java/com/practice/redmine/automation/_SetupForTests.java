package com.practice.redmine.automation;

import com.codeborne.selenide.Selenide;
import com.practice.redmine.automation.pages.RegistrationPage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.util.Random;

@Log4j
public class _SetupForTests {

    @Test
    @Step
    public void registerOtherUser() {
        log.info("Running setup");
        new RegistrationPage()
                .goTo()
                .tryRegisteringUser(
                        "OtherUser",
                        "Password",
                        "Other",
                        "User",
                        "user@email" + new Random().nextInt(1000000) + ".com",
                        "English");

        Selenide.close();
    }

    @Test
    @Step
    public void registerUserWhichExists() {
        log.info("Running setup");
        new RegistrationPage()
                .goTo()
                .tryRegisteringUser(
                        "UserWhichExists",
                        "Password",
                        "UserWhichExists",
                        "User",
                        "user@email" + new Random().nextInt(1000000) + ".com",
                        "English");
        Selenide.close();
    }
}
