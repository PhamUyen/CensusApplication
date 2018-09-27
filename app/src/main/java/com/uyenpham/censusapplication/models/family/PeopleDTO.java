
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

public class PeopleDTO {
    public static final String ID_HO = "IDHO";
    @SerializedName("HOSO")
    private String mHOSO;
    @SerializedName("ID")
    private String mID;
    @SerializedName("IDHO")
    private String mIDHO;
    @SerializedName("Q2")
    private Integer mQ2;
    @SerializedName("Q3")
    private Integer mQ3;
    @SerializedName("Q4")
    private Integer mQ4;
    @SerializedName("Q5")
    private Integer mQ5;
    @SerializedName("Q61")
    private Integer mQ61;
    @SerializedName("Q62")
    private Integer mQ62;
    @SerializedName("Q63")
    private Integer mQ63;
    @SerializedName("Q64")
    private Integer mQ64;
    @SerializedName("Q65")
    private Integer mQ65;
    @SerializedName("Q66")
    private Integer mQ66;
    @SerializedName("Q7")
    private Integer mQ7;
    @SerializedName("Q8")
    private Integer mQ8;
    @SerializedName("Q9")
    private Integer mQ9;

    public PeopleDTO() {
    }

    public PeopleDTO(String mHOSO, String mID, String mIDHO, Integer mQ2, Integer mQ3, Integer
            mQ4, Integer mQ5, Integer mQ61, Integer mQ62, Integer mQ63, Integer mQ64, Integer
            mQ65, Integer mQ66, Integer mQ7, Integer mQ8, Integer mQ9) {
        this.mHOSO = mHOSO;
        this.mID = mID;
        this.mIDHO = mIDHO;
        this.mQ2 = mQ2;
        this.mQ3 = mQ3;
        this.mQ4 = mQ4;
        this.mQ5 = mQ5;
        this.mQ61 = mQ61;
        this.mQ62 = mQ62;
        this.mQ63 = mQ63;
        this.mQ64 = mQ64;
        this.mQ65 = mQ65;
        this.mQ66 = mQ66;
        this.mQ7 = mQ7;
        this.mQ8 = mQ8;
        this.mQ9 = mQ9;
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

    public Integer getQ2() {
        return mQ2;
    }

    public void setQ2(Integer q2) {
        mQ2 = q2;
    }

    public Integer getQ3() {
        return mQ3;
    }

    public void setQ3(Integer q3) {
        mQ3 = q3;
    }

    public Integer getQ4() {
        return mQ4;
    }

    public void setQ4(Integer q4) {
        mQ4 = q4;
    }

    public Integer getQ5() {
        return mQ5;
    }

    public void setQ5(Integer q5) {
        mQ5 = q5;
    }

    public Integer getQ61() {
        return mQ61;
    }

    public void setQ61(Integer q61) {
        mQ61 = q61;
    }

    public Integer getQ62() {
        return mQ62;
    }

    public void setQ62(Integer q62) {
        mQ62 = q62;
    }

    public Integer getQ63() {
        return mQ63;
    }

    public void setQ63(Integer q63) {
        mQ63 = q63;
    }

    public Integer getQ64() {
        return mQ64;
    }

    public void setQ64(Integer q64) {
        mQ64 = q64;
    }

    public Integer getQ65() {
        return mQ65;
    }

    public void setQ65(Integer q65) {
        mQ65 = q65;
    }

    public Integer getQ66() {
        return mQ66;
    }

    public void setQ66(Integer q66) {
        mQ66 = q66;
    }

    public Integer getQ7() {
        return mQ7;
    }

    public void setQ7(Integer q7) {
        mQ7 = q7;
    }

    public Integer getQ8() {
        return mQ8;
    }

    public void setQ8(Integer q8) {
        mQ8 = q8;
    }

    public Integer getQ9() {
        return mQ9;
    }

    public void setQ9(Integer q9) {
        mQ9 = q9;
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
