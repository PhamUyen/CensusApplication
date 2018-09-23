package com.uyenpham.censusapplication.models.survey;

public class AnswerDTO {
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_ANSWER = "answer";
    public final static String COLUMN_QUESTION_ID = "questionID";
    private String id;
    private Object answer;
    private String questionID;

    public AnswerDTO(String id, Object answer, String questionID) {
        this.id = id;
        this.answer = answer;
        this.questionID = questionID;
    }

    public AnswerDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
}
