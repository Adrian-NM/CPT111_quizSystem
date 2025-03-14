package xjtlu.cpt111.assignment.quiz.view;

import xjtlu.cpt111.assignment.quiz.controller.LeaderboardController;
import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import xjtlu.cpt111.assignment.quiz.my_util.InputUtil;

import java.util.List;
import java.util.Scanner;

public class LeaderboardView implements View{
    private final LeaderboardController controller;
    private final Scanner scanner;

    public LeaderboardView(LeaderboardController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void display() {
        System.out.println("""
                =============== Leaderboard ===============
                Which topic do you want to view?""" + Topic.ALL_TOPICS);

        int choice = InputUtil.getValidOption(scanner, "Choose a topic: ", Topic.values().length);
        controller.getTopicLeaderboard(Topic.values()[choice - 1]);
    }

    public void displayTopicLeaderboard(List<String> leaderboard, int userRank) {
        System.out.println("Leaderboard:");
        leaderboard.forEach(System.out::println);

        if (userRank != -1) {
            System.out.println("\nYour rank: " + userRank);
        } else {
            System.out.println("\nYou are not in the leaderboard");
        }
        System.out.println("""
                Press any key to return to the main menu...""");
        scanner.nextLine();
        controller.backToMenu();
    }
}
