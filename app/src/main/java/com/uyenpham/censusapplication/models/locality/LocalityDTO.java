package com.uyenpham.censusapplication.models.locality;

import com.google.gson.annotations.SerializedName;

public class LocalityDTO {
    @SerializedName("CapDuyet")
    private Object mCapDuyet;
    @SerializedName("DIABANDACTHU")
    private Long mDIABANDACTHU;
    @SerializedName("DIABANMAU")
    private Long mDIABANMAU;
    @SerializedName("GHICHU")
    private String mGHICHU;
    @SerializedName("IDDB")
    private String mIDDB;
    @SerializedName("MADIABAN")
    private String mMADIABAN;
    @SerializedName("MAHUYEN")
    private String mMAHUYEN;
    @SerializedName("MATHON_HIENTHI")
    private String mMATHONHIENTHI;
    @SerializedName("MATINH")
    private String mMATINH;
    @SerializedName("MAXA")
    private String mMAXA;
    @SerializedName("SODIENTHOAI")
    private Object mSODIENTHOAI;
    @SerializedName("SOHO")
    private Long mSOHO;
    @SerializedName("TENDIABAN")
    private String mTENDIABAN;
    @SerializedName("TTNT")
    private Long mTTNT;
    @SerializedName("Trangthai")
    private Long mTrangthai;

    public Object getCapDuyet() {
        return mCapDuyet;
    }

    public void setCapDuyet(Object capDuyet) {
        mCapDuyet = capDuyet;
    }

    public Long getDIABANDACTHU() {
        return mDIABANDACTHU;
    }

    public void setDIABANDACTHU(Long dIABANDACTHU) {
        mDIABANDACTHU = dIABANDACTHU;
    }

    public Long getDIABANMAU() {
        return mDIABANMAU;
    }

    public void setDIABANMAU(Long dIABANMAU) {
        mDIABANMAU = dIABANMAU;
    }

    public String getGHICHU() {
        return mGHICHU;
    }

    public void setGHICHU(String gHICHU) {
        mGHICHU = gHICHU;
    }

    public String getIDDB() {
        return mIDDB;
    }

    public void setIDDB(String iDDB) {
        mIDDB = iDDB;
    }

    public String getMADIABAN() {
        return mMADIABAN;
    }

    public void setMADIABAN(String mADIABAN) {
        mMADIABAN = mADIABAN;
    }

    public String getMAHUYEN() {
        return mMAHUYEN;
    }

    public void setMAHUYEN(String mAHUYEN) {
        mMAHUYEN = mAHUYEN;
    }

    public String getMATHONHIENTHI() {
        return mMATHONHIENTHI;
    }

    public void setMATHONHIENTHI(String mATHONHIENTHI) {
        mMATHONHIENTHI = mATHONHIENTHI;
    }

    public String getMATINH() {
        return mMATINH;
    }

    public void setMATINH(String mATINH) {
        mMATINH = mATINH;
    }

    public String getMAXA() {
        return mMAXA;
    }

    public void setMAXA(String mAXA) {
        mMAXA = mAXA;
    }

    public Object getSODIENTHOAI() {
        return mSODIENTHOAI;
    }

    public void setSODIENTHOAI(Object sODIENTHOAI) {
        mSODIENTHOAI = sODIENTHOAI;
    }

    public Long getSOHO() {
        return mSOHO;
    }

    public void setSOHO(Long sOHO) {
        mSOHO = sOHO;
    }

    public String getTENDIABAN() {
        return mTENDIABAN;
    }

    public void setTENDIABAN(String tENDIABAN) {
        mTENDIABAN = tENDIABAN;
    }

    public Long getTTNT() {
        return mTTNT;
    }

    public void setTTNT(Long tTNT) {
        mTTNT = tTNT;
    }

    public Long getTrangthai() {
        return mTrangthai;
    }

    public void setTrangthai(Long trangthai) {
        mTrangthai = trangthai;
    }
}
