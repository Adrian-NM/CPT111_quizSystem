package xjtlu.cpt111.assignment.quiz.view;

import xjtlu.cpt111.assignment.quiz.controller.AuthController;
import xjtlu.cpt111.assignment.quiz.my_util.InputUtil;
import java.util.Scanner;

/**
 * View for the authentication page.
 */
public class AuthView implements View {
    private final AuthController controller;
    private final Scanner scanner;

    public AuthView(AuthController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void display() {
        System.out.println("""
                
                Welcome to the quiz system application.
                You are not logged in.
                1. Log in
                2. Register for a new account
                3. Quit""");

        int choice = InputUtil.getValidOption(scanner, "Choose an option: ", 3);

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    /**
     * Prompts the user to enter their ID and password, and then calls the controller's login method.
     */
    private void login() {
        System.out.print("User ID: ");
        String id = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        controller.login(id, password);
    }

    /**
     * Prompts the user to enter the ID, name, and password, and then calls the controller's register method.
     */
    private void register() {
        System.out.print("User ID: ");
        String id = scanner.nextLine();
        System.out.print("User Name: ");
        String name = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        controller.register(id, name, password);
    }
}
