package com.practice.redmine.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//C:\Users\Michal\IdeaProjects\RedmineAutomation\
//C:\Users\Michal\IdeaProjects\RedmineAutomation\src\test\resources\com\practice\redmine\automation
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        features = {"src\\test\\resources\\com\\practice\\redmine\\automation\\delete_test_users.feature",
                "src\\test\\resources\\com\\practice\\redmine\\automation\\registration.feature"})
public class DeleteUsersThenRegisterTest {
}