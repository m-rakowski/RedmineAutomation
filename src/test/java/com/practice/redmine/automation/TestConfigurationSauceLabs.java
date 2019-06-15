//package com.practice.redmine.automation;
//
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.WebDriverRunner;
//import com.practice.redmine.automation.utils.Utils;
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import io.qameta.allure.Step;
//import lombok.extern.log4j.Log4j;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map;
//
//@Log4j
//public class TestConfigurationSauceLabs {
//
//
//    String platform, buildName, testCaseName;
//
//    @Before
//    @Step
//    public void beforeEachScenario(Scenario scenario) {
//        Selenide.close();
//        log.debug("Starting scenario " + scenario.getName());
//        browserConfiguration();
//    }
//
//    private static class EnvSetting {
//        public String browser, browserVersion, platform;
//
//        public EnvSetting(String browser, String browserVersion, String platform) {
//            this.browser = browser;
//            this.browserVersion = browserVersion;
//            this.platform = platform;
//        }
//    }
//
//    private static class EnvSettings {
//        private static Map<String, EnvSetting> envSettingsMap;
//
//        static {
//            envSettingsMap = new HashMap<>();
//            envSettingsMap.put("chrome", new EnvSetting("chrome", "72.0", "Windows 10"));
//            envSettingsMap.put("edge", new EnvSetting("edge", "16.16299", "Windows 10"));
//            envSettingsMap.put("firefox", new EnvSetting("firefox", "65.0", "Windows 10"));
//            envSettingsMap.put("internet explorer", new EnvSetting("internet explorer", "11.285", "Windows 10"));
//            envSettingsMap.put("safari", new EnvSetting("safari", "12.0", "macOS 10.14"));
//        }
//
//        public static EnvSetting getForBrowser(String browserName) {
//            return envSettingsMap.get(browserName);
//        }
//    }
//
//    @Step
//    public void browserConfiguration() {
//
//        EnvSetting envSetting = EnvSettings.getForBrowser(System.getProperty("chosen.execution.browser"));
//
////        Configuration.remote = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
//        Configuration.reportsFolder = "target/build";
//        Configuration.timeout = 4000;
//        Configuration.browser = envSetting.browser;
//        Configuration.browserVersion = envSetting.browserVersion;
//        platform = envSetting.platform;
//
//        buildName = "Java Redmine Automated Checks";
//        testCaseName = buildName + "_" + Utils.getTimestamp() + "_" + Configuration.browser + "_" + Configuration.browserVersion;
//
////        Configuration.browserCapabilities = getSauceLabsCredentialsAndEnvSettings();
//
//        includeEnvInfoInReport();
//    }
//
//    private void includeEnvInfoInReport() {
//        String content = "testCaseName=" + testCaseName + "\n" +
//                "Configuration.browser=" + Configuration.browser + "\n" +
//                "Configuration.browserVersion=" + Configuration.browserVersion + "\n" +
//                "platform=" + platform + "\n" +
//                "buildName=" + buildName + "\n" +
//                "Configuration.remote=" + Configuration.remote + "\n";
//
////                "Configuration.timeout=" + Configuration.timeout + "\n" +
////                "Configuration.reportsFolder=" + Configuration.reportsFolder + "\n";
//
//        try {
//            File dir = new File("target\\allure-results");
//            dir.mkdirs();
//            new File(dir, "environment.properties");
//            Files.write(Paths.get("target\\allure-results\\environment.properties"), content.getBytes());
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    private DesiredCapabilities getSauceLabsCredentialsAndEnvSettings() {
//        /**
//         * In this section, we will configure our SauceLabs credentials in order to run our tests on saucelabs.com
//         */
//        String sauceUserName = "michal.rakowski";
//        String sauceAccessKey = "53d016bc-8832-477b-ad9f-23d19f3456c2";
//
//        /**
//         * In this section, we will configure our test to run a specific
//         * browser/os combination in Sauce Labs
//         */
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        //set your user name and access key to run tests in Sauce
//        capabilities.setCapability("username", sauceUserName);
//        //set your sauce labs access key
//        capabilities.setCapability("accessKey", sauceAccessKey);
//
//        capabilities.setCapability("browserName", Configuration.browser);
//        capabilities.setCapability("platform", platform);
//        capabilities.setCapability("version", Configuration.browserVersion);
//        capabilities.setCapability("build", buildName);
//        capabilities.setCapability("name", testCaseName);
//
//        /**
//         * In this section, we will set the WebDriver to a Remote driver to run on sauce, and pass the capabilities
//         * we just set. Then we perform som actions on the page before quitting the driver.
//         */
//        return capabilities;
//    }
//
////    @AfterStep
////    @Step
////    public void screenshotAfterEachStep() {
////        if (WebDriverRunner.hasWebDriverStarted()) {
////            Utils.screenshot();
////        }
////    }
//
//    @After
//    @Step
//    public void afterEachScenario(Scenario scenario) {
//        if ((scenario.isFailed() || !scenario.isFailed()) && WebDriverRunner.hasWebDriverStarted()) {
//            Utils.screenshot();
//        }
//        Selenide.close();
//    }
//}
