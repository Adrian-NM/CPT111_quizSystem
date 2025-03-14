package xjtlu.cpt111.assignment.quiz.controller;

import xjtlu.cpt111.assignment.quiz.my_model.Score;
import xjtlu.cpt111.assignment.quiz.service.UserService;
import xjtlu.cpt111.assignment.quiz.view.DashboardView;

public class DashboardController implements Controller {
    private final DashboardView view;
    private String nextController;

    public DashboardController() {
        this.view = new DashboardView(this);
    }

    @Override
    public void start() {
        view.display();

        StringBuilder sb = new StringBuilder();
        for (Score score : UserService.getRecentScores()) {
            if (score == null) {
                sb.append("No more recent scores. Go to take some quizzes!");
                break;
            } else {
                sb.append(score.getTopic().getDisplayName()).append(": ").append(score.getScorePoint()).append("\n");
            }
        }
        view.displayRecentScores(sb.toString());
    }

    @Override
    public String getNextControllerRequest() {
        return nextController;
    }

    public void backToMenu() {
        nextController = "Menu";
    }
}