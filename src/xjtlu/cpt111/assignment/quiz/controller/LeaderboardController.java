package xjtlu.cpt111.assignment.quiz.controller;

import xjtlu.cpt111.assignment.quiz.my_model.Score;
import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import xjtlu.cpt111.assignment.quiz.service.UserService;
import xjtlu.cpt111.assignment.quiz.view.LeaderboardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LeaderboardController implements Controller{
    private final LeaderboardView view;
    private String nextController;

    public LeaderboardController() {
        view = new LeaderboardView(this);
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
     * Get the leaderboard data for the given topic.
     * Fetches data, calculates user rank, and prepares output for the view.
     * @param topic the topic to display the leaderboard for
     */
    public void getTopicLeaderboard(Topic topic) {
        TreeMap<Integer, List<Score>> topicData = UserService.getLeaderboard(topic);

        // Prepare leaderboard data
        List<String> leaderboard = new ArrayList<>();
        String currentUserId = UserService.getCurrentUser().getId();
        int rank = 1, userRank = -1; // the default value of -1 indicates that the user is not on the leaderboard
        int prevScore = -1; // Initialize with an invalid score

        // Iterate over scores (descending order) to build leaderboard
        for (Map.Entry<Integer, List<Score>> entry : topicData.entrySet()) {
            int score = entry.getKey();

            // Only update rank if the score changes
            if (score != prevScore) {
                prevScore = score; // Update previous score
            } else {
                rank--; // Same score, retain rank for this group
            }

            for (Score record : entry.getValue()) {
                leaderboard.add(rank + ". " + record.getUserName() + ": " + score);
                if (record.getUserId().equals(currentUserId)) {
                    userRank = rank; // Record current user's rank
                }
            }
            rank += entry.getValue().size(); // Increase rank based on group size
        }

        view.displayTopicLeaderboard(leaderboard, userRank);
    }



    public void backToMenu() {
        nextController = "Menu";
    }
}
