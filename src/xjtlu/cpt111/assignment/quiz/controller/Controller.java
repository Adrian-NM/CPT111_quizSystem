package xjtlu.cpt111.assignment.quiz.controller;

/**
 * The base interface for all controllers.
 * Controllers are responsible for the logic and manipulation of the models and views.
 * It defines two methods:
 * {@link #start()} to start the controller and display the regarding view.
 * {@link #getNextControllerRequest()}. to pass the name of the next controller request to {@link xjtlu.cpt111.assignment.quiz.Main} according to the controller's logic.
 */
public interface Controller {
    void start();
    String getNextControllerRequest();
}
