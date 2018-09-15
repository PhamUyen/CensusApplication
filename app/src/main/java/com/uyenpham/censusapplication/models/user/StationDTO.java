
package com.uyenpham.censusapplication.models.user;

import com.google.gson.annotations.SerializedName;

public class StationDTO {

    @SerializedName("CapDuyet")
    private Object mCapDuyet;
    @SerializedName("LOAIDMHC")
    private Long mLOAIDMHC;
    @SerializedName("Loaixa")
    private Object mLoaixa;
    @SerializedName("MAHUYEN")
    private String mMAHUYEN;
    @SerializedName("MATINH")
    private String mMATINH;
    @SerializedName("MAXA")
    private String mMAXA;
    @SerializedName("TENXA")
    private String mTENXA;
    @SerializedName("TTNT")
    private Long mTTNT;

    public StationDTO(Object mCapDuyet, Long mLOAIDMHC, Object mLoaixa, String mMAHUYEN, String
            mMATINH, String mMAXA, String mTENXA, Long mTTNT) {
        this.mCapDuyet = mCapDuyet;
        this.mLOAIDMHC = mLOAIDMHC;
        this.mLoaixa = mLoaixa;
        this.mMAHUYEN = mMAHUYEN;
        this.mMATINH = mMATINH;
        this.mMAXA = mMAXA;
        this.mTENXA = mTENXA;
        this.mTTNT = mTTNT;
    }

    public StationDTO() {
    }

    public Object getCapDuyet() {
        return mCapDuyet;
    }

    public void setCapDuyet(Object capDuyet) {
        mCapDuyet = capDuyet;
    }

    public Long getLOAIDMHC() {
        return mLOAIDMHC;
    }

    public void setLOAIDMHC(Long lOAIDMHC) {
        mLOAIDMHC = lOAIDMHC;
    }

    public Object getLoaixa() {
        return mLoaixa;
    }

    public void setLoaixa(Object loaixa) {
        mLoaixa = loaixa;
    }

    public String getMAHUYEN() {
        return mMAHUYEN;
    }

    public void setMAHUYEN(String mAHUYEN) {
        mMAHUYEN = mAHUYEN;
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

    public String getTENXA() {
        return mTENXA;
    }

    public void setTENXA(String tENXA) {
        mTENXA = tENXA;
    }

    public Long getTTNT() {
        return mTTNT;
    }

    public void setTTNT(Long tTNT) {
        mTTNT = tTNT;
    }

}
