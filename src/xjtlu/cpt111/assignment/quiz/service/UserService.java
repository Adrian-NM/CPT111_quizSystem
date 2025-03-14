package xjtlu.cpt111.assignment.quiz.service;

import xjtlu.cpt111.assignment.quiz.my_model.Score;
import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import xjtlu.cpt111.assignment.quiz.my_model.User;
import xjtlu.cpt111.assignment.quiz.my_util.ScoreUtil;
import java.util.*;

public class UserService {
    private static User currentUser;
    private static int[] records;
    private static Score[] recentScores;
    private static List<Score> newScores;
    private static LeaderboardService leaderboardData;

    /**
     * Log in the user and initialize the user's state.
     * @param user the user to log in
     */
    public static void login(User user) {
        currentUser = user;
        newScores = new ArrayList<>();

        // Initialize the user's recent scores
        String userId = currentUser.getId();
        recentScores = ScoreUtil.getRecent(userId, 3);

        // Initialize the leaderboard data if it doesn't exist
        if (leaderboardData == null) {
            leaderboardData = new LeaderboardService();
        }
        records = leaderboardData.getRecords(userId);

        // Register a shutdown hook to ensure scores are saved
        Runtime.getRuntime().addShutdownHook(new Thread(UserService::saveScoresOnExit));
    }

    /**
     * Log out the user without saving scores (delegates to saveScoresOnExit for saving).
     */
    public static void logout() {
        saveScoresOnExit(); // Save scores if not already saved
        currentUser = null;
        records = null;
        newScores = null;
        recentScores = null;
    }

    /**
     * Save the user's score when the program exits or user logs out.
     */
    private static synchronized void saveScoresOnExit() {
        if (newScores != null && currentUser != null) { // Ensure saveScore is called only once
            ScoreUtil.saveScore(currentUser.getId(), currentUser.getName(), newScores);
            newScores = null;
        }
    }

    /**
     * Save the user's score in the memory and update the leaderboard if necessary.
     * @param topic the topic of the quiz
     * @param scorePoint the score point of the quiz
     * @return true if the score is higher than the previous record, false otherwise
     */
    public static boolean quickSave(Topic topic, int scorePoint) {
        Score score = new Score(topic, scorePoint, currentUser.getId(), currentUser.getName());
        newScores.add(score);

        // Shift the recent scores to the right and add the new score to the first position
        for (int i = recentScores.length - 1; i > 0; i--) {
            recentScores[i] = recentScores[i - 1];
        }
        recentScores[0] = score;

        if (scorePoint > records[topic.ordinal()]) {
            records[topic.ordinal()] = scorePoint;
            leaderboardData.updateLeaderboard(score);
            return true;
        }
        return false;
    }

    public static Score[] getRecentScores() {
        return recentScores;
    }

    public static TreeMap<Integer, List<Score>> getLeaderboard(Topic topic) {
        return leaderboardData.getTopicLeaderboard(topic);
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}


