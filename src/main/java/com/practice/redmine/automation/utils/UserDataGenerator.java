package com.practice.redmine.automation.utils;

import com.practice.redmine.automation.entities.User;
import com.practice.redmine.automation.pages.LoginPage;

import java.io.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.Integer.parseInt;


public class UserDataGenerator {


    private static String counterFileName = "user_counter.txt";

    public static User getNewUser() throws IOException {

        UserDataGenerator.incrementCounter();

        String username = "TestUser" + UserDataGenerator.readLastCountFromFile();

        return new User(
                username,
                username + "Password",
                username + "FirstName",
                username + "LastName",
                username.toLowerCase() + "@test.com",
                "English (British)");
    }

    public static void incrementCounter() throws IOException {
        String currentCount = UserDataGenerator.readLastCountFromFile();

        BufferedWriter bw = new BufferedWriter(new FileWriter(counterFileName));
        String content = Integer.valueOf(parseInt(currentCount) + 1).toString();

        bw.write(content);
        bw.close();

        // no need to close it.
        //bw.close();
    }

    public static String readLastCountFromFile()
            throws java.io.IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(counterFileName));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

    public static User getLastUserGenerated() throws IOException {

        String lastCount = UserDataGenerator.readLastCountFromFile();

        //String count = Integer.valueOf(Integer.parseInt(lastCount) - 1).toString();
        String username = "TestUser" + lastCount;


        return new User(
                username,
                username + "Password",
                username + "FirstName",
                username + "LastName",
                username.toLowerCase() + "@test.com",
                "English");
    }

    public static void deleteUsersFromSystem() {

        LoginPage loginPage = new LoginPage();

        for (int i = 2; i <= 100; i++) {
            loginPage.goTo();
            loginPage.tryLoggingInAs("TestUser" + i, "TestUser" + i + "Password");
            //<div class="flash error" id="flash_error">Invalid user or password</div>

            if ($("#flash_error").exists() && "Invalid user or password".equals($("#flash_error").text())) {
                continue;
            }


            open("http://demo.redmine.org/my/account/destroy");
            $("#confirm").setSelected(true);
            //<input type="submit" name="commit" value="Delete my account">
            $(byName("commit")).click();

            //<div class="flash notice" id="flash_notice">Your account has been permanently deleted.</div>

            $("#flash_notice").shouldHave(text("Your account has been permanently deleted."));


        }
    }
    public static void resetCounter() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(counterFileName));
        String content = "0";

        bw.write(content);
        bw.close();
    }
}
