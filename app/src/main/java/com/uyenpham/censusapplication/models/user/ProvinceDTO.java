
package com.uyenpham.censusapplication.models.user;

import com.google.gson.annotations.SerializedName;

public class ProvinceDTO {

    @SerializedName("CapDuyet")
    private Long mCapDuyet;
    @SerializedName("LOAIDMHC")
    private Long mLOAIDMHC;
    @SerializedName("MATINH")
    private String mMATINH;
    @SerializedName("MaVung")
    private Long mMaVung;
    @SerializedName("STT")
    private Long mSTT;
    @SerializedName("TENTINH")
    private String mTENTINH;

    public ProvinceDTO() {
    }

    public ProvinceDTO(Long mCapDuyet, Long mLOAIDMHC, String mMATINH, Long mMaVung, Long mSTT,
                       String mTENTINH) {
        this.mCapDuyet = mCapDuyet;
        this.mLOAIDMHC = mLOAIDMHC;
        this.mMATINH = mMATINH;
        this.mMaVung = mMaVung;
        this.mSTT = mSTT;
        this.mTENTINH = mTENTINH;
    }

    public Long getCapDuyet() {
        return mCapDuyet;
    }

    public void setCapDuyet(Long capDuyet) {
        mCapDuyet = capDuyet;
    }

    public Long getLOAIDMHC() {
        return mLOAIDMHC;
    }

    public void setLOAIDMHC(Long lOAIDMHC) {
        mLOAIDMHC = lOAIDMHC;
    }

    public String getMATINH() {
        return mMATINH;
    }

    public void setMATINH(String mATINH) {
        mMATINH = mATINH;
    }

    public Long getMaVung() {
        return mMaVung;
    }

    public void setMaVung(Long maVung) {
        mMaVung = maVung;
    }

    public Long getSTT() {
        return mSTT;
    }

    public void setSTT(Long sTT) {
        mSTT = sTT;
    }

    public String getTENTINH() {
        return mTENTINH;
    }

    public void setTENTINH(String tENTINH) {
        mTENTINH = tENTINH;
    }

}
