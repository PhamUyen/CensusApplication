
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FamilyDTO implements Serializable{

    @SerializedName("DIACHI")
    private String mDIACHI;
    @SerializedName("DIENTHOAI")
    private String mDIENTHOAI;
    @SerializedName("EMAIL")
    private String mEMAIL;
    @SerializedName("GHICHU")
    private Object mGHICHU;
    @SerializedName("HOSO")
    private String mHOSO;
    @SerializedName("IDDB")
    private String mIDDB;
    @SerializedName("IDHO")
    private String mIDHO;
    @SerializedName("Loaiphieu")
    private Long mLoaiphieu;
    @SerializedName("MADIABAN")
    private String mMADIABAN;
    @SerializedName("MAHUYEN")
    private String mMAHUYEN;
    @SerializedName("MATHON")
    private String mMATHON;
    @SerializedName("MATINH")
    private String mMATINH;
    @SerializedName("MAXA")
    private String mMAXA;
    @SerializedName("SONHANKHAU_DIEUTRA")
    private Object mSONHANKHAUDIEUTRA;
    @SerializedName("SONHANKHAU_HIEUCHINH")
    private Object mSONHANKHAUHIEUCHINH;
    @SerializedName("SONHANKHAU_LAPBK")
    private Long mSONHANKHAULAPBK;
    @SerializedName("SONU_DIEUTRA")
    private Object mSONUDIEUTRA;
    @SerializedName("SONU_HIEUCHINH")
    private Object mSONUHIEUCHINH;
    @SerializedName("SONU_LAPBK")
    private Long mSONULAPBK;
    @SerializedName("TENCHUHO")
    private String mTENCHUHO;
    @SerializedName("TINHTRANGHO")
    private Long mTINHTRANGHO;

    public FamilyDTO(String mDIACHI, String mIDDB, String mTENCHUHO, long
            mTINHTRANGHO) {
        this.mDIACHI = mDIACHI;
        this.mIDDB = mIDDB;
        this.mTENCHUHO = mTENCHUHO;
        this.mTINHTRANGHO = mTINHTRANGHO;
    }

    public String getDIACHI() {
        return mDIACHI;
    }

    public void setDIACHI(String dIACHI) {
        mDIACHI = dIACHI;
    }

    public String getDIENTHOAI() {
        return mDIENTHOAI;
    }

    public void setDIENTHOAI(String dIENTHOAI) {
        mDIENTHOAI = dIENTHOAI;
    }

    public String getEMAIL() {
        return mEMAIL;
    }

    public void setEMAIL(String eMAIL) {
        mEMAIL = eMAIL;
    }

    public Object getGHICHU() {
        return mGHICHU;
    }

    public void setGHICHU(Object gHICHU) {
        mGHICHU = gHICHU;
    }

    public String getHOSO() {
        return mHOSO;
    }

    public void setHOSO(String hOSO) {
        mHOSO = hOSO;
    }

    public String getIDDB() {
        return mIDDB;
    }

    public void setIDDB(String iDDB) {
        mIDDB = iDDB;
    }

    public String getIDHO() {
        return mIDHO;
    }

    public void setIDHO(String iDHO) {
        mIDHO = iDHO;
    }

    public Long getLoaiphieu() {
        return mLoaiphieu;
    }

    public void setLoaiphieu(Long loaiphieu) {
        mLoaiphieu = loaiphieu;
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

    public String getMATHON() {
        return mMATHON;
    }

    public void setMATHON(String mATHON) {
        mMATHON = mATHON;
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

    public Object getSONHANKHAUDIEUTRA() {
        return mSONHANKHAUDIEUTRA;
    }

    public void setSONHANKHAUDIEUTRA(Object sONHANKHAUDIEUTRA) {
        mSONHANKHAUDIEUTRA = sONHANKHAUDIEUTRA;
    }

    public Object getSONHANKHAUHIEUCHINH() {
        return mSONHANKHAUHIEUCHINH;
    }

    public void setSONHANKHAUHIEUCHINH(Object sONHANKHAUHIEUCHINH) {
        mSONHANKHAUHIEUCHINH = sONHANKHAUHIEUCHINH;
    }

    public Long getSONHANKHAULAPBK() {
        return mSONHANKHAULAPBK;
    }

    public void setSONHANKHAULAPBK(Long sONHANKHAULAPBK) {
        mSONHANKHAULAPBK = sONHANKHAULAPBK;
    }

    public Object getSONUDIEUTRA() {
        return mSONUDIEUTRA;
    }

    public void setSONUDIEUTRA(Object sONUDIEUTRA) {
        mSONUDIEUTRA = sONUDIEUTRA;
    }

    public Object getSONUHIEUCHINH() {
        return mSONUHIEUCHINH;
    }

    public void setSONUHIEUCHINH(Object sONUHIEUCHINH) {
        mSONUHIEUCHINH = sONUHIEUCHINH;
    }

    public Long getSONULAPBK() {
        return mSONULAPBK;
    }

    public void setSONULAPBK(Long sONULAPBK) {
        mSONULAPBK = sONULAPBK;
    }

    public String getTENCHUHO() {
        return mTENCHUHO;
    }

    public void setTENCHUHO(String tENCHUHO) {
        mTENCHUHO = tENCHUHO;
    }

    public Long getTINHTRANGHO() {
        return mTINHTRANGHO;
    }

    public void setTINHTRANGHO(Long tINHTRANGHO) {
        mTINHTRANGHO = tINHTRANGHO;
    }

}
