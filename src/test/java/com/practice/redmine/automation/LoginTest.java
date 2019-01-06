package com.practice.redmine.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//C:\Users\Michal\IdeaProjects\RedmineAutomation\
//C:\Users\Michal\IdeaProjects\RedmineAutomation\src\test\resources\com\practice\redmine\automation
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src\\test\\resources\\com\\practice\\redmine\\automation\\login.feature"},
        plugin = {"pretty", "io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm", "json:target/cucumber-report/report.json"}
)
public class LoginTest {
}