package org.sdgas.model;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by 120378 on 2015-04-02.
 */
@Entity
public class USERINFO {

    private int USERID;
    private String BADGENUMBER;
    private String NAME;
    private String SSN;
    private String GENDER;
    private String TITLE;
    private String PAGER;
    private Date BIRTHDAY;
    private Date HIREDDAY;
    private String STREET;
    private String CITY;
    private String STATE;
    private String ZIP;
    private String OPHONE;
    private String FPHONE;
    private int VERIFICATIONMETHOD;
    private int DEFAULTDEPTID;
    private int ATT;
    private int INLATE;
    private int OUTEARLY;
    private int OVERTIME;
    private int SEP;
    private int HOLIDAY;
    private String MINZU;
    private String LUNCHDURATION;
    private String MVerifyPass;
    private Image PHOTO;
    private String PASSWORD;

    @Id
    @GeneratedValue
    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    @Column(length = 12, nullable = false)
    public String getBADGENUMBER() {
        return BADGENUMBER;
    }

    public void setBADGENUMBER(String BADGENUMBER) {
        this.BADGENUMBER = BADGENUMBER;
    }

    @Column(length = 20)
    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    @Column(length = 20)
    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    @Column(length = 2)
    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    @Column(length = 20)
    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    @Column(length = 20)
    public String getPAGER() {
        return PAGER;
    }

    public void setPAGER(String PAGER) {
        this.PAGER = PAGER;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getBIRTHDAY() {
        return BIRTHDAY;
    }

    public void setBIRTHDAY(Date BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getHIREDDAY() {
        return HIREDDAY;
    }

    public void setHIREDDAY(Date HIREDDAY) {
        this.HIREDDAY = HIREDDAY;
    }

    @Column(length = 40)
    public String getSTREET() {
        return STREET;
    }

    public void setSTREET(String STREET) {
        this.STREET = STREET;
    }

    @Column(length = 2)
    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    @Column(length = 2)
    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    @Column(length = 12)
    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    @Column(length = 20)
    public String getOPHONE() {
        return OPHONE;
    }

    public void setOPHONE(String OPHONE) {
        this.OPHONE = OPHONE;
    }

    @Column(length = 20)
    public String getFPHONE() {
        return FPHONE;
    }

    public void setFPHONE(String FPHONE) {
        this.FPHONE = FPHONE;
    }

    public int getVERIFICATIONMETHOD() {
        return VERIFICATIONMETHOD;
    }

    public void setVERIFICATIONMETHOD(int VERIFICATIONMETHOD) {
        this.VERIFICATIONMETHOD = VERIFICATIONMETHOD;
    }

    @Column(columnDefinition = "INT default 1")
    public int getDEFAULTDEPTID() {
        return DEFAULTDEPTID;
    }

    public void setDEFAULTDEPTID(int DEFAULTDEPTID) {
        this.DEFAULTDEPTID = DEFAULTDEPTID;
    }

    @Column(columnDefinition = "INT default 1")
    public int getATT() {
        return ATT;
    }

    public void setATT(int ATT) {
        this.ATT = ATT;
    }

    @Column(columnDefinition = "INT default 1")
    public int getINLATE() {
        return INLATE;
    }

    public void setINLATE(int INLATE) {
        this.INLATE = INLATE;
    }

    @Column(columnDefinition = "INT default 1")
    public int getOUTEARLY() {
        return OUTEARLY;
    }

    public void setOUTEARLY(int OUTEARLY) {
        this.OUTEARLY = OUTEARLY;
    }

    @Column(columnDefinition = "INT default 1")
    public int getOVERTIME() {
        return OVERTIME;
    }

    public void setOVERTIME(int OVERTIME) {
        this.OVERTIME = OVERTIME;
    }

    @Column(columnDefinition = "INT default 1")
    public int getSEP() {
        return SEP;
    }

    public void setSEP(int SEP) {
        this.SEP = SEP;
    }

    @Column(columnDefinition = "INT default 1")
    public int getHOLIDAY() {
        return HOLIDAY;
    }

    public void setHOLIDAY(int HOLIDAY) {
        this.HOLIDAY = HOLIDAY;
    }

    @Column(length = 8)
    public String getMINZU() {
        return MINZU;
    }

    public void setMINZU(String MINZU) {
        this.MINZU = MINZU;
    }

    @Column(columnDefinition = "INT default 1")
    public String getLUNCHDURATION() {
        return LUNCHDURATION;
    }

    public void setLUNCHDURATION(String LUNCHDURATION) {
        this.LUNCHDURATION = LUNCHDURATION;
    }

    @Column(length = 10)
    public String getMVerifyPass() {
        return MVerifyPass;
    }

    public void setMVerifyPass(String MVerifyPass) {
        this.MVerifyPass = MVerifyPass;
    }

    @Column(length = 20)
    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
