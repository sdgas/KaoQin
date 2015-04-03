package org.sdgas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 120378 on 2015-04-02.
 */

@Entity
public class Administrators {

    /**
     * 用户工号
     */
    private int userId;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 管理员类型 0超级管理员  1部门考勤员
     */
    private int type;

    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(length = 16, nullable = false)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
