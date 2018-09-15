
package com.uyenpham.censusapplication.models.user;

import com.google.gson.annotations.SerializedName;

public class ProfileDTO {

    @SerializedName("Huyen")
    private DistrictDTO mHuyen;
    @SerializedName("MaHuyen")
    private String mMaHuyen;
    @SerializedName("MaNSD")
    private String mMaNSD;
    @SerializedName("MaQuyen")
    private Long mMaQuyen;
    @SerializedName("MaTinh")
    private String mMaTinh;
    @SerializedName("MaXa")
    private String mMaXa;
    @SerializedName("TenNSD")
    private String mTenNSD;
    @SerializedName("Tinh")
    private ProvinceDTO mTinh;
    @SerializedName("Xa")
    private StationDTO mXa;

    public ProfileDTO() {
    }

    public ProfileDTO(DistrictDTO mHuyen, String mMaHuyen, String mMaNSD, Long mMaQuyen, String
            mMaTinh, String mMaXa, String mTenNSD, ProvinceDTO mTinh, StationDTO mXa) {
        this.mHuyen = mHuyen;
        this.mMaHuyen = mMaHuyen;
        this.mMaNSD = mMaNSD;
        this.mMaQuyen = mMaQuyen;
        this.mMaTinh = mMaTinh;
        this.mMaXa = mMaXa;
        this.mTenNSD = mTenNSD;
        this.mTinh = mTinh;
        this.mXa = mXa;
    }

    public DistrictDTO getHuyen() {
        return mHuyen;
    }

    public void setHuyen(DistrictDTO huyen) {
        mHuyen = huyen;
    }

    public String getMaHuyen() {
        return mMaHuyen;
    }

    public void setMaHuyen(String maHuyen) {
        mMaHuyen = maHuyen;
    }

    public String getMaNSD() {
        return mMaNSD;
    }

    public void setMaNSD(String maNSD) {
        mMaNSD = maNSD;
    }

    public Long getMaQuyen() {
        return mMaQuyen;
    }

    public void setMaQuyen(Long maQuyen) {
        mMaQuyen = maQuyen;
    }

    public String getMaTinh() {
        return mMaTinh;
    }

    public void setMaTinh(String maTinh) {
        mMaTinh = maTinh;
    }

    public String getMaXa() {
        return mMaXa;
    }

    public void setMaXa(String maXa) {
        mMaXa = maXa;
    }

    public String getTenNSD() {
        return mTenNSD;
    }

    public void setTenNSD(String tenNSD) {
        mTenNSD = tenNSD;
    }

    public ProvinceDTO getTinh() {
        return mTinh;
    }

    public void setTinh(ProvinceDTO tinh) {
        mTinh = tinh;
    }

    public StationDTO getXa() {
        return mXa;
    }

    public void setXa(StationDTO xa) {
        mXa = xa;
    }

}
