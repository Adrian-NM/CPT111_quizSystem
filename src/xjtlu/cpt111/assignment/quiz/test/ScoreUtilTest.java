package xjtlu.cpt111.assignment.quiz.test;

import xjtlu.cpt111.assignment.quiz.my_model.Score;
import xjtlu.cpt111.assignment.quiz.my_model.Topic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xjtlu.cpt111.assignment.quiz.my_util.ScoreUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreUtilTest {

    private static final String TEST_USER_ID = "testUser";
    private static final String TEST_USER_NAME = "TestUser";
    private static final String TEST_SCORE_PATH = "assignment1_quizSystem/resources/scores/users/";
    private static final String TEST_TOPIC_PATH = "assignment1_quizSystem/resources/scores/topic/";

    @Before
    public void setUp() {
        File userFile = new File(TEST_SCORE_PATH + TEST_USER_ID + ".csv");
        if (userFile.exists()) {
            userFile.delete();
        }
        new File(TEST_TOPIC_PATH).mkdirs();
    }

    @After
    public void tearDown() {
        File userFile = new File(TEST_SCORE_PATH + TEST_USER_ID + ".csv");
        if (userFile.exists()) {
            userFile.delete();
        }
    }


    /*
    Ensure the file is successfully created
     */
    @Test
    public void testCreateFile() {
        ScoreUtil.createFile(TEST_USER_ID);
        File userFile = new File(TEST_SCORE_PATH + TEST_USER_ID + ".csv");
        assertTrue(userFile.exists());
    }


    /*
    Call ScoreUtil.saveScore to save a score,
    then call ScoreUtil.getRecent to retrieve the recent score.
     */
    @Test
    public void testGetRecent() {
        ScoreUtil.createFile(TEST_USER_ID);
        List<Score> scores = new ArrayList<>();
        scores.add(new Score(Topic.COMPUTER_SCIENCE, 90, TEST_USER_ID, TEST_USER_NAME));
        ScoreUtil.saveScore(TEST_USER_ID, TEST_USER_NAME, scores);//save score in scores_cs.csv

        Score[] recentScores = ScoreUtil.getRecent(TEST_USER_ID, 1);
        assertEquals(1, recentScores.length); // assert length is 1
        assertEquals(90, recentScores[0].getScorePoint()); // 90
    }
}