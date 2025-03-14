package xjtlu.cpt111.assignment.quiz.controller;

import xjtlu.cpt111.assignment.quiz.my_util.ScoreUtil;
import xjtlu.cpt111.assignment.quiz.view.AuthView;
import xjtlu.cpt111.assignment.quiz.my_model.User;
import xjtlu.cpt111.assignment.quiz.my_util.UserUtil;
import xjtlu.cpt111.assignment.quiz.service.UserService;

/**
 * Controller for authentication, including login and register functions.
 */
public class AuthController implements Controller{
    private final AuthView view;
    private String nextController;

    public AuthController() {
        this.view = new AuthView(this);
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
     * Login function.
     * Check if the user is valid according to the registration file, and if so, switch to Menu Controller.
     * @param id user ID
     * @param password user password
     */
    public void login(String id, String password) {
        if (UserUtil.isValidUser(id, password)) {
            String userName = UserUtil.getUserName(id);
            System.out.println("Login successful. Welcome back, " + userName + "!");
            UserService.login(new User(id, userName));
            // Switch to Menu Controller
            nextController = "Menu";
        } else {
            System.out.println("Invalid ID or password. Please try again.");
            view.display();
        }
    }

    /**
     * Register function.
     * Check if the user ID is valid for registration, and if so, the parameters will be written into the registration file, and the user's own score file will be created.
     * Then login the user automatically and switch to Menu Controller.
     * @param id user ID
     * @param name user name
     * @param password user password
     */
    public void register(String id, String name, String password) {
        if (UserUtil.isValidID(id)) {
            if (!name.contains(",") && !password.contains(",")) {
                UserUtil.addUser(id, name, password);
                ScoreUtil.createFile(id);
                System.out.println("Registration successful. Welcome, " + name + "!");
                UserService.login(new User(id, name));
                // Switch to Menu Controller
                nextController = "Menu";
            } else {
                System.out.println("The name or password cannot contain comma. Please choose another name or password.");
                view.display();
            }
        } else {
            System.out.println("The ID is already taken or you provided an exception ID. Please choose another ID or login if it is your account.");
            view.display();
        }
    }
}
