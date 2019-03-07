package com.practice.redmine.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src\\test\\resources\\com\\practice\\redmine\\automation\\delete_user.feature"},
        plugin = {"pretty",
                "io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm",
                "json:target/cucumber-report/report.json"}
)
public class DeleteUserTest {
}