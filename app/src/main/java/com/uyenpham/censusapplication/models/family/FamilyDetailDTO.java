
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "FamilyDetailDTO")
public class FamilyDetailDTO extends FamilyDTO{

    @SerializedName("C42")
    @DatabaseField(columnName = "C42")
    private Integer mC42;
    @SerializedName("C50")
    @DatabaseField(columnName = "C50")
    private Integer mC50;
    @SerializedName("C51")
    @DatabaseField(columnName = "C51")
    private Integer mC51;
    @SerializedName("C52A")
    @DatabaseField(columnName = "C52A")
    private Integer mC52A;
    @SerializedName("C52B")
    @DatabaseField(columnName = "C52B")
    private String mC52B;
    @SerializedName("C52C")
    @DatabaseField(columnName = "C52C")
    private String mC52C;
    @SerializedName("C53A")
    @DatabaseField(columnName = "C53A")
    private Integer mC53A;
    @SerializedName("C53B")
    @DatabaseField(columnName = "C53B")
    private Integer mC53B;
    @SerializedName("C54")
    @DatabaseField(columnName = "C54")
    private Integer mC54;
    @SerializedName("C55")
    @DatabaseField(columnName = "C55")
    private Integer mC55;
    @SerializedName("C56")
    @DatabaseField(columnName = "C56")
    private Integer mC56;
    @SerializedName("C57")
    @DatabaseField(columnName = "C57")
    private Integer mC57;
    @SerializedName("C58A")
    @DatabaseField(columnName = "C58A")
    private Integer mC58A;
    @SerializedName("C58B")
    @DatabaseField(columnName = "C58B")
    private String mC58B;
    @SerializedName("C59")
    @DatabaseField(columnName = "C59")
    private Integer mC59;
    @SerializedName("C60")
    @DatabaseField(columnName = "C60")
    private Integer mC60;
    @SerializedName("C60A")
    @DatabaseField(columnName = "C60A")
    private String mC60A;
    @SerializedName("C61")
    @DatabaseField(columnName = "C61")
    private Integer mC61;
    @SerializedName("C61A")
    @DatabaseField(columnName = "C61A")
    private String mC61A;
    @SerializedName("C62")
    @DatabaseField(columnName = "C62")
    private Integer mC62;
    @SerializedName("C62A")
    @DatabaseField(columnName = "C62A")
    private String mC62A;
    @SerializedName("C63")
    @DatabaseField(columnName = "C63")
    private Integer mC63;
    @SerializedName("C63A")
    @DatabaseField(columnName = "C63A")
    private String mC63A;
    @SerializedName("C64")
    @DatabaseField(columnName = "C64")
    private Integer mC64;
    @SerializedName("C65A")
    @DatabaseField(columnName = "C65A")
    private String mC65A;
    @SerializedName("C65B")
    @DatabaseField(columnName = "C65B")
    private String mC65B;
    @SerializedName("C65C")
    @DatabaseField(columnName = "C65C")
    private String mC65C;
    @SerializedName("C65D")
    @DatabaseField(columnName = "C65D")
    private String mC65D;
    @SerializedName("C65E")
    @DatabaseField(columnName = "C65E")
    private String mC65E;
    @SerializedName("C65F")
    @DatabaseField(columnName = "C65F")
    private String mC65F;
    @SerializedName("C65G")
    @DatabaseField(columnName = "C65G")
    private String mC65G;
    @SerializedName("C65H")
    @DatabaseField(columnName = "C65H")
    private String mC65H;
    @SerializedName("C65I")
    @DatabaseField(columnName = "C65I")
    private String mC65I;
    @SerializedName("C65J")
    @DatabaseField(columnName = "C65J")
    private String mC65J;
    @SerializedName("C65K")
    @DatabaseField(columnName = "C65K")
    private String mC65K;
    @SerializedName("C65L")
    @DatabaseField(columnName = "C65L")
    private String mC65L;
    @SerializedName("DIACHICHUHO")
    @DatabaseField(columnName = "DIACHICHUHO")
    private String mDIACHICHUHO;
    @SerializedName("ID")
    @DatabaseField(columnName = "ID")
    private String mID;
    @SerializedName("KT6")
    @DatabaseField(columnName = "KT6")
    private Integer mKT6;
    @SerializedName("NgayCapnhat")
    @DatabaseField(columnName = "NgayCapnhat")
    private String mNgayCapnhat;
    @SerializedName("NguoiCapnhat")
    @DatabaseField(columnName = "NguoiCapnhat")
    private String mNguoiCapnhat;
    @SerializedName("PHUONGPHAPDIEUTRA")
    @DatabaseField(columnName = "PHUONGPHAPDIEUTRA")
    private Long mPHUONGPHAPDIEUTRA;
    @SerializedName("THOIGIANBD")
    @DatabaseField(columnName = "THOIGIANBD")
    private String mTHOIGIANBD;
    @SerializedName("THOIGIANKT")
    @DatabaseField(columnName = "THOIGIANKT")
    private String mTHOIGIANKT;
    @SerializedName("TSKHAU")
    @DatabaseField(columnName = "TSKHAU")
    private Long mTSKHAU;
    @SerializedName("TSNAM")
    @DatabaseField(columnName = "TSNAM")
    private Long mTSNAM;
    @SerializedName("TSNU")
    @DatabaseField(columnName = "TSNU")
    private Long mTSNU;
    @SerializedName("TTNT")
    @DatabaseField(columnName = "TTNT")
    private Long mTTNT;
    @SerializedName("Trangthai")
    @DatabaseField(columnName = "Trangthai")
    private Integer mTrangthai;

    public FamilyDetailDTO() {
    }

    public FamilyDetailDTO(Integer mC42, Integer mC50, Integer mC51, Integer mC52A, String mC52B,
                           String mC52C, Integer mC53A, Integer mC53B, Integer mC54, Integer
                                   mC55, Integer mC56, Integer mC57, Integer mC58A, String mC58B,
                           Integer mC59, Integer mC60, String mC60A, Integer mC61, String mC61A,
                           Integer mC62, String mC62A, Integer mC63, String mC63A, Integer mC64,
                           String mC65A, String mC65B, String mC65C, String mC65D, String
                                   mC65E, String mC65F, String mC65G, String mC65H, String
                                   mC65I, String mC65J, String mC65K, String mC65L, String
                                   mDIACHICHUHO, String mDIENTHOAI, String mHOSO, String mID,
                           String mIDHO, Integer mKT6, Long mLoaiphieu, String mMADIABAN, String
                                   mMAHUYEN, String mMATHON, String mMATINH, String mMAXA, String
                                   mNgayCapnhat, String mNguoiCapnhat, Long mPHUONGPHAPDIEUTRA,
                           String mTENCHUHO, String mTHOIGIANBD, String mTHOIGIANKT, Long
                                   mTSKHAU, Long mTSNAM, Long mTSNU, Long mTTNT, Integer
                                   mTrangthai) {
        this.mC42 = mC42;
        this.mC50 = mC50;
        this.mC51 = mC51;
        this.mC52A = mC52A;
        this.mC52B = mC52B;
        this.mC52C = mC52C;
        this.mC53A = mC53A;
        this.mC53B = mC53B;
        this.mC54 = mC54;
        this.mC55 = mC55;
        this.mC56 = mC56;
        this.mC57 = mC57;
        this.mC58A = mC58A;
        this.mC58B = mC58B;
        this.mC59 = mC59;
        this.mC60 = mC60;
        this.mC60A = mC60A;
        this.mC61 = mC61;
        this.mC61A = mC61A;
        this.mC62 = mC62;
        this.mC62A = mC62A;
        this.mC63 = mC63;
        this.mC63A = mC63A;
        this.mC64 = mC64;
        this.mC65A = mC65A;
        this.mC65B = mC65B;
        this.mC65C = mC65C;
        this.mC65D = mC65D;
        this.mC65E = mC65E;
        this.mC65F = mC65F;
        this.mC65G = mC65G;
        this.mC65H = mC65H;
        this.mC65I = mC65I;
        this.mC65J = mC65J;
        this.mC65K = mC65K;
        this.mC65L = mC65L;
        this.mDIACHICHUHO = mDIACHICHUHO;
        this.mID = mID;
        this.mKT6 = mKT6;
        this.mNgayCapnhat = mNgayCapnhat;
        this.mNguoiCapnhat = mNguoiCapnhat;
        this.mPHUONGPHAPDIEUTRA = mPHUONGPHAPDIEUTRA;
        this.mTHOIGIANBD = mTHOIGIANBD;
        this.mTHOIGIANKT = mTHOIGIANKT;
        this.mTSKHAU = mTSKHAU;
        this.mTSNAM = mTSNAM;
        this.mTSNU = mTSNU;
        this.mTTNT = mTTNT;
        this.mTrangthai = mTrangthai;
    }

    public Integer getmC42() {
        return mC42;
    }

    public void setmC42(Integer mC42) {
        this.mC42 = mC42;
    }

    public Integer getmC50() {
        return mC50;
    }

    public void setmC50(Integer mC50) {
        this.mC50 = mC50;
    }

    public Integer getmC51() {
        return mC51;
    }

    public void setmC51(Integer mC51) {
        this.mC51 = mC51;
    }

    public Integer getmC52A() {
        return mC52A;
    }

    public void setmC52A(Integer mC52A) {
        this.mC52A = mC52A;
    }

    public String getmC52B() {
        return mC52B;
    }

    public void setmC52B(String mC52B) {
        this.mC52B = mC52B;
    }

    public String getmC52C() {
        return mC52C;
    }

    public void setmC52C(String mC52C) {
        this.mC52C = mC52C;
    }

    public Integer getmC53A() {
        return mC53A;
    }

    public void setmC53A(Integer mC53A) {
        this.mC53A = mC53A;
    }

    public Integer getmC53B() {
        return mC53B;
    }

    public void setmC53B(Integer mC53B) {
        this.mC53B = mC53B;
    }

    public Integer getmC54() {
        return mC54;
    }

    public void setmC54(Integer mC54) {
        this.mC54 = mC54;
    }

    public Integer getmC55() {
        return mC55;
    }

    public void setmC55(Integer mC55) {
        this.mC55 = mC55;
    }

    public Integer getmC56() {
        return mC56;
    }

    public void setmC56(Integer mC56) {
        this.mC56 = mC56;
    }

    public Integer getmC57() {
        return mC57;
    }

    public void setmC57(Integer mC57) {
        this.mC57 = mC57;
    }

    public Integer getmC58A() {
        return mC58A;
    }

    public void setmC58A(Integer mC58A) {
        this.mC58A = mC58A;
    }

    public String getmC58B() {
        return mC58B;
    }

    public void setmC58B(String mC58B) {
        this.mC58B = mC58B;
    }

    public Integer getmC59() {
        return mC59;
    }

    public void setmC59(Integer mC59) {
        this.mC59 = mC59;
    }

    public Integer getmC60() {
        return mC60;
    }

    public void setmC60(Integer mC60) {
        this.mC60 = mC60;
    }

    public String getmC60A() {
        return mC60A;
    }

    public void setmC60A(String mC60A) {
        this.mC60A = mC60A;
    }

    public Integer getmC61() {
        return mC61;
    }

    public void setmC61(Integer mC61) {
        this.mC61 = mC61;
    }

    public String getmC61A() {
        return mC61A;
    }

    public void setmC61A(String mC61A) {
        this.mC61A = mC61A;
    }

    public Integer getmC62() {
        return mC62;
    }

    public void setmC62(Integer mC62) {
        this.mC62 = mC62;
    }

    public String getmC62A() {
        return mC62A;
    }

    public void setmC62A(String mC62A) {
        this.mC62A = mC62A;
    }

    public Integer getmC63() {
        return mC63;
    }

    public void setmC63(Integer mC63) {
        this.mC63 = mC63;
    }

    public String getmC63A() {
        return mC63A;
    }

    public void setmC63A(String mC63A) {
        this.mC63A = mC63A;
    }

    public Integer getmC64() {
        return mC64;
    }

    public void setmC64(Integer mC64) {
        this.mC64 = mC64;
    }

    public String getmC65A() {
        return mC65A;
    }

    public void setmC65A(String mC65A) {
        this.mC65A = mC65A;
    }

    public String getmC65B() {
        return mC65B;
    }

    public void setmC65B(String mC65B) {
        this.mC65B = mC65B;
    }

    public String getmC65C() {
        return mC65C;
    }

    public void setmC65C(String mC65C) {
        this.mC65C = mC65C;
    }

    public String getmC65D() {
        return mC65D;
    }

    public void setmC65D(String mC65D) {
        this.mC65D = mC65D;
    }

    public String getmC65E() {
        return mC65E;
    }

    public void setmC65E(String mC65E) {
        this.mC65E = mC65E;
    }

    public String getmC65F() {
        return mC65F;
    }

    public void setmC65F(String mC65F) {
        this.mC65F = mC65F;
    }

    public String getmC65G() {
        return mC65G;
    }

    public void setmC65G(String mC65G) {
        this.mC65G = mC65G;
    }

    public String getmC65H() {
        return mC65H;
    }

    public void setmC65H(String mC65H) {
        this.mC65H = mC65H;
    }

    public String getmC65I() {
        return mC65I;
    }

    public void setmC65I(String mC65I) {
        this.mC65I = mC65I;
    }

    public String getmC65J() {
        return mC65J;
    }

    public void setmC65J(String mC65J) {
        this.mC65J = mC65J;
    }

    public String getmC65K() {
        return mC65K;
    }

    public void setmC65K(String mC65K) {
        this.mC65K = mC65K;
    }

    public String getmC65L() {
        return mC65L;
    }

    public void setmC65L(String mC65L) {
        this.mC65L = mC65L;
    }

    public String getmDIACHICHUHO() {
        return mDIACHICHUHO;
    }

    public void setmDIACHICHUHO(String mDIACHICHUHO) {
        this.mDIACHICHUHO = mDIACHICHUHO;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }


    public void setmKT6(Integer mKT6) {
        this.mKT6 = mKT6;
    }

    public String getmNgayCapnhat() {
        return mNgayCapnhat;
    }

    public void setmNgayCapnhat(String mNgayCapnhat) {
        this.mNgayCapnhat = mNgayCapnhat;
    }

    public String getmNguoiCapnhat() {
        return mNguoiCapnhat;
    }

    public void setmNguoiCapnhat(String mNguoiCapnhat) {
        this.mNguoiCapnhat = mNguoiCapnhat;
    }

    public Long getmPHUONGPHAPDIEUTRA() {
        return mPHUONGPHAPDIEUTRA;
    }

    public void setmPHUONGPHAPDIEUTRA(Long mPHUONGPHAPDIEUTRA) {
        this.mPHUONGPHAPDIEUTRA = mPHUONGPHAPDIEUTRA;
    }

    public String getmTHOIGIANBD() {
        return mTHOIGIANBD;
    }

    public void setmTHOIGIANBD(String mTHOIGIANBD) {
        this.mTHOIGIANBD = mTHOIGIANBD;
    }

    public String getmTHOIGIANKT() {
        return mTHOIGIANKT;
    }

    public void setmTHOIGIANKT(String mTHOIGIANKT) {
        this.mTHOIGIANKT = mTHOIGIANKT;
    }

    public Long getmTSKHAU() {
        return mTSKHAU;
    }

    public void setmTSKHAU(Long mTSKHAU) {
        this.mTSKHAU = mTSKHAU;
    }

    public Long getmTSNAM() {
        return mTSNAM;
    }

    public void setmTSNAM(Long mTSNAM) {
        this.mTSNAM = mTSNAM;
    }

    public Long getmTSNU() {
        return mTSNU;
    }

    public void setmTSNU(Long mTSNU) {
        this.mTSNU = mTSNU;
    }

    public Long getmTTNT() {
        return mTTNT;
    }

    public void setmTTNT(Long mTTNT) {
        this.mTTNT = mTTNT;
    }

    public Integer getmTrangthai() {
        return mTrangthai;
    }

    public void setmTrangthai(Integer mTrangthai) {
        this.mTrangthai = mTrangthai;
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
                return field.get(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return null;
    }
}
