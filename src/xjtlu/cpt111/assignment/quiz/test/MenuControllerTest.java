package xjtlu.cpt111.assignment.quiz.test;

import org.junit.Before;
import org.junit.Test;
import xjtlu.cpt111.assignment.quiz.controller.MenuController;

import static org.junit.Assert.*;

public class MenuControllerTest {
    private MenuController menuController;

    @Before
    public void setUp() {
        menuController = new MenuController();
    }



    @Test
    public void testStartQuiz() {
        menuController.startQuiz();
        assertEquals("Quiz", menuController.getNextControllerRequest());
    }

    @Test
    public void testDashboard() {
        menuController.dashboard();
        assertEquals("Dashboard", menuController.getNextControllerRequest());
    }

    @Test
    public void testLeaderboard() {
        menuController.leaderboard();
        assertEquals("Leaderboard", menuController.getNextControllerRequest());
    }


}

/*
Starting a Quiz: The startQuiz method correctly sets the next controller to "Quiz".
Navigating to Dashboard: The dashboard method correctly sets the next controller to "Dashboard".
Navigating to Leaderboard: The leaderboard method correctly sets the next controller to "Leaderboard".
 */
