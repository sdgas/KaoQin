package org.sdgas.VO;

/**
 * Created by 斌 on 2015/4/5.
 */
public class OverTimeVO extends BaseVO{

    private String userinfo;
    private String longTime;
    private String remarks;

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public String getLongTime() {
        return longTime;
    }

    public void setLongTime(String longTime) {
        this.longTime = longTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
