package com.uyenpham.censusapplication.models.locality;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "LocalityDTO")
public class LocalityDTO {
    public static final String ID_USER= "user";
    public static final String ID_LOCALITY = "IDDB";
    @SerializedName("CapDuyet")
    @DatabaseField(columnName = "mCapDuyet")
    private String mCapDuyet;
    @SerializedName("DIABANDACTHU")
    @DatabaseField (columnName = "mDIABANDACTHU")
    private Long mDIABANDACTHU;
    @SerializedName("DIABANMAU")
    @DatabaseField (columnName = "mDIABANMAU")
    private Long mDIABANMAU;
    @SerializedName("GHICHU")
    @DatabaseField (columnName = "mGHICHU")
    private String mGHICHU;
    @SerializedName("IDDB")
    @DatabaseField (columnName = "IDDB",id = true)
    private String mIDDB;
    @SerializedName("MADIABAN")
    private String mMADIABAN;
    @DatabaseField (columnName = "MAHUYEN")
    @SerializedName("MAHUYEN")
    private String mMAHUYEN;
    @SerializedName("MATHON_HIENTHI")
    @DatabaseField (columnName = "MATHON_HIENTHI")
    private String mMATHONHIENTHI;
    @SerializedName("MATINH")
    @DatabaseField (columnName = "MATINH")
    private String mMATINH;
    @SerializedName("MAXA")
    @DatabaseField (columnName = "MAXA")
    private String mMAXA;
    @SerializedName("SODIENTHOAI")
    @DatabaseField (columnName = "SODIENTHOAI")
    private String mSODIENTHOAI;
    @SerializedName("SOHO")
    @DatabaseField (columnName = "SOHO")
    private Long mSOHO;
    @SerializedName("TENDIABAN")
    @DatabaseField (columnName = "TENDIABAN")
    private String mTENDIABAN;
    @SerializedName("TTNT")
    @DatabaseField (columnName = "TTNT")
    private Long mTTNT;
    @SerializedName("Trangthai")
    @DatabaseField (columnName = "Trangthai")
    private Long mTrangthai;
    @DatabaseField (columnName = "user")
    private String user;

    public String getUser() {
        return user;
    }

    public LocalityDTO() {
    }

    public LocalityDTO(String mCapDuyet, Long mDIABANDACTHU, Long mDIABANMAU, String mGHICHU,
                       String mIDDB, String mMADIABAN, String mMAHUYEN, String mMATHONHIENTHI,
                       String mMATINH, String mMAXA, String mSODIENTHOAI, Long mSOHO, String
                               mTENDIABAN, Long mTTNT, Long mTrangthai, String user) {
        this.mCapDuyet = mCapDuyet;
        this.mDIABANDACTHU = mDIABANDACTHU;
        this.mDIABANMAU = mDIABANMAU;
        this.mGHICHU = mGHICHU;
        this.mIDDB = mIDDB;
        this.mMADIABAN = mMADIABAN;
        this.mMAHUYEN = mMAHUYEN;
        this.mMATHONHIENTHI = mMATHONHIENTHI;
        this.mMATINH = mMATINH;
        this.mMAXA = mMAXA;
        this.mSODIENTHOAI = mSODIENTHOAI;
        this.mSOHO = mSOHO;
        this.mTENDIABAN = mTENDIABAN;
        this.mTTNT = mTTNT;
        this.mTrangthai = mTrangthai;
        this.user = user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Object getCapDuyet() {
        return mCapDuyet;
    }

    public void setCapDuyet(String capDuyet) {
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

    public void setSODIENTHOAI(String sODIENTHOAI) {
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
