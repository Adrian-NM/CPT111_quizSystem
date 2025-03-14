package xjtlu.cpt111.assignment.quiz.my_util;

import xjtlu.cpt111.assignment.quiz.model.*;
import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import xjtlu.cpt111.assignment.quiz.util.IOUtilities;
import java.io.File;

public class QuestionUtil {
    private static final String QUESTION_BACK_PATH = "assignment1_quizSystem" + File.separator + "resources" + File.separator + "questionsBank" + File.separator;

    /**
     * Load all questions in the specified topic.
     * @param topic the topic
     * @return an array of questions
     */
    public static Question[] loadQuestions(Topic topic) {
        String path = QUESTION_BACK_PATH + "questions_" + topic.getFileNamePart() + ".xml";
        return IOUtilities.readQuestions(path);
    }
}
