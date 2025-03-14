package xjtlu.cpt111.assignment.quiz.my_model;

public class Score {
    private final Topic topic;
    private final int scorePoint;

    // Optional user details (for leaderboard or ranking purposes)
    private String userId;
    private String userName;

    // Constructor for a standalone score (e.g., recent scores)
    public Score(Topic topic, int scorePoint) {
        this.topic = topic;
        this.scorePoint = scorePoint;
    }

    // Constructor for a leaderboard score
    public Score(Topic topic, int scorePoint, String userId, String userName) {
        this.topic = topic;
        this.scorePoint = scorePoint;
        this.userId = userId;
        this.userName = userName;
    }

    public Topic getTopic() {
        return topic;
    }

    public int getScorePoint() {
        return scorePoint;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}


