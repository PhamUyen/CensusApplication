
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class WomanDTO {
    public static final String ID_HO = "ID";
    public static final String ID_WOMAN = "IDTV";

    @SerializedName("C34")
    private Long mC34;
    @SerializedName("C35A")
    private Long mC35A;
    @SerializedName("C35B")
    private Long mC35B;
    @SerializedName("C36A")
    private Long mC36A;
    @SerializedName("C36B")
    private Long mC36B;
    @SerializedName("C37A")
    private Long mC37A;
    @SerializedName("C37B")
    private Long mC37B;
    @SerializedName("C38N")
    private String mC38N;
    @SerializedName("C38T")
    private String mC38T;
    @SerializedName("C39A")
    private Long mC39A;
    @SerializedName("C39B")
    private Long mC39B;
    @SerializedName("C40A")
    private Long mC40A;
    @SerializedName("C40B")
    private Long mC40B;
    @SerializedName("C41")
    private Long mC41;
    @SerializedName("C41K")
    private String mC41K;
    @SerializedName("ID")
    private String mID;
    @SerializedName("IDTV")
    private String mIDTV;
    @SerializedName("TenTV")
    private String mTenTV;

    public WomanDTO() {
    }

    public WomanDTO(String mID) {
        this.mID = mID;
    }

    public WomanDTO(Long mC34, Long mC35A, Long mC35B, Long mC36A, Long mC36B, Long mC37A, Long
            mC37B, String mC38N, String mC38T, Long mC39A, Long mC39B, Long mC40A, Long mC40B,
                    Long mC41, String mC41K, String mID, String mIDTV, String mTenTV) {
        this.mC34 = mC34;
        this.mC35A = mC35A;
        this.mC35B = mC35B;
        this.mC36A = mC36A;
        this.mC36B = mC36B;
        this.mC37A = mC37A;
        this.mC37B = mC37B;
        this.mC38N = mC38N;
        this.mC38T = mC38T;
        this.mC39A = mC39A;
        this.mC39B = mC39B;
        this.mC40A = mC40A;
        this.mC40B = mC40B;
        this.mC41 = mC41;
        this.mC41K = mC41K;
        this.mID = mID;
        this.mIDTV = mIDTV;
        this.mTenTV = mTenTV;
    }

    public Long getC34() {
        return mC34;
    }

    public void setC34(Long c34) {
        mC34 = c34;
    }

    public Long getC35A() {
        return mC35A;
    }

    public void setC35A(Long c35A) {
        mC35A = c35A;
    }

    public Long getC35B() {
        return mC35B;
    }

    public void setC35B(Long c35B) {
        mC35B = c35B;
    }

    public Long getC36A() {
        return mC36A;
    }

    public void setC36A(Long c36A) {
        mC36A = c36A;
    }

    public Long getC36B() {
        return mC36B;
    }

    public void setC36B(Long c36B) {
        mC36B = c36B;
    }

    public Long getC37A() {
        return mC37A;
    }

    public void setC37A(Long c37A) {
        mC37A = c37A;
    }

    public Long getC37B() {
        return mC37B;
    }

    public void setC37B(Long c37B) {
        mC37B = c37B;
    }

    public String getC38N() {
        return mC38N;
    }

    public void setC38N(String c38N) {
        mC38N = c38N;
    }

    public String getC38T() {
        return mC38T;
    }

    public void setC38T(String c38T) {
        mC38T = c38T;
    }

    public Long getC39A() {
        return mC39A;
    }

    public void setC39A(Long c39A) {
        mC39A = c39A;
    }

    public Long getC39B() {
        return mC39B;
    }

    public void setC39B(Long c39B) {
        mC39B = c39B;
    }

    public Long getC40A() {
        return mC40A;
    }

    public void setC40A(Long c40A) {
        mC40A = c40A;
    }

    public Long getC40B() {
        return mC40B;
    }

    public void setC40B(Long c40B) {
        mC40B = c40B;
    }

    public Long getC41() {
        return mC41;
    }

    public void setC41(Long c41) {
        mC41 = c41;
    }

    public String getC41K() {
        return mC41K;
    }

    public void setC41K(String c41K) {
        mC41K = c41K;
    }

    public String getID() {
        return mID;
    }

    public void setID(String iD) {
        mID = iD;
    }

    public String getIDTV() {
        return mIDTV;
    }

    public void setIDTV(String iDTV) {
        mIDTV = iDTV;
    }

    public String getTenTV() {
        return mTenTV;
    }

    public void setTenTV(String tenTV) {
        mTenTV = tenTV;
    }
    public  boolean set(String fieldName, Object fieldValue) {
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(this, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }
}
