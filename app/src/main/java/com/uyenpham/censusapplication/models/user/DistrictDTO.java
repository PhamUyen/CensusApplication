
package com.uyenpham.censusapplication.models.user;

import com.google.gson.annotations.SerializedName;
public class DistrictDTO {

    @SerializedName("CapDuyet")
    private Object mCapDuyet;
    @SerializedName("LOAIDMHC")
    private Long mLOAIDMHC;
    @SerializedName("MAHUYEN")
    private String mMAHUYEN;
    @SerializedName("MATINH")
    private String mMATINH;
    @SerializedName("TENHUYEN")
    private String mTENHUYEN;

    public DistrictDTO() {
    }

    public DistrictDTO(Object mCapDuyet, Long mLOAIDMHC, String mMAHUYEN, String mMATINH, String
            mTENHUYEN) {
        this.mCapDuyet = mCapDuyet;
        this.mLOAIDMHC = mLOAIDMHC;
        this.mMAHUYEN = mMAHUYEN;
        this.mMATINH = mMATINH;
        this.mTENHUYEN = mTENHUYEN;
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

    public String getTENHUYEN() {
        return mTENHUYEN;
    }

    public void setTENHUYEN(String tENHUYEN) {
        mTENHUYEN = tENHUYEN;
    }

}
