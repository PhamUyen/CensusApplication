package com.uyenpham.censusapplication.models.survey;

public class AnswerDTO {
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_ANSWER = "answerString";
    public final static String COLUMN_QUESTION_ID = "questionID";
    private String idHo;
    private String answerString;
    private Integer answerInt;
    private String questionID;
    private String id;

    public AnswerDTO(String id, String answer, String questionID, String idHo, Integer answerInt) {
        this.id = id;
        this.answerString = answer;
        this.questionID = questionID;
        this.idHo = idHo;
        this.answerInt = answerInt;
    }

    public AnswerDTO() {
    }

    public AnswerDTO(String questionID) {
        this.questionID = questionID;
    }

    public AnswerDTO(String idHo, String questionID, String id) {
        this.idHo = idHo;
        this.questionID = questionID;
        this.id = id;
    }

    public Integer getAnswerInt() {
        return answerInt;
    }

    public void setAnswerInt(Integer answerInt) {
        this.answerInt = answerInt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHo() {
        return idHo;
    }

    public void setIdHo(String idHo) {
        this.idHo = idHo;
    }

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
}
