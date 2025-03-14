package xjtlu.cpt111.assignment.quiz.my_util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;
import java.nio.file.Paths;

public class UserUtil {
    private static final String REGISTRY_PATH = "assignment1_quizSystem" + File.separator + "resources" + File.separator + "users.csv";

    /**
     * Get a scanner for reading the users.csv file
     * @return a scanner for reading the users.csv file
     */
    private static Scanner getUserData() {
        File userfile = new File(REGISTRY_PATH);
        try {
            return new Scanner(userfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check if the given id and password match a user in the users.csv file
     * @param id user id
     * @param password user password
     * @return true if the given id and password match a user in the users.csv file, false otherwise
     */
    public static boolean isValidUser(String id, String password) {
        try (Scanner scanner = getUserData()) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] cols = line.split(",");
                if (cols[0].equals(id) && cols[2].equals(password)) return true;
            }
            return false;
        }
    }

    /**
     * Get the name of a user given the id
     * @param id user id
     * @return the name of the user with the given id
     */
    public static String getUserName(String id) {
        try (Scanner scanner = getUserData()) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] cols = line.split(",");
                if (cols[0].equals(id)) return cols[1];
            }
            return null;
        }
    }

    /**
     * Check if the given id is valid for register
     * A valid ID should be:
     * - Not registered
     * - Not empty
     * - Not longer than 10 characters
     * - Not contain any of the following characters: / \ : * ? < > | ,
     * - Not contain any invalid path characters (e.g., spaces, tabs, etc.)
     * @param id user id
     * @return true if the given id is valid, false otherwise
     */
    public static boolean isValidID(String id) {
        try (Scanner scanner = getUserData()) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] cols = line.split(",");
                if (cols[0].equals(id)) return false;
            }
            if (id == null || id.trim().isEmpty()) {
                return false;
            }
            //Basic checks:  Length and disallowed characters
            if (id.length() > 10) { //Common OS limit, adjust if needed
                return false;
            }
            if (id.contains("/") || id.contains("\\") || id.contains(":") || id.contains("*") ||
                    id.contains("?") || id.contains("<") || id.contains(">") || id.contains("|")||
                    id.contains(",")) {
                return false;
            }
            //More robust check using Paths.get (handles OS-specific path limitations):
            try {
                Paths.get(id + ".csv"); //Attempt to create a path; exceptions indicate problems
                return true;
            } catch (InvalidPathException e) {
                return false;
            } catch (Exception e) { //Catch other unexpected exceptions (e.g., security issues)
                return false;
            }
        }
    }

    /**
     * Add a line to the users.csv file
     * @param id user id
     * @param name user name
     * @param password user password
     */
    public static void addUser(String id, String name, String password) {
        File userfile = new File(REGISTRY_PATH);
        try (FileWriter writer = new FileWriter(userfile, true)) {
            String userCsv = id + "," + name + "," + password + "\n";
            writer.write(userCsv);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
