
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "DeadDTO")
public class DeadDTO {
    public static final String ID_HO = "ID";
    public static final String ID_DEAD = "IDCHET";
    @SerializedName("C43")
    @DatabaseField(columnName = "C43")
    private String mC43;
    @SerializedName("C44")
    @DatabaseField(columnName = "C44")
    private Integer mC44;
    @SerializedName("C45N")
    @DatabaseField(columnName = "C45N")
    private Integer mC45N;
    @SerializedName("C45T")
    @DatabaseField(columnName = "C45T")
    private Integer mC45T;
    @SerializedName("C46N")
    @DatabaseField(columnName = "C46N")
    private Integer mC46N;
    @SerializedName("C46T")
    @DatabaseField(columnName = "C46T")
    private Integer mC46T;
    @SerializedName("C47")
    @DatabaseField(columnName = "C47")
    private Integer mC47;
    @SerializedName("C48")
    @DatabaseField(columnName = "C48")
    private Integer mC48;
    @SerializedName("C48K")
    @DatabaseField(columnName = "C48K")
    private String mC48K;
    @SerializedName("C49")
    @DatabaseField(columnName = "C49")
    private Integer mC49;
    @SerializedName("C49K")
    @DatabaseField(columnName = "C49K")
    private String mC49K;
    @SerializedName("ID")
    @DatabaseField(columnName = "ID")
    private String mID;
    @SerializedName("IDCHET")
    @DatabaseField(columnName = "IDCHET", id = true)
    private String mIDCHET;

    public DeadDTO() {
    }

    public DeadDTO(String mC43, String mIDCHET) {
        this.mC43 = mC43;
        this.mIDCHET = mIDCHET;
    }

    public DeadDTO(String mID) {
        this.mID = mID;
    }

    public DeadDTO(String mC43, Integer mC44, Integer mC45N, Integer mC45T, Integer mC46N, Integer
            mC46T, Integer mC47, Integer mC48, String mC48K, Integer mC49, String mC49K, String mID,
                   String mIDCHET) {
        this.mC43 = mC43;
        this.mC44 = mC44;
        this.mC45N = mC45N;
        this.mC45T = mC45T;
        this.mC46N = mC46N;
        this.mC46T = mC46T;
        this.mC47 = mC47;
        this.mC48 = mC48;
        this.mC48K = mC48K;
        this.mC49 = mC49;
        this.mC49K = mC49K;
        this.mID = mID;
        this.mIDCHET = mIDCHET;
    }

    public String getmC43() {
        return mC43;
    }

    public void setmC43(String mC43) {
        this.mC43 = mC43;
    }

    public Integer getmC44() {
        return mC44;
    }

    public void setmC44(Integer mC44) {
        this.mC44 = mC44;
    }

    public Integer getmC45N() {
        return mC45N;
    }

    public void setmC45N(Integer mC45N) {
        this.mC45N = mC45N;
    }

    public Integer getmC45T() {
        return mC45T;
    }

    public void setmC45T(Integer mC45T) {
        this.mC45T = mC45T;
    }

    public Integer getmC46N() {
        return mC46N;
    }

    public void setmC46N(Integer mC46N) {
        this.mC46N = mC46N;
    }

    public Integer getmC46T() {
        return mC46T;
    }

    public void setmC46T(Integer mC46T) {
        this.mC46T = mC46T;
    }

    public Integer getmC47() {
        return mC47;
    }

    public void setmC47(Integer mC47) {
        this.mC47 = mC47;
    }

    public Integer getmC48() {
        return mC48;
    }

    public void setmC48(Integer mC48) {
        this.mC48 = mC48;
    }

    public String getmC48K() {
        return mC48K;
    }

    public void setmC48K(String mC48K) {
        this.mC48K = mC48K;
    }

    public Integer getmC49() {
        return mC49;
    }

    public void setmC49(Integer mC49) {
        this.mC49 = mC49;
    }

    public String getmC49K() {
        return mC49K;
    }

    public void setmC49K(String mC49K) {
        this.mC49K = mC49K;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmIDCHET() {
        return mIDCHET;
    }

    public void setmIDCHET(String mIDCHET) {
        this.mIDCHET = mIDCHET;

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
