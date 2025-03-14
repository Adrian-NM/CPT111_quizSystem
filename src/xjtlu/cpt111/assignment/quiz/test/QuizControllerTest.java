package xjtlu.cpt111.assignment.quiz.test;

import org.junit.Before;
import org.junit.Test;
import xjtlu.cpt111.assignment.quiz.controller.QuizController;
import xjtlu.cpt111.assignment.quiz.my_util.QuestionUtil;
import xjtlu.cpt111.assignment.quiz.view.QuizView;

import static org.junit.Assert.assertEquals;

public class QuizControllerTest {

    private QuizController quizController;
    private QuizView view;
    private QuestionUtil questionUtil;

    @Before
    public void setUp() {
        quizController = new QuizController();
        view = new QuizView(quizController);
        questionUtil = new QuestionUtil();
    }



    @Test
    public void testBackToMenu() {
        quizController.backToMenu();
        assertEquals("Menu", quizController.getNextControllerRequest()); // 验证返回的控制器请求
    }
}
