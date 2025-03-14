package xjtlu.cpt111.assignment.quiz.service;

import xjtlu.cpt111.assignment.quiz.model.Option;
import xjtlu.cpt111.assignment.quiz.model.Question;

public class QuestionService {
    /**
     * Validate the question.
     * A question is valid if it has at least 2 options and only one correct answer.
     * No need to check if the question has a question statement and a topic as they are included in all constructors of Question.
     * @param question the question
     * @return true if the question is valid, false otherwise
     */
    public static boolean isValidQuestion(Question question) {
        Option[] options = question.getOptions();
        if (options.length < 2) return false;
        int correctCount = 0;
        for (Option option : options) {
            if (option.isCorrectAnswer()) correctCount++;
        }
        return correctCount == 1;
    }

    /**
     * Get the index of the correct answer.
     * If there is no correct answer, return 0.
     */
    public static int getCorrectAnswer(Question question) {
        Option[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            if (options[i].isCorrectAnswer()) {
                return (i + 1);
            }
        }
        return 0;
    }
}
