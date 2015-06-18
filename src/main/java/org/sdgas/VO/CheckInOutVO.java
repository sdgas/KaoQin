package org.sdgas.VO;

import java.util.Date;

/**
 * Created by 120378 on 2015/6/17.
 */
public class CheckInOutVO extends BaseVO {

    private String userInfo;
    private String month;
    private String CHECKTIME;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getCHECKTIME() {
        return CHECKTIME;
    }

    public void setCHECKTIME(String CHECKTIME) {
        this.CHECKTIME = CHECKTIME;
    }
}
