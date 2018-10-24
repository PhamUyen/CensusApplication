
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Field;

@DatabaseTable(tableName = "PeopleDTO")
public class PeopleDTO {
    public static final String ID_HO = "IDHO";
    @SerializedName("HOSO")
    @DatabaseField(columnName = "HOSO")
    private String mHOSO;
    @SerializedName("ID")
    @DatabaseField(columnName = "ID")
    private String mID;
    @SerializedName("IDHO")
    @DatabaseField(columnName = "IDHO",id = true)
    private String mIDHO;
    @SerializedName("Q2A")
    @DatabaseField(columnName = "Q2A")
    private Integer mQ2A;
    @SerializedName("Q3A")
    @DatabaseField(columnName = "Q3A")
    private Integer mQ3A;
    @SerializedName("Q4A")
    @DatabaseField(columnName = "Q4A")
    private Integer mQ4A;
    @SerializedName("Q6")
    @DatabaseField(columnName = "Q6")
    private Integer mQ6;
    public PeopleDTO() {
    }

    public PeopleDTO(String mHOSO, String mIDHO) {
        this.mHOSO = mHOSO;
        this.mIDHO = mIDHO;
    }

    public PeopleDTO(String mHOSO, String mID, String mIDHO, Integer mQ2A, Integer mQ3A, Integer
            mQ4A, Integer mQ6) {
        this.mHOSO = mHOSO;
        this.mID = mID;
        this.mIDHO = mIDHO;
        this.mQ2A = mQ2A;
        this.mQ3A = mQ3A;
        this.mQ4A = mQ4A;
        this.mQ6 = mQ6;
    }

    public static String getIdHo() {
        return ID_HO;
    }

    public String getmHOSO() {
        return mHOSO;
    }

    public void setmHOSO(String mHOSO) {
        this.mHOSO = mHOSO;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmIDHO() {
        return mIDHO;
    }

    public void setmIDHO(String mIDHO) {
        this.mIDHO = mIDHO;
    }

    public Integer getmQ2A() {
        return mQ2A;
    }

    public void setmQ2A(Integer mQ2A) {
        this.mQ2A = mQ2A;
    }

    public Integer getmQ3A() {
        return mQ3A;
    }

    public void setmQ3A(Integer mQ3A) {
        this.mQ3A = mQ3A;
    }

    public Integer getmQ4A() {
        return mQ4A;
    }

    public void setmQ4A(Integer mQ4A) {
        this.mQ4A = mQ4A;
    }

    public Integer getmQ6() {
        return mQ6;
    }

    public void setmQ6(Integer mQ6) {
        this.mQ6 = mQ6;
    }

    public String getHOSO() {
        return mHOSO;
    }

    public void setHOSO(String hOSO) {
        mHOSO = hOSO;
    }

    public String getID() {
        return mID;
    }

    public void setID(String iD) {
        mID = iD;
    }

    public String getIDHO() {
        return mIDHO;
    }

    public void setIDHO(String iDHO) {
        mIDHO = iDHO;
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
    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof PeopleDTO){
            PeopleDTO ptr = (PeopleDTO) v;
            retVal = ptr.mID == this.mID;
        }

        return retVal;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
