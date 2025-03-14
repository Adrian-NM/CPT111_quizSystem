package xjtlu.cpt111.assignment.quiz.controller;

import xjtlu.cpt111.assignment.quiz.view.MenuView;
import xjtlu.cpt111.assignment.quiz.service.UserService;

/**
 * Controller for the menu.
 */
public class MenuController implements Controller{
    private final MenuView view;
    private String nextController;

    public MenuController() {
        view = new MenuView(this);
    }

    @Override
    public void start() {
        view.display();
    }

    @Override
    public String getNextControllerRequest() {
        return nextController;
    }

    /**
     * Start the quiz.
     * Set the next controller to Quiz.
     */
    public void startQuiz() {
        nextController = "Quiz";
    }

    public void dashboard(){
        nextController = "Dashboard";
    }

    public void leaderboard(){
        nextController = "Leaderboard";
    }

    /**
     * Logout function.
     * Set the current user to null and switch to AuthController.
     */
    public void logout() {
        UserService.logout();
        nextController = "Auth";
    }

}

