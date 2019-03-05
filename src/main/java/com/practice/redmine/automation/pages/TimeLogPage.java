package com.practice.redmine.automation.pages;

import static com.codeborne.selenide.Selenide.$;

public class TimeLogPage {

    public void log(Integer hours, Integer activity) {
        $("#time_entry_hours").setValue(hours.toString());
        $("#time_entry_activity_id").selectOption(activity);
        $("input[name=commit]").click();
    }
}
