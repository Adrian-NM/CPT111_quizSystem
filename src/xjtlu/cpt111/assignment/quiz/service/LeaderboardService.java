package xjtlu.cpt111.assignment.quiz.service;

import xjtlu.cpt111.assignment.quiz.my_model.Score;
import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import xjtlu.cpt111.assignment.quiz.my_util.ScoreUtil;
import java.util.*;

/**
 * Class to store the leaderboard data for each topic.
 * Each topic has a TreeMap that maps score points to a list of different users' records.
 * The TreeMap is sorted in descending order of score points.
 */
public class LeaderboardService {
    private final Map<Topic, TreeMap<Integer, List<Score>>> data;

    public LeaderboardService() {
        this.data = new HashMap<>();
        initLeaderboardData();
    }

    /**
     * Initializes leaderboard data by reading files and updating the leaderboard.
     */
    private void initLeaderboardData() {
        for (Topic topic : Topic.values()) {
            TreeMap<Integer, List<Score>> topicData = new TreeMap<>(Collections.reverseOrder());
            data.put(topic, topicData);

            // Fetch all records from the file and update the leaderboard
            List<Score> scores = ScoreUtil.readTopicScores(topic);
            for (Score score : scores) {
                updateLeaderboard(score);
            }
        }
    }

    /**
     * Updates the leaderboard data with a new score record.
     * Ensures no duplicate records exist for the same user.
     * @param newScore the new score record to be added
     */
    public void updateLeaderboard(Score newScore) {
        TreeMap<Integer, List<Score>> topicData = data.get(newScore.getTopic());

        // Remove old record
        topicData.values().forEach(list -> list.removeIf(score -> score.getUserId().equals(newScore.getUserId())));

        // Insert new record
        topicData.computeIfAbsent(newScore.getScorePoint(), k -> new ArrayList<>()).add(newScore);
    }


    public TreeMap<Integer, List<Score>> getTopicLeaderboard(Topic topic) {
        return data.get(topic);
    }

    /**
     * Get the user's highest score in each topic.
     * @param id the user ID
     * @return an array of integers representing the user's highest score in each topic
     */
    public int[] getRecords(String id) {
        int[] records = new int[Topic.values().length];
        Arrays.fill(records, 0);

        for (Topic topic : Topic.values()) {
            TreeMap<Integer, List<Score>> topicData = data.get(topic);
            if (topicData != null) {
                for (Map.Entry<Integer, List<Score>> entry : topicData.entrySet()) {
                    for (Score record : entry.getValue()) {
                        if (record.getUserId().equals(id)) {
                            records[topic.ordinal()] = entry.getKey();
                            break;
                        }
                        if (records[topic.ordinal()] > 0) break;
                    }
                }
            }
        }
        return records;
    }
}

