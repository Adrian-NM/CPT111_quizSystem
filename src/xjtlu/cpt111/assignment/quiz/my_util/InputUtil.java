package xjtlu.cpt111.assignment.quiz.my_util;

import java.util.Scanner;

public class InputUtil {
    public static int getValidOption(Scanner scanner, String prompt, int maxOption) {
        int input = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 1 && input <= maxOption) {
                    valid = true;
                } else System.out.println("Invalid input. Please enter a valid option.");
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character
        return input;
    }
}
