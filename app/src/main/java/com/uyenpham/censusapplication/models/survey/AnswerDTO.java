package com.uyenpham.censusapplication.models.survey;

public class AnswerDTO {
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_ANSWER = "answer";
    public final static String COLUMN_QUESTION_ID = "questionID";
    private String id;
    private String answer;
    private String questionID;

    public AnswerDTO(String id, String answer, String questionID) {
        this.id = id;
        this.answer = answer;
        this.questionID = questionID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
}
