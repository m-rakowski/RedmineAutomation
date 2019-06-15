package com.practice.redmine.automation.utils;

import com.practice.redmine.automation.entities.User;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Ignore
public class _UserDataGeneratorTest {

    @Test
    public void deleteAllTestUsers() {
        UserDataGenerator.deleteUsersFromSystem();
    }

    @Test
    public void shouldResetCounter() throws IOException {

        UserDataGenerator.resetCounter();
        assertEquals("0", UserDataGenerator.readLastCountFromFile());
    }

    @Test
    public void shouldReadFileAsString() throws IOException {

        String fileContent = UserDataGenerator.readLastCountFromFile();
        assertNotEquals("", fileContent);
    }

    @Test
    public void shouldIncrementCounter() throws IOException {

        //Given
        String counterPreIncrement = UserDataGenerator.readLastCountFromFile();

        //When
        UserDataGenerator.incrementCounter();

        //Then
        String counterPostIncrement = UserDataGenerator.readLastCountFromFile();
        assertEquals(1, Integer.parseInt(counterPostIncrement) - Integer.parseInt(counterPreIncrement));
    }

    @Test
    public void shouldGenerateUsers() throws IOException {

        //When
        User user1 = UserDataGenerator.getNewUser();

        //Then
        String pattern = "(.*)(\\d+)";
        assertTrue(user1.username.matches(pattern));
    }

    @Test
    public void shouldGenerateUser() throws IOException {

        User user = UserDataGenerator.getNewUser();

        Pattern r = Pattern.compile("([^0-9]+)(\\d+)");
        Matcher m = r.matcher(user.username);

        if (m.matches()) {
            String count = m.group(2);

            assertEquals("TestUser" + count + "FirstName", user.firstName);
            assertEquals("TestUser" + count + "LastName", user.lastName);
            assertEquals("testuser" + count + "@test.com", user.email);
            assertEquals("TestUser" + count + "Password", user.password);
            assertEquals("English (British)", user.language);

        } else {
            fail("Incorrect username: " + user.username);
        }

    }

}