package com.uyenpham.censusapplication.models.survey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FakeQuestionsResponse {
    @SerializedName("questions")
    private ArrayList<QuestionDTO> questions;

    public FakeQuestionsResponse(ArrayList<QuestionDTO> questions) {
        this.questions = questions;
    }

    public ArrayList<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionDTO> questions) {
        this.questions = questions;
    }
}
