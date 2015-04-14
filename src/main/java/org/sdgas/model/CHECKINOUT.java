package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 120378 on 2015-04-14.
 */
@Entity
@IdClass(CheckInOutPK.class)
public class CHECKINOUT {

    /**
     * 员工ID号
     */
    private int USERID;

    /**
     * 签到/签退时间
     */
    private Date CHECKTIME;

    /**
     * 签到/签退标志：I-签到，O-签退
     */
    private String CHECKTYPE;

    /**
     * 验证方式
     */
    private int VERIFYCODE;

    /**
     * 采集数据的考勤终端/设备ID
     */
    private String SENSORID;
    private String Memoinfo;
    private String WorkCode;
    private String sn;
    private short UserExtFmt;



    @Id
    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCHECKTIME() {
        return CHECKTIME;
    }

    public void setCHECKTIME(Date CHECKTIME) {
        this.CHECKTIME = CHECKTIME;
    }

    @Column(length = 5)
    public String getCHECKTYPE() {
        return CHECKTYPE;
    }

    public void setCHECKTYPE(String CHECKTYPE) {
        this.CHECKTYPE = CHECKTYPE;
    }

    @Column(columnDefinition = "INT default 0")
    public int getVERIFYCODE() {
        return VERIFYCODE;
    }

    public void setVERIFYCODE(int VERIFYCODE) {
        this.VERIFYCODE = VERIFYCODE;
    }

    @Column(length = 8)
    public String getSENSORID() {
        return SENSORID;
    }

    public void setSENSORID(String SENSORID) {
        this.SENSORID = SENSORID;
    }

    @Column(length = 30)
    public String getMemoinfo() {
        return Memoinfo;
    }

    public void setMemoinfo(String memoinfo) {
        Memoinfo = memoinfo;
    }

    @Column(length = 24)
    public String getWorkCode() {
        return WorkCode;
    }

    public void setWorkCode(String workCode) {
        WorkCode = workCode;
    }

    @Column(length = 20)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public short getUserExtFmt() {
        return UserExtFmt;
    }

    public void setUserExtFmt(short userExtFmt) {
        UserExtFmt = userExtFmt;
    }
}
