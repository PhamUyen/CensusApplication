
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DeadDTO {
    public static final String ID_HO = "ID";
    public static final String ID_DEAD = "IDTV";
    @SerializedName("C43")
    private String mC43;
    @SerializedName("C44")
    private Long mC44;
    @SerializedName("C45N")
    private String mC45N;
    @SerializedName("C45T")
    private String mC45T;
    @SerializedName("C46N")
    private String mC46N;
    @SerializedName("C46T")
    private String mC46T;
    @SerializedName("C47")
    private Integer mC47;
    @SerializedName("C48")
    private Long mC48;
    @SerializedName("C48K")
    private String mC48K;
    @SerializedName("C49")
    private Integer mC49;
    @SerializedName("C49K")
    private String mC49K;
    @SerializedName("ID")
    private String mID;
    @SerializedName("IDCHET")
    private String mIDCHET;

    public DeadDTO() {
    }

    public DeadDTO(String mC43, Long mC44, String mC45N, String mC45T, String mC46N, String
            mC46T, Integer mC47, Long mC48, String mC48K, Integer mC49, String mC49K, String mID,
                   String mIDCHET) {
        this.mC43 = mC43;
        this.mC44 = mC44;
        this.mC45N = mC45N;
        this.mC45T = mC45T;
        this.mC46N = mC46N;
        this.mC46T = mC46T;
        this.mC47 = mC47;
        this.mC48 = mC48;
        this.mC48K = mC48K;
        this.mC49 = mC49;
        this.mC49K = mC49K;
        this.mID = mID;
        this.mIDCHET = mIDCHET;
    }

    public String getmC43() {
        return mC43;
    }

    public void setmC43(String mC43) {
        this.mC43 = mC43;
    }

    public Long getmC44() {
        return mC44;
    }

    public void setmC44(Long mC44) {
        this.mC44 = mC44;
    }

    public String getmC45N() {
        return mC45N;
    }

    public void setmC45N(String mC45N) {
        this.mC45N = mC45N;
    }

    public String getmC45T() {
        return mC45T;
    }

    public void setmC45T(String mC45T) {
        this.mC45T = mC45T;
    }

    public String getmC46N() {
        return mC46N;
    }

    public void setmC46N(String mC46N) {
        this.mC46N = mC46N;
    }

    public String getmC46T() {
        return mC46T;
    }

    public void setmC46T(String mC46T) {
        this.mC46T = mC46T;
    }

    public Integer getmC47() {
        return mC47;
    }

    public void setmC47(Integer mC47) {
        this.mC47 = mC47;
    }

    public Long getmC48() {
        return mC48;
    }

    public void setmC48(Long mC48) {
        this.mC48 = mC48;
    }

    public String getmC48K() {
        return mC48K;
    }

    public void setmC48K(String mC48K) {
        this.mC48K = mC48K;
    }

    public Integer getmC49() {
        return mC49;
    }

    public void setmC49(Integer mC49) {
        this.mC49 = mC49;
    }

    public String getmC49K() {
        return mC49K;
    }

    public void setmC49K(String mC49K) {
        this.mC49K = mC49K;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmIDCHET() {
        return mIDCHET;
    }

    public void setmIDCHET(String mIDCHET) {
        this.mIDCHET = mIDCHET;
    }
}