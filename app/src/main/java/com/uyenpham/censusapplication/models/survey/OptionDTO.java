package com.uyenpham.censusapplication.models.survey;

public class OptionDTO {
    private String option;
    private  String type;
    private boolean isSelected;

    public OptionDTO() {
    }

    public OptionDTO(String option, String type, boolean isSelected) {
        this.option = option;
        this.type = type;
        this.isSelected = isSelected;
    }

    public OptionDTO(String option, String type) {
        this.option = option;
        this.type = type;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
