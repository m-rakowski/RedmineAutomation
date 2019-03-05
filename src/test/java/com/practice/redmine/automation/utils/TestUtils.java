package com.practice.redmine.automation.utils;

import static com.codeborne.selenide.Selenide.$;

public class TestUtils {

    public static void logout() {
        $("a.logout").click();

    }
}
