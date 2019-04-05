package hitrac.co.zw.aptest.model;

public class Question {

    private String question;
    private String possibleAnswer1;
    private String possibleAnswer2;
    private String possibleAnswer3;
    private String possibleAnswer4;
    private String correctAnswer;
    private int questionNumber;


    private String id;


    public Question(String question, String possibleAnswer1, String possibleAnswer2, String possibleAnswer3, String possibleAnswer4, String correctAnswer, String id, int questionNumber) {
        this.question = question;
        this.possibleAnswer1 = possibleAnswer1;
        this.possibleAnswer2 = possibleAnswer2;
        this.possibleAnswer3 = possibleAnswer3;
        this.possibleAnswer4 = possibleAnswer4;
        this.correctAnswer = correctAnswer;
        this.id = id;
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPossibleAnswer1() {
        return possibleAnswer1;
    }

    public void setPossibleAnswer1(String possibleAnswer1) {
        this.possibleAnswer1 = possibleAnswer1;
    }

    public String getPossibleAnswer2() {
        return possibleAnswer2;
    }

    public void setPossibleAnswer2(String possibleAnswer2) {
        this.possibleAnswer2 = possibleAnswer2;
    }

    public String getPossibleAnswer3() {
        return possibleAnswer3;
    }

    public void setPossibleAnswer3(String possibleAnswer3) {
        this.possibleAnswer3 = possibleAnswer3;
    }

    public String getPossibleAnswer4() {
        return possibleAnswer4;
    }

    public void setPossibleAnswer4(String possibleAnswer4) {
        this.possibleAnswer4 = possibleAnswer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", possibleAnswer1='" + possibleAnswer1 + '\'' +
                ", possibleAnswer2='" + possibleAnswer2 + '\'' +
                ", possibleAnswer3='" + possibleAnswer3 + '\'' +
                ", possibleAnswer4='" + possibleAnswer4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", id='" + id + '\'' +
                ", questionNumber=" + questionNumber +
                '}';
    }
}
