package xjtlu.cpt111.assignment.quiz.view;

import xjtlu.cpt111.assignment.quiz.controller.QuizController;
import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import xjtlu.cpt111.assignment.quiz.my_util.InputUtil;

import java.util.Scanner;

/**
 * View for the quiz page.
 */
public class QuizView implements View{
    private final QuizController controller;
    private final Scanner scanner;

    public QuizView(QuizController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void display() {
        System.out.println("""
                =============== Quiz ===============
                Please select a topic to take the quiz:""" + Topic.ALL_TOPICS);

        int maxOption = Topic.values().length + 1;
        System.out.println(maxOption +  ". Back to menu");
        int choice = InputUtil.getValidOption(scanner, "Choose an option: ", maxOption);
        if (choice != maxOption) controller.startQuiz(Topic.fromOptionNumber(choice));
        else controller.backToMenu();
    }

    /**
     * Display the start message of the quiz.
     */
    public void startQuiz() {
        System.out.println("""
                
                Quiz starts now! Be careful and good luck!
                Tip: All question are single choice questions. The harder the question, the more points you can get.
                """);
    }

    /**
     * Display the question and return the user's choice.
     * @param question_str The question string.
     * @param maxOption The maximum option number.
     * @return  The user's choice.
     */
    public int getAnswer(String question_str, int maxOption) {
        System.out.println(question_str);
        return InputUtil.getValidOption(scanner, "Choose the correct option: ", maxOption);
    }

    /**
     * Display the result of the quiz.
     * @param score the score of the quiz.
     * @param breakScore whether the score breaks the record.
     */
    public void displayResult(int score, boolean breakScore) {
        System.out.print("Your score is: " + score + "/100. ");
        if (breakScore) {
            System.out.println("Congratulations! You have broken the record! Check your rank in the leaderboard!");
        } else if (score == 100) {
            System.out.println("Congratulations! You got a full score!");
        } else {
            System.out.println("Better luck next time!");
        }
        System.out.println("Quiz over. The score has been saved.");
    }
}
