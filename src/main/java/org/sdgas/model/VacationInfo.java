package org.sdgas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 120378 on 2015-04-02.
 */

@Entity
public class VacationInfo {

    /**
     * 标识列
     */
    private int id;
    /**
     * 员工信息
     */
    private int userinfo;

    /**
     * 休假时长
     */
    private double longTime;
    /**
     * 休假原因
     */
    private String remarks;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public int getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(int userinfo) {
        this.userinfo = userinfo;
    }

    @Column(precision = 4, scale = 1, nullable = false)
    public double getLongTime() {
        return longTime;
    }

    public void setLongTime(double longTime) {
        this.longTime = longTime;
    }

    @Column(length = 300)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
