package com.practice.redmine.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"not @test_setup"},
//        tags = {"@projects"},
//        tags = {"@private_project"},
//        tags = {"@wiki"},

        plugin = {"pretty",
                "io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm",
                "json:target/cucumber-report/report.json"}
)
public class RunAllTestsTest {
}