package org.sdgas.VO;

import java.util.Date;

/**
 * Created by 120378 on 2015/6/17.
 */
public class CheckInOutVO extends BaseVO {

    private int USERID;
    private String CHECKTIME;

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public String getCHECKTIME() {
        return CHECKTIME;
    }

    public void setCHECKTIME(String CHECKTIME) {
        this.CHECKTIME = CHECKTIME;
    }
}
