
package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;
import com.uyenpham.censusapplication.utils.Logger;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MemberDTO {
    public static final String ID_HO = "ID";
    public static final String ID_MEMBER = "IDTV";
    public static final String C02 = "C02";

    @SerializedName("C01")
    private String mC01;
    @SerializedName("C02")
    private Integer mC02;
    @SerializedName("C03")
    private Integer mC03;
    @SerializedName("C05")
    private Integer mC05;
    @SerializedName("C08")
    private Integer mC08;
    @SerializedName("C09")
    private Integer mC09;
    @SerializedName("C10A")
    private String mC10A;
    @SerializedName("C10B")
    private String mC10B;
    @SerializedName("C11")
    private Integer mC11;
    @SerializedName("C12")
    private Integer mC12;
    @SerializedName("C12K")
    private String mC12K;
    @SerializedName("C13A")
    private Integer mC13A;
    @SerializedName("C13B")
    private Integer mC13B;
    @SerializedName("C13C")
    private Integer mC13C;
    @SerializedName("C13D")
    private Integer mC13D;
    @SerializedName("C13E")
    private Integer mC13E;
    @SerializedName("C13F")
    private Integer mC13F;
    @SerializedName("C14")
    private Integer mC14;
    @SerializedName("C15")
    private Integer mC15;
    @SerializedName("C16")
    private Integer mC16;
    @SerializedName("C17")
    private Integer mC17;
    @SerializedName("C18")
    private Integer mC18;
    @SerializedName("C19")
    private Integer mC19;
    @SerializedName("C20")
    private Integer mC20;
    @SerializedName("C21")
    private Integer mC23;
    @SerializedName("C22")
    private Integer mC22;
    @SerializedName("C21N")
    private Integer mC21N;
    @SerializedName("C21T")
    private Integer mC21T;
    @SerializedName("C24")
    private Integer mC24;
    @SerializedName("C25")
    private Integer mC25;
    @SerializedName("C28")
    private Integer mC28;
    @SerializedName("C27A")
    private String mC27A;
    @SerializedName("C27B")
    private String mC27B;
    @SerializedName("C27C")
    private Integer mC27C;
    @SerializedName("C26A")
    private String mC26A;
    @SerializedName("C26B")
    private String mC26B;
    @SerializedName("C26C")
    private Integer mC26C;
    @SerializedName("C29A")
    private Integer mC29A;
    @SerializedName("C29B")
    private Integer mC29B;
    @SerializedName("C29C")
    private Integer mC29C;
    @SerializedName("C30")
    private Integer mC30;
    @SerializedName("C31")
    private Integer mC31;
    @SerializedName("C32A")
    private Integer mC32A;
    @SerializedName("C32B")
    private String mC32B;
    @SerializedName("C33")
    private Integer mC33;
    @SerializedName("C4N")
    private Integer mC4N;
    @SerializedName("C4T")
    private Integer mC4T;
    @SerializedName("C6A")
    private Integer mC6A;
    @SerializedName("C6B")
    private String mC6B;
    @SerializedName("C6C")
    private String mC6C;
    @SerializedName("C7A")
    private Integer mC7A;
    @SerializedName("C7B")
    private String mC7B;
    @SerializedName("C7C")
    private String mC7C;
    @SerializedName("ID")
    private String mID;
    @SerializedName("IDTV")
    private String mIDTV;
    @SerializedName("STT_NKTT")
    private Integer mSTTNKTT;

    private boolean isSlected;

    public MemberDTO() {
    }

    public MemberDTO(String C01) {
        this.mC01 = C01;
    }

    public MemberDTO(String mC01, Integer mC02, Integer mC03, Integer mC05, Integer mC08, Integer
            mC09, String mC10A, String mC10B, Integer mC11, Integer mC12, String mC12K, Integer
            mC13A, Integer mC13B, Integer mC13C, Integer mC13D, Integer mC13E, Integer mC13F,
                     Integer mC14, Integer mC15, Integer mC16, Integer mC17, Integer mC18, Integer
                             mC19, Integer mC20, Integer mC23, Integer mC22, Integer mC21N,
                     Integer mC21T, Integer mC24, Integer mC25, Integer mC28, String mC27A,
                     String mC27B, Integer mC27C, String mC26A, String mC26B, Integer mC26C,
                     Integer mC29A, Integer mC29B, Integer mC29C, Integer mC30, Integer mC31,
                     Integer mC32A, String mC32B, Integer mC33, Integer mC4N, Integer mC4T,
                     Integer mC6A, String mC6B, String mC6C, Integer mC7A, String mC7B, String
                             mC7C, String mID, String mIDTV, Integer mSTTNKTT) {
        this.mC01 = mC01;
        this.mC02 = mC02;
        this.mC03 = mC03;
        this.mC05 = mC05;
        this.mC08 = mC08;
        this.mC09 = mC09;
        this.mC10A = mC10A;
        this.mC10B = mC10B;
        this.mC11 = mC11;
        this.mC12 = mC12;
        this.mC12K = mC12K;
        this.mC13A = mC13A;
        this.mC13B = mC13B;
        this.mC13C = mC13C;
        this.mC13D = mC13D;
        this.mC13E = mC13E;
        this.mC13F = mC13F;
        this.mC14 = mC14;
        this.mC15 = mC15;
        this.mC16 = mC16;
        this.mC17 = mC17;
        this.mC18 = mC18;
        this.mC19 = mC19;
        this.mC20 = mC20;
        this.mC23 = mC23;
        this.mC22 = mC22;
        this.mC21N = mC21N;
        this.mC21T = mC21T;
        this.mC24 = mC24;
        this.mC25 = mC25;
        this.mC28 = mC28;
        this.mC27A = mC27A;
        this.mC27B = mC27B;
        this.mC27C = mC27C;
        this.mC26A = mC26A;
        this.mC26B = mC26B;
        this.mC26C = mC26C;
        this.mC29A = mC29A;
        this.mC29B = mC29B;
        this.mC29C = mC29C;
        this.mC30 = mC30;
        this.mC31 = mC31;
        this.mC32A = mC32A;
        this.mC32B = mC32B;
        this.mC33 = mC33;
        this.mC4N = mC4N;
        this.mC4T = mC4T;
        this.mC6A = mC6A;
        this.mC6B = mC6B;
        this.mC6C = mC6C;
        this.mC7A = mC7A;
        this.mC7B = mC7B;
        this.mC7C = mC7C;
        this.mID = mID;
        this.mIDTV = mIDTV;
        this.mSTTNKTT = mSTTNKTT;
    }

    public boolean isSlected() {
        return isSlected;
    }

    public void setSlected(boolean slected) {
        isSlected = slected;
    }

    public String getmC01() {
        return mC01;
    }

    public void setmC01(String mC01) {
        this.mC01 = mC01;
    }

    public Integer getmC02() {
        return mC02;
    }

    public void setmC02(Integer mC02) {
        this.mC02 = mC02;
    }

    public Integer getmC03() {
        return mC03;
    }

    public void setmC03(Integer mC03) {
        this.mC03 = mC03;
    }

    public Integer getmC05() {
        return mC05;
    }

    public void setmC05(Integer mC05) {
        this.mC05 = mC05;
    }

    public Integer getmC08() {
        return mC08;
    }

    public void setmC08(Integer mC08) {
        this.mC08 = mC08;
    }

    public Integer getmC09() {
        return mC09;
    }

    public void setmC09(Integer mC09) {
        this.mC09 = mC09;
    }

    public String getmC10A() {
        return mC10A;
    }

    public void setmC10A(String mC10A) {
        this.mC10A = mC10A;
    }

    public String getmC10B() {
        return mC10B;
    }

    public void setmC10B(String mC10B) {
        this.mC10B = mC10B;
    }

    public Integer getmC11() {
        return mC11;
    }

    public void setmC11(Integer mC11) {
        this.mC11 = mC11;
    }

    public Integer getmC12() {
        return mC12;
    }

    public void setmC12(Integer mC12) {
        this.mC12 = mC12;
    }

    public String getmC12K() {
        return mC12K;
    }

    public void setmC12K(String mC12K) {
        this.mC12K = mC12K;
    }

    public Integer getmC13A() {
        return mC13A;
    }

    public void setmC13A(Integer mC13A) {
        this.mC13A = mC13A;
    }

    public Integer getmC13B() {
        return mC13B;
    }

    public void setmC13B(Integer mC13B) {
        this.mC13B = mC13B;
    }

    public Integer getmC13C() {
        return mC13C;
    }

    public void setmC13C(Integer mC13C) {
        this.mC13C = mC13C;
    }

    public Integer getmC13D() {
        return mC13D;
    }

    public void setmC13D(Integer mC13D) {
        this.mC13D = mC13D;
    }

    public Integer getmC13E() {
        return mC13E;
    }

    public void setmC13E(Integer mC13E) {
        this.mC13E = mC13E;
    }

    public Integer getmC13F() {
        return mC13F;
    }

    public void setmC13F(Integer mC13F) {
        this.mC13F = mC13F;
    }

    public Integer getmC14() {
        return mC14;
    }

    public void setmC14(Integer mC14) {
        this.mC14 = mC14;
    }

    public Integer getmC15() {
        return mC15;
    }

    public void setmC15(Integer mC15) {
        this.mC15 = mC15;
    }

    public Integer getmC16() {
        return mC16;
    }

    public void setmC16(Integer mC16) {
        this.mC16 = mC16;
    }

    public Integer getmC17() {
        return mC17;
    }

    public void setmC17(Integer mC17) {
        this.mC17 = mC17;
    }

    public Integer getmC18() {
        return mC18;
    }

    public void setmC18(Integer mC18) {
        this.mC18 = mC18;
    }

    public Integer getmC19() {
        return mC19;
    }

    public void setmC19(Integer mC19) {
        this.mC19 = mC19;
    }

    public Integer getmC20() {
        return mC20;
    }

    public void setmC20(Integer mC20) {
        this.mC20 = mC20;
    }


    public Integer getmC22() {
        return mC22;
    }

    public void setmC22(Integer mC22) {
        this.mC22 = mC22;
    }


    public Integer getmC24() {
        return mC24;
    }

    public void setmC24(Integer mC24) {
        this.mC24 = mC24;
    }

    public Integer getmC25() {
        return mC25;
    }

    public void setmC25(Integer mC25) {
        this.mC25 = mC25;
    }


    public Integer getmC29A() {
        return mC29A;
    }

    public void setmC29A(Integer mC29A) {
        this.mC29A = mC29A;
    }

    public Integer getmC29B() {
        return mC29B;
    }

    public void setmC29B(Integer mC29B) {
        this.mC29B = mC29B;
    }

    public Integer getmC29C() {
        return mC29C;
    }

    public void setmC29C(Integer mC29C) {
        this.mC29C = mC29C;
    }

    public Integer getmC30() {
        return mC30;
    }

    public void setmC30(Integer mC30) {
        this.mC30 = mC30;
    }

    public Integer getmC31() {
        return mC31;
    }

    public void setmC31(Integer mC31) {
        this.mC31 = mC31;
    }

    public Integer getmC32A() {
        return mC32A;
    }

    public void setmC32A(Integer mC32A) {
        this.mC32A = mC32A;
    }

    public String getmC32B() {
        return mC32B;
    }

    public void setmC32B(String mC32B) {
        this.mC32B = mC32B;
    }

    public Integer getmC33() {
        return mC33;
    }

    public void setmC33(Integer mC33) {
        this.mC33 = mC33;
    }

    public Integer getmC4N() {
        return mC4N;
    }

    public void setmC4N(Integer mC4N) {
        this.mC4N = mC4N;
    }

    public Integer getmC4T() {
        return mC4T;
    }

    public void setmC4T(Integer mC4T) {
        this.mC4T = mC4T;
    }

    public Integer getmC6A() {
        return mC6A;
    }

    public void setmC6A(Integer mC6A) {
        this.mC6A = mC6A;
    }

    public String getmC6B() {
        return mC6B;
    }

    public void setmC6B(String mC6B) {
        this.mC6B = mC6B;
    }

    public String getmC6C() {
        return mC6C;
    }

    public void setmC6C(String mC6C) {
        this.mC6C = mC6C;
    }

    public Integer getmC7A() {
        return mC7A;
    }

    public void setmC7A(Integer mC7A) {
        this.mC7A = mC7A;
    }

    public String getmC7B() {
        return mC7B;
    }

    public void setmC7B(String mC7B) {
        this.mC7B = mC7B;
    }

    public String getmC7C() {
        return mC7C;
    }

    public void setmC7C(String mC7C) {
        this.mC7C = mC7C;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmIDTV() {
        return mIDTV;
    }

    public void setmIDTV(String mIDTV) {
        this.mIDTV = mIDTV;
    }

    public Integer getmSTTNKTT() {
        return mSTTNKTT;
    }

    public static String getIdHo() {
        return ID_HO;
    }

    public static String getIdMember() {
        return ID_MEMBER;
    }

    public Integer getmC23() {
        return mC23;
    }

    public void setmC23(Integer mC23) {
        this.mC23 = mC23;
    }

    public Integer getmC21N() {
        return mC21N;
    }

    public void setmC21N(Integer mC21N) {
        this.mC21N = mC21N;
    }

    public Integer getmC21T() {
        return mC21T;
    }

    public void setmC21T(Integer mC21T) {
        this.mC21T = mC21T;
    }

    public Integer getmC28() {
        return mC28;
    }

    public void setmC28(Integer mC28) {
        this.mC28 = mC28;
    }

    public String getmC27A() {
        return mC27A;
    }

    public void setmC27A(String mC27A) {
        this.mC27A = mC27A;
    }

    public String getmC27B() {
        return mC27B;
    }

    public void setmC27B(String mC27B) {
        this.mC27B = mC27B;
    }

    public Integer getmC27C() {
        return mC27C;
    }

    public void setmC27C(Integer mC27C) {
        this.mC27C = mC27C;
    }

    public String getmC26A() {
        return mC26A;
    }

    public void setmC26A(String mC26A) {
        this.mC26A = mC26A;
    }

    public String getmC26B() {
        return mC26B;
    }

    public void setmC26B(String mC26B) {
        this.mC26B = mC26B;
    }

    public Integer getmC26C() {
        return mC26C;
    }

    public void setmC26C(Integer mC26C) {
        this.mC26C = mC26C;
    }

    public void setmSTTNKTT(Integer mSTTNKTT) {
        this.mSTTNKTT = mSTTNKTT;
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
                Logger.e(e);
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
            retVal = ptr.getID() == this.mID;
        }

        return retVal;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
