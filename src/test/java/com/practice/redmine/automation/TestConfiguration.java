package com.practice.redmine.automation;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.practice.redmine.automation.utils.Utils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

@Log4j
public class TestConfiguration {

    @Before
    @Step
    public void beforeEachScenario(Scenario scenario) {
        log.debug("Starting scenario " + scenario.getName());
        browserConfiguration();

    }

    @Step
    public void browserConfiguration() {

        Configuration.reportsFolder = "target/build";
        Configuration.timeout = 2000;
        Configuration.browser = "chrome";
        Configuration.fastSetValue = true;

        log.info("Configuration.reportsFolder = " + Configuration.reportsFolder);
        log.info("Configuration.timeout = " + Configuration.timeout);
        log.info("Configuration.browser = " + Configuration.browser);
        log.info("Configuration.fastSetValue = " + Configuration.fastSetValue);

    }

//    @AfterStep
//    @Step
//    public void screenshotAfterEachStep() {
//        Utils.screenshot();
//    }

    @After
    @Step
    public void afterEachScenario(Scenario scenario) {
        if ((scenario.isFailed() || !scenario.isFailed()) && WebDriverRunner.hasWebDriverStarted()) {
            Utils.screenshot();
        }
        Selenide.close();
    }
}
