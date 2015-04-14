package org.sdgas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 斌 on 2015/4/15.
 */
public class CheckInOutPK implements Serializable {

    /**
     * 员工ID号
     */
    private int USERID;

    /**
     * 签到/签退时间
     */
    private Date CHECKTIME;

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public Date getCHECKTIME() {
        return CHECKTIME;
    }

    public void setCHECKTIME(Date CHECKTIME) {
        this.CHECKTIME = CHECKTIME;
    }
}
