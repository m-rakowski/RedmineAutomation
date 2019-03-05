package com.practice.redmine.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src\\test\\resources\\com\\practice\\redmine\\automation\\log_time.feature"},
        plugin = {"pretty",
                "io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm",
                "json:target/cucumber-report/report.json"}
)
public class LogTimeTest {
}