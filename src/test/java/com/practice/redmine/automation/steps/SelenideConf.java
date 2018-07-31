package com.practice.redmine.automation.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class SelenideConf {

    @Before
    public void setUp() {

        Configuration.timeout = 2000;
        Configuration.browser = "chrome";
    }

    @After
    public void tearDown() {
        Selenide.close();
    }

}
