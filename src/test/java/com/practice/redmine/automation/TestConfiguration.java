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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j
public class TestConfiguration {

    @Before
    @Step
    public void beforeEachScenario(Scenario scenario) {
        Selenide.close();
        log.debug("Starting scenario " + scenario.getName());
        browserConfiguration();
    }

    @Step
    public void browserConfiguration() {

        Configuration.reportsFolder = "target/build";
        Configuration.timeout = 6000;
        Configuration.browser = System.getProperty("chosen.execution.browser");

        Configuration.fastSetValue = false;
        Configuration.startMaximized = true;

        if (Configuration.browser.toLowerCase().equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\Admin\\lab\\apps\\RedmineAutomation\\chromedriver.exe");
        }


        if (Configuration.browser.toLowerCase().equals("ie")) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
            Configuration.browserCapabilities = capabilities;
        }

        includeEnvInfoInReport();
    }

    private void includeEnvInfoInReport() {
        String content =
                "browser=" + Configuration.browser + "\n" +
                        "language=" + "Java" + "\n" +
                        "timeout=" + Configuration.timeout;
        try {
            File dir = new File("target\\allure-results");
            dir.mkdirs();
            new File(dir, "environment.properties");
            Files.write(Paths.get("target\\allure-results\\environment.properties"), content.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

//    @AfterStep
//    @Step
//    public void screenshotAfterEachStep() {
//        if (WebDriverRunner.hasWebDriverStarted()) {
//            Utils.screenshot();
//        }
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
