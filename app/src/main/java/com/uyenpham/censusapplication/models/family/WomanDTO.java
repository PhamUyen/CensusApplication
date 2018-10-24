
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "WomanDTO")
public class WomanDTO {
    public static final String ID_HO = "ID";
    public static final String ID_WOMAN = "IDTV";

    @SerializedName("C34")
    @DatabaseField(columnName = "C34")
    private Integer mC34;
    @SerializedName("C35A")
    @DatabaseField(columnName = "C35A")
    private Integer mC35A;
    @SerializedName("C35B")
    @DatabaseField(columnName = "C35B")
    private Integer mC35B;
    @SerializedName("C36A")
    @DatabaseField(columnName = "C36A")
    private Integer mC36A;
    @SerializedName("C36B")
    @DatabaseField(columnName = "C36B")
    private Integer mC36B;
    @SerializedName("C37A")
    @DatabaseField(columnName = "C37A")
    private Integer mC37A;
    @SerializedName("C37B")
    @DatabaseField(columnName = "C37B")
    private Integer mC37B;
    @SerializedName("C38N")
    @DatabaseField(columnName = "C38N")
    private Integer mC38N;
    @SerializedName("C38T")
    @DatabaseField(columnName = "C38T")
    private Integer mC38T;
    @SerializedName("C39A")
    @DatabaseField(columnName = "C39A")
    private Integer mC39A;
    @SerializedName("C39B")
    @DatabaseField(columnName = "C39B")
    private Integer mC39B;
    @SerializedName("C40A")
    @DatabaseField(columnName = "C40A")
    private Integer mC40A;
    @SerializedName("C40B")
    @DatabaseField(columnName = "C40B")
    private Integer mC40B;
    @SerializedName("C41")
    @DatabaseField(columnName = "C41")
    private Integer mC41;
    @SerializedName("C41K")
    @DatabaseField(columnName = "C41K")
    private String mC41K;
    @SerializedName("ID")
    @DatabaseField(columnName = "ID")
    private String mID;
    @SerializedName("IDTV")
    @DatabaseField(columnName = "IDTV", id = true)
    private String mIDTV;
    @SerializedName("TenTV")
    @DatabaseField(columnName = "TenTV")
    private String mTenTV;

    public WomanDTO() {
    }

    public WomanDTO(String mID) {
        this.mID = mID;
    }

    public WomanDTO(String mIDTV, String mTenTV) {
        this.mIDTV = mIDTV;
        this.mTenTV = mTenTV;
    }

    public WomanDTO(Integer mC34, Integer mC35A, Integer mC35B, Integer mC36A, Integer mC36B, Integer mC37A, Integer
            mC37B, Integer mC38N, Integer mC38T, Integer mC39A, Integer mC39B, Integer mC40A, Integer mC40B,
                    Integer mC41, String mC41K, String mID, String mIDTV, String mTenTV) {
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

    public Integer getC34() {
        return mC34;
    }

    public void setC34(Integer c34) {
        mC34 = c34;
    }

    public Integer getC35A() {
        return mC35A;
    }

    public void setC35A(Integer c35A) {
        mC35A = c35A;
    }

    public Integer getC35B() {
        return mC35B;
    }

    public void setC35B(Integer c35B) {
        mC35B = c35B;
    }

    public Integer getC36A() {
        return mC36A;
    }

    public void setC36A(Integer c36A) {
        mC36A = c36A;
    }

    public Integer getC36B() {
        return mC36B;
    }

    public void setC36B(Integer c36B) {
        mC36B = c36B;
    }

    public Integer getC37A() {
        return mC37A;
    }

    public void setC37A(Integer c37A) {
        mC37A = c37A;
    }

    public Integer getC37B() {
        return mC37B;
    }

    public void setC37B(Integer c37B) {
        mC37B = c37B;
    }

    public Integer getC38N() {
        return mC38N;
    }

    public void setC38N(Integer c38N) {
        mC38N = c38N;
    }

    public Integer getC38T() {
        return mC38T;
    }

    public void setC38T(Integer c38T) {
        mC38T = c38T;
    }

    public Integer getC39A() {
        return mC39A;
    }

    public void setC39A(Integer c39A) {
        mC39A = c39A;
    }

    public Integer getC39B() {
        return mC39B;
    }

    public void setC39B(Integer c39B) {
        mC39B = c39B;
    }

    public Integer getC40A() {
        return mC40A;
    }

    public void setC40A(Integer c40A) {
        mC40A = c40A;
    }

    public Integer getC40B() {
        return mC40B;
    }

    public void setC40B(Integer c40B) {
        mC40B = c40B;
    }

    public Integer getC41() {
        return mC41;
    }

    public void setC41(Integer c41) {
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
        fieldName = "m"+fieldName;
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
    public  Object get(String fieldName) {
        fieldName = "m"+fieldName;
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(this);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return null;
    }
}
