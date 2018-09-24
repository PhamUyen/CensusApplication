package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;

public class PeopleDetailDTO extends PeopleDTO {
    public static final String ID_HO = "ID";

    @SerializedName("STT")
    private int STT;
    @SerializedName("Q1")
    private String Q1;
    @SerializedName("Chuho")
    private Integer Chuho;
    private boolean isSelected;

    public PeopleDetailDTO() {
    }

    public PeopleDetailDTO(String q1, Integer chuho,int STT) {
        Q1 = q1;
        Chuho = chuho;
        STT = STT;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public PeopleDetailDTO(String mHOSO, String mID, String mIDHO, Integer mQ2, Integer mQ3,
                           Integer mQ4, Integer mQ5, Integer mQ61, Integer mQ62, Integer mQ63,
                           Integer mQ64, Integer mQ65, Integer mQ66, Integer mQ7, Integer mQ8,
                           Integer mQ9, String q1, Integer chuho) {
        super(mHOSO, mID, mIDHO, mQ2, mQ3, mQ4, mQ5, mQ61, mQ62, mQ63, mQ64, mQ65, mQ66, mQ7,
                mQ8, mQ9);
        Q1 = q1;
        Chuho = chuho;
    }

    public String getQ1() {
        return Q1;
    }

    public void setQ1(String q1) {
        Q1 = q1;
    }

    public Integer getChuho() {
        return Chuho;
    }

    public void setChuho(Integer chuho) {
        Chuho = chuho;
    }
}
