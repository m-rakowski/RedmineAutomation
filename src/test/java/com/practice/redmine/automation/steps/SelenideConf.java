package com.practice.redmine.automation.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.practice.redmine.automation.utils.Utils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.extern.log4j.Log4j;

@Log4j
public class SelenideConf {

    @Before
    public void setUp() {

        Configuration.reportsFolder = "target/build";
        Configuration.timeout = 2000;
        Configuration.browser = "chrome";
    }

    @After
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Utils.screenshot();
        }
        Selenide.close();
    }

}
