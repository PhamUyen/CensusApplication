package com.uyenpham.censusapplication.models.survey;

public class WarningDTO {
    private String message;
    private int type;

    public WarningDTO(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public WarningDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
