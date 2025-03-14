package xjtlu.cpt111.assignment.quiz.my_util;

import xjtlu.cpt111.assignment.quiz.my_model.Score;
import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import java.io.*;
import java.util.*;

/**
 * This method is used to store the user ID and score in a csv file named topic
 */
public class ScoreUtil {
    private static final String SCORE_PATH = "assignment1_quizSystem" + File.separator + "resources" + File.separator + "scores" + File.separator;

    /**
     * Create the user's own file for all scores history
     * @param id user ID
     */
    public static void createFile(String id) {
        File userHistory = new File(SCORE_PATH + "users" + File.separator + id + ".csv");
        try {
            if (!userHistory.exists()) {
                userHistory.createNewFile();
            } else throw new RuntimeException("file already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the user's recent scores to initialize a score list for quick access
     * Use a string list to store the most recent lines read from the user's history file.
     * This list will only hold the latest 'recentCount' lines to minimize memory usage.
     * @param id user ID
     * @param recentCount the number of recent scores we want
     * @return an array of recent scores with the newest score at the first position. If the user has less than recentCount scores, the array will be filled with null.
     */
    public static Score[] getRecent(String id, int recentCount) {
        File userHistory = new File(SCORE_PATH + "users" + File.separator + id + ".csv");
        Score[] recentScores = new Score[recentCount];
        List<String> recentLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(userHistory))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (recentLines.size() >= recentCount) {
                    recentLines.removeFirst();
                }
                recentLines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the user history file: " + e.getMessage(), e);
        }

        // parse the recent lines into Score objects
        int index = 0;
        for (int i = recentLines.size() - 1; i >= 0; i--) {
            String[] data = recentLines.get(i).split(",");
            Topic topic = Topic.valueOf(data[0]);
            int score = Integer.parseInt(data[1]);
            recentScores[index++] = new Score(topic, score);
        }

        // if the user has less than recentCount scores, fill the rest with null
        for (int i = index; i < recentCount; i++) {
            recentScores[i] = null;
        }

        return recentScores;
    }

    /**
     * Reads all score records for a given topic from the topic file.
     * @param topic the topic to read scores for
     * @return a list of scores for the given topic
     */
    public static List<Score> readTopicScores(Topic topic) {
        List<Score> records = new ArrayList<>();
        File topicFile = new File(SCORE_PATH + "topic" + File.separator + "scores_" + topic.getFileNamePart() + ".csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(topicFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String userId = values[0];
                String userName = values[1];
                int scorePoint = Integer.parseInt(values[2]);
                records.add(new Score(topic, scorePoint, userId, userName));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading scores for topic: " + topic, e);
        }

        return records;
    }


    /**
     * Save the user's scores to the user's score file and the topic's score file.
     * @param id the user's id
     * @param name the user's name
     * @param newScores the user's new scores to be saved
     */
    public static void saveScore(String id, String name, List<Score> newScores) {
        File userHistory = new File(SCORE_PATH + "users" + File.separator + id + ".csv");
        Map<Topic, BufferedWriter> topicWriters = new HashMap<>();

        try (BufferedWriter userWriter = new BufferedWriter(new FileWriter(userHistory, true))) {
            // the main loop to save the scores
            for (Score score : newScores) {
                Topic topic = score.getTopic();
                int scorePoint = score.getScorePoint();
                userWriter.write(topic + "," + scorePoint + "\n");

                // create the topic file writer if it doesn't exist
                topicWriters.computeIfAbsent(topic, k -> {
                    try {
                        File topicFile = new File( SCORE_PATH+ "topic" + File.separator + "scores_" + topic.getFileNamePart() + ".csv");
                        return new BufferedWriter(new FileWriter(topicFile, true));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                // choose the correct topic file writer and write the score
                BufferedWriter topicWriter = topicWriters.get(topic);
                topicWriter.write(id + "," + name + "," + scorePoint + "\n");
            }

            // close all topic file writers safely
            for (BufferedWriter writer : topicWriters.values()) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

