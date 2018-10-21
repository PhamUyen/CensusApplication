
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;
import com.uyenpham.censusapplication.utils.Logger;

import java.io.Serializable;
import java.lang.reflect.Field;

public class FamilyDTO implements Serializable{
    public static final String ID_FAMILY = "IDHO";

    @SerializedName("DIACHI")
    private String mDIACHI;
    @SerializedName("DIENTHOAI")
    private String mDIENTHOAI;
    @SerializedName("EMAIL")
    private String mEMAIL;
    @SerializedName("GHICHU")
    private String mGHICHU;
    @SerializedName("HOSO")
    private String mHOSO;
    @SerializedName("IDDB")
    private String mIDDB;
    @SerializedName("IDHO")
    private String mIDHO;
    @SerializedName("Loaiphieu")
    private Integer mLoaiphieu;
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
    private Integer mSONHANKHAUDIEUTRA;
    @SerializedName("SONHANKHAU_HIEUCHINH")
    private Integer mSONHANKHAUHIEUCHINH;
    @SerializedName("SONHANKHAU_LAPBK")
    private Integer mSONHANKHAULAPBK;
    @SerializedName("SONU_DIEUTRA")
    private Integer mSONUDIEUTRA;
    @SerializedName("SONU_HIEUCHINH")
    private Integer mSONUHIEUCHINH;
    @SerializedName("SONU_LAPBK")
    private Integer mSONULAPBK;
    @SerializedName("TENCHUHO")
    private String mTENCHUHO;
    @SerializedName("TINHTRANGHO")
    private Integer mTINHTRANGHO;

    public FamilyDTO() {
    }

    public FamilyDTO(String mDIACHI, String mIDDB, String mTENCHUHO, Integer
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

    public void setGHICHU(String gHICHU) {
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

    public Integer getLoaiphieu() {
        return mLoaiphieu;
    }

    public void setLoaiphieu(String loaiphieu) {
        mLoaiphieu = Integer.parseInt(loaiphieu);
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

    public Integer getSONHANKHAUDIEUTRA() {
        return mSONHANKHAUDIEUTRA;
    }

    public void setSONHANKHAUDIEUTRA(Integer sONHANKHAUDIEUTRA) {
        mSONHANKHAUDIEUTRA = sONHANKHAUDIEUTRA;
    }

    public Integer getSONHANKHAUHIEUCHINH() {
        return mSONHANKHAUHIEUCHINH;
    }

    public void setSONHANKHAUHIEUCHINH(Integer sONHANKHAUHIEUCHINH) {
        mSONHANKHAUHIEUCHINH = sONHANKHAUHIEUCHINH;
    }

    public Integer getSONHANKHAULAPBK() {
        return mSONHANKHAULAPBK;
    }

    public void setSONHANKHAULAPBK(Integer sONHANKHAULAPBK) {
        mSONHANKHAULAPBK = sONHANKHAULAPBK;
    }

    public Integer getSONUDIEUTRA() {
        return mSONUDIEUTRA;
    }

    public void setSONUDIEUTRA(Integer sONUDIEUTRA) {
        mSONUDIEUTRA = sONUDIEUTRA;
    }

    public Integer getSONUHIEUCHINH() {
        return mSONUHIEUCHINH;
    }

    public void setSONUHIEUCHINH(Integer sONUHIEUCHINH) {
        mSONUHIEUCHINH = sONUHIEUCHINH;
    }

    public Integer getSONULAPBK() {
        return mSONULAPBK;
    }

    public void setSONULAPBK(Integer sONULAPBK) {
        mSONULAPBK = sONULAPBK;
    }

    public String getTENCHUHO() {
        return mTENCHUHO;
    }

    public void setTENCHUHO(String tENCHUHO) {
        mTENCHUHO = tENCHUHO;
    }

    public Integer getTINHTRANGHO() {
        return mTINHTRANGHO;
    }

    public void setTINHTRANGHO(Integer tINHTRANGHO) {
        mTINHTRANGHO = tINHTRANGHO;
    }
    public  boolean set(String fieldName, Object fieldValue) {
        fieldName = fieldName.startsWith("m") ?fieldName : "m"+fieldName;
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                if(field.getType().equals(String.class)){
                    field.set(this, fieldValue);
                }else {
                    field.set(this, Integer.parseInt((String)fieldValue));
                }
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }
    public  Object get(String fieldName) {
        fieldName = fieldName.startsWith("m") ?fieldName : "m"+fieldName;
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(this);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (IllegalAccessException e) {
                Logger.e(e);
            }
        }
        return null;
    }
}
