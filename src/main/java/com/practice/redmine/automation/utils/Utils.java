package com.practice.redmine.automation.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j
public class Utils {

    public static String getTimestamp() {

        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy__HH-mm-ss");
        return europeanDateFormatter.format(LocalDateTime.now());
    }

    public static void screenshot() {

        String screenshotName = getTimestamp();
        String pathToScreenshot = Selenide.screenshot(screenshotName);

        try {
            Path content = Paths.get(pathToScreenshot);
            InputStream is = Files.newInputStream(content);
            Allure.addAttachment(screenshotName, is);
        } catch (IOException e) {
            log.error("Could not take screenshot\n" + e.getMessage());
        }
    }

    public static void waitAndRefreshUntilPresent(int iterationsTimeOut, SelenideElement selenideElement) {

        int i = 0;
        for (; i < iterationsTimeOut; i++) {
            if (selenideElement.exists()) {
                break;
            } else {
                Selenide.sleep(200);
                Selenide.refresh();
            }
        }
        log.debug("i was " +i);
    }
}
