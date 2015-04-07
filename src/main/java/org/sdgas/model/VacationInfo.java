package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

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
     * 休假类型
     */
    private String vacationSymbol;
    /**
     * 开始时间
     */
    private Date beginDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 休假时长
     */
    private double longTime;
    /**
     * 标记
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

    @Temporal(TemporalType.DATE)
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(length = 10)
    public String getVacationSymbol() {
        return vacationSymbol;
    }

    public void setVacationSymbol(String vacationSymbol) {
        this.vacationSymbol = vacationSymbol;
    }
}
