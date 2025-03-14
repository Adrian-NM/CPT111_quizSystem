package xjtlu.cpt111.assignment.quiz;

import xjtlu.cpt111.assignment.quiz.controller.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the entry point of the application.
 */
public class Main {
    private static Controller controller;
    private static final Map<String, Controller> controllers = new HashMap<>();

    /**
     * The main method of the application.
     * It sets the first controller to the AuthController and then enters a loop where it waits for the controller to request a new controller.
     * It uses a HashMap to store the instances of the controllers to avoid creating new instances of the same controller.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.controller = new AuthController();
        Main.controller.start();

        while(true) {
            String request = Main.controller.getNextControllerRequest();
            if (request != null) {
                Controller nextController = controllers.get(request);
                if (nextController == null) {
                    nextController = getControllerInstance(request);
                    controllers.put(request, nextController);
                }
                nextController.start();
                Main.controller = nextController;
            }
        }
    }

    /**
     * This helper method returns an instance of the controller specified by the controllerName parameter.
     * @param controllerName the name of the controller to return
     * @return an instance of the controller specified by the controllerName parameter
     */
    private static Controller getControllerInstance(String controllerName) {
        return switch (controllerName) {
            case "Menu" -> new MenuController();
            case "Auth" -> new AuthController();
            case "Quiz" -> new QuizController();
            case "Leaderboard" -> new LeaderboardController();
            case "Dashboard" -> new DashboardController();
            default -> throw new IllegalArgumentException("Invalid controller name: " + controllerName);
        };
    }

}
