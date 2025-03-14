package xjtlu.cpt111.assignment.quiz.view;

import xjtlu.cpt111.assignment.quiz.controller.DashboardController;

import java.util.Scanner;

public class DashboardView implements View{
    private final DashboardController controller;
    private final Scanner scanner;

    public DashboardView(DashboardController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void display() {
        System.out.println("""
                =========== Dashboard ===========
                This is the topics and scores of your last three quizzes.""");
    }

    public void displayRecentScores(String recentScores) {
        System.out.println(recentScores);
        System.out.println("""
                Press any key to return to the main menu...""");
        scanner.nextLine();
        controller.backToMenu();
    }
}
