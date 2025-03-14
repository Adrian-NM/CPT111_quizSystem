package xjtlu.cpt111.assignment.quiz.my_model;

/**
 * This enum class represents the topics that the quiz can be based on.
 */
public enum Topic {
    COMPUTER_SCIENCE("Computer Science", "cs"),
    ELECTRONIC_ENGINEERING("Electronic Engineering", "ee"),
    ENGLISH("English", "english"),
    MATHEMATICS("Mathematics", "mathematics");

    /**
     * This is the string representation of all topics for the user to choose from.
     */
    public static final String ALL_TOPICS;
    static {
        StringBuilder sb = new StringBuilder();
        for (Topic topic : Topic.values()) {
            sb.append("\n").append(topic.optionNumber).append(". ").append(topic.getDisplayName());
        }
        ALL_TOPICS = sb.toString();
    }

    private final String displayName;
    private final String fileNamePart;
    private final int optionNumber;

    Topic(String displayName, String fileNamePart) {
        this.displayName = displayName;
        this.fileNamePart = fileNamePart;
        this.optionNumber = this.ordinal() + 1; // Option number starts from 1
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFileNamePart() {
        return fileNamePart;
    }

    public static Topic fromOptionNumber(int optionNumber) {
        for (Topic topic : Topic.values()) {
            if (topic.optionNumber == optionNumber) {
                return topic;
            }
        }
        return null;
    }
}
