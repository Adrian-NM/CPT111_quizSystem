package xjtlu.cpt111.assignment.quiz.view;

import xjtlu.cpt111.assignment.quiz.controller.MenuController;
import xjtlu.cpt111.assignment.quiz.my_util.InputUtil;
import xjtlu.cpt111.assignment.quiz.service.UserService;
import java.util.Scanner;

/**
 * View for the menu page.
 */
public class MenuView implements View {
    private final MenuController controller;
    private final Scanner scanner;

    public MenuView(MenuController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void display() {
        System.out.print("""
                     =============== Menu ===============
                     You are logged in as\s""" + UserService.getCurrentUser().getName() + """
                     
                     1. Start quiz
                     2. Dashboard
                     3. Leaderboard
                     4. Log out
                     5. Quit
                     """);

        int choice = InputUtil.getValidOption(scanner, "Choose an option: ", 5);
        switch (choice) {
            case 1:
                controller.startQuiz();
                break;
            case 2:
                controller.dashboard();
                break;
            case 3:
                controller.leaderboard();
                break;
            case 4:
                controller.logout();
                break;
            case 5:
                System.exit(1);
                break;
        }
    }
}

