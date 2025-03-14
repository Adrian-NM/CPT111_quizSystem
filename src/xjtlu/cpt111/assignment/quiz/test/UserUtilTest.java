package xjtlu.cpt111.assignment.quiz.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xjtlu.cpt111.assignment.quiz.my_util.UserUtil;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UserUtilTest {

    private static final String TEST_RESOURCE_PATH = "assignment1_quizSystem" + File.separator + "resources" + File.separator;
    private static final String TEST_USER_FILE = TEST_RESOURCE_PATH + "users.csv";

    @Before
    public void setUp() throws IOException {
        new File(TEST_RESOURCE_PATH).mkdirs();
        // build test users
        try (FileWriter writer = new FileWriter(TEST_USER_FILE, true)) {
            writer.write("user1,User One,password1\n");
        }
    }

    @Test
    public void testIsValidUser() {
        assertTrue(UserUtil.isValidUser("user1", "password1")); // user1 valid
        assertFalse(UserUtil.isValidUser("user1", "wrongPassword")); // password wrong
        assertFalse(UserUtil.isValidUser("unknownUser", "password1")); // id wrong
    }

    @Test
    public void testIsValidID() {
        assertFalse(UserUtil.isValidID("user1")); // test exist user, expect return false
        assertTrue(UserUtil.isValidID("newUser")); // test valid ID
        assertFalse(UserUtil.isValidID("")); // test empty
        assertFalse(UserUtil.isValidID("invalid/file")); // test contain /
        assertFalse(UserUtil.isValidID("invalid,file"));
        assertFalse(UserUtil.isValidID("invalid!file"));
        assertFalse(UserUtil.isValidID("invalid?file"));
        assertFalse(UserUtil.isValidID("invalid>file"));
        assertFalse(UserUtil.isValidID("invalid<file"));
        assertFalse(UserUtil.isValidID("invalid|file"));
        assertFalse(UserUtil.isValidID("invalid//file"));
    }

    @Test
    public void testAddUser() throws IOException {
        UserUtil.addUser("user3", "User Three", "password3"); // new user: User3
        assertTrue(UserUtil.isValidUser("user3", "password3"));
        assertEquals("User Three", UserUtil.getUserName("user3"));
    }

    @After
    public void tearDown() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(TEST_USER_FILE));
        int linesToKeep = lines.size() - 1;

        if (linesToKeep > 0) {
            try (FileWriter writer = new FileWriter(TEST_USER_FILE, false)) {

                for (int i = 0; i < linesToKeep; i++) {
                    writer.write(lines.get(i) + "\n");
                }
            }
        } else {
            new FileWriter(TEST_USER_FILE, false).close();
        }
    }




}
