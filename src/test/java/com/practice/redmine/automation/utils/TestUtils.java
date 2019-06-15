package com.practice.redmine.automation.utils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class TestUtils {

    public static void logout() {
        if ($("a.logout").exists()) {
            $("a.logout").click();
        }
    }
}
