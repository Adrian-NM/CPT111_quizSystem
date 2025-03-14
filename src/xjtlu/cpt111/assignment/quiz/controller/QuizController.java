package xjtlu.cpt111.assignment.quiz.controller;

import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import xjtlu.cpt111.assignment.quiz.my_util.QuestionUtil;
import xjtlu.cpt111.assignment.quiz.service.QuestionService;
import xjtlu.cpt111.assignment.quiz.service.UserService;
import xjtlu.cpt111.assignment.quiz.view.QuizView;
import xjtlu.cpt111.assignment.quiz.model.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller for the Quiz System.
 */
public class QuizController implements Controller{
    private final QuizView view;
    private String nextController;

    public QuizController() {
        view = new QuizView(this);
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
     * Start the quiz with the given topic.
     * This method will read all questions from the file, shuffle them and select 1 random question from each difficulty level.
     * All questions selected are validated.
     * Then it will prompt the user to answer each question and calculate the score.
     * @param topic The topic of the quiz.
     */
    public void startQuiz(Topic topic){
        List<Question> questionList = new ArrayList<>();
        for (Question question : QuestionUtil.loadQuestions(topic)) {
            if (QuestionService.isValidQuestion(question)) {
                questionList.add(question);
            }
        }
        Collections.shuffle(questionList);

        Question[] selectedQuestions = new Question[4];
        int[] correctAnswers = new int[4];
        for (Question question : questionList){
            switch (question.getDifficulty()){
                case EASY:
                    if (selectedQuestions[0] == null){
                        selectedQuestions[0] = question;
                        correctAnswers[0] = QuestionService.getCorrectAnswer(question);
                        break;
                    }
                case MEDIUM:
                    if (selectedQuestions[1] == null){
                        selectedQuestions[1] = question;
                        correctAnswers[1] = QuestionService.getCorrectAnswer(question);
                        break;
                    }
                case HARD:
                    if (selectedQuestions[2] == null){
                        selectedQuestions[2] = question;
                        correctAnswers[2] = QuestionService.getCorrectAnswer(question);
                        break;
                    }
                case VERY_HARD:
                    if (selectedQuestions[3] == null){
                        selectedQuestions[3] = question;
                        correctAnswers[3] = QuestionService.getCorrectAnswer(question);
                        break;
                    }
            }
        }

        view.startQuiz();
        int scorePoint = 0;
        for (int i = 0; i < selectedQuestions.length; i++){
            int answer = view.getAnswer(selectedQuestions[i].toString("Question #" + (i + 1), false), selectedQuestions[i].getOptions().length);
            scorePoint += answer == correctAnswers[i] ? 10 * (i + 1) : 0; // The score of each question is ascending by its index, which is the difficulty level.
        }

        boolean breakRecord = UserService.quickSave(topic, scorePoint);
        view.displayResult(scorePoint, breakRecord);

        nextController = "Menu";
    }

    /**
     * Go back to Menu.
     * Switch to the menu controller.
     */
    public void backToMenu(){
        nextController = "Menu";
    }
}
