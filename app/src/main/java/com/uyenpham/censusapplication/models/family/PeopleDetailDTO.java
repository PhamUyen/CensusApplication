package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PeopleDetailDTO")
public class PeopleDetailDTO {
    public static final String ID_HO = "ID";
    @SerializedName("HOSO")
    @DatabaseField(columnName = "HOSO")
    private String mHOSO;
    @SerializedName("ID")
    @DatabaseField(columnName = "ID", id = true)
    private String mID;
    @SerializedName("IDHO")
    @DatabaseField(columnName = "IDHO")
    private String mIDHO;
    @SerializedName("Q2A")
    @DatabaseField(columnName = "Q2A")
    private Integer mQ2A;
    @SerializedName("STT")
    @DatabaseField(columnName = "STT")
    private int STT;
    @SerializedName("Q1A")
    @DatabaseField(columnName = "Q1A")
    private String Q1A;
    @SerializedName("Q1B")
    @DatabaseField(columnName = "Q1B")
    private Integer Q1B;
    @SerializedName("Q2B")
    @DatabaseField(columnName = "Q2B")
    private Integer Q2B;
    @SerializedName("Q3B")
    @DatabaseField(columnName = "Q3B")
    private Integer Q3B;
    @SerializedName("Q4B")
    @DatabaseField(columnName = "Q4B")
    private Integer Q4B;
    @SerializedName("Q5")
    @DatabaseField(columnName = "Q5")
    private String Q5;
    @SerializedName("Chuho")
    @DatabaseField(columnName = "Chuho")
    private Integer Chuho;
    private boolean isSelected;

    public PeopleDetailDTO() {
    }

    public PeopleDetailDTO(String q1, String q5,int stt, Integer chuho) {
        Q1A = q1;
        Q5 = q5;
        STT = stt;
        Chuho = chuho;
    }

    public PeopleDetailDTO(String mHOSO, String mID, String mIDHO, Integer mQ2A, int STT, String
            q1A, Integer q1B, Integer q2B, Integer q3B, Integer q4B, String q5, Integer chuho) {
        this.mHOSO = mHOSO;
        this.mID = mID;
        this.mIDHO = mIDHO;
        this.mQ2A = mQ2A;
        this.STT = STT;
        Q1A = q1A;
        Q1B = q1B;
        Q2B = q2B;
        Q3B = q3B;
        Q4B = q4B;
        Q5 = q5;
        Chuho = chuho;
    }

    public static String getIdHo() {
        return ID_HO;
    }

    public Integer getChuho() {
        return Chuho;
    }

    public void setChuho(Integer chuho) {
        Chuho = chuho;
    }

    public String getmHOSO() {
        return mHOSO;
    }

    public void setmHOSO(String mHOSO) {
        this.mHOSO = mHOSO;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmIDHO() {
        return mIDHO;
    }

    public void setmIDHO(String mIDHO) {
        this.mIDHO = mIDHO;
    }

    public Integer getmQ2A() {
        return mQ2A;
    }

    public void setmQ2A(Integer mQ2A) {
        this.mQ2A = mQ2A;
    }

    public String getQ1A() {
        return Q1A;
    }

    public void setQ1A(String q1A) {
        Q1A = q1A;
    }

    public Integer getQ1B() {
        return Q1B;
    }

    public void setQ1B(Integer q1B) {
        Q1B = q1B;
    }

    public Integer getQ2B() {
        return Q2B;
    }

    public void setQ2B(Integer q2B) {
        Q2B = q2B;
    }

    public Integer getQ3B() {
        return Q3B;
    }

    public void setQ3B(Integer q3B) {
        Q3B = q3B;
    }

    public Integer getQ4B() {
        return Q4B;
    }

    public void setQ4B(Integer q4B) {
        Q4B = q4B;
    }

    public String getQ5() {
        return Q5;
    }

    public void setQ5(String q5) {
        Q5 = q5;
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

}
