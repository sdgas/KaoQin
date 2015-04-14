package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 120378 on 2015-04-02.
 */
@Entity
public class Overtime {

    /**
     * 加班编号
     */
    private int id;
    /**
     * 员工信息
     */
    private int userinfo;

    /**
     * 加班开始时间
     */
    private Date beginTime;

    /**
     * 加班结束时间
     */
    private Date endTime;

    /**
     * 加班时长
     */
    private double longTime;
    /**
     * 加班原因
     */
    private String remarks;

    /**
     * 加班日期
     */
    private String day;

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

    @Temporal(TemporalType.TIMESTAMP)
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(length = 10)
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
