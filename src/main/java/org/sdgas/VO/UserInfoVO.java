package org.sdgas.VO;

/**
 * Created by 120378 on 2015-04-02.
 */
public class UserInfoVO extends BaseVO {

    private String USERID;
    private String NAME;
    private String pwd;

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
