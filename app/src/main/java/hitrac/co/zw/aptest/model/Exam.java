package hitrac.co.zw.aptest.model;

import java.util.List;

public class Exam {

   private String examName;
   private String id;
   private List<Question>questionList;
   private Subject subject;
   private Syllabus syllabus;



    public Exam(String examName, String id, List<Question> questionList, Subject subject, Syllabus syllabus) {
        this.examName = examName;
        this.id = id;
        this.questionList = questionList;
        this.subject = subject;
        this.syllabus = syllabus;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Syllabus getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examName='" + examName + '\'' +
                ", id='" + id + '\'' +
                ", questionList=" + questionList +
                ", subject=" + subject +
                ", syllabus=" + syllabus +
                '}';
    }
}

