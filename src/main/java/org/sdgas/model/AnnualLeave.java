package org.sdgas.model;

import org.sdgas.util.ExcelResources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 120378 on 2015-04-07.
 */
@Entity
public class AnnualLeave {

    private int id;
    /**
     * 用户信息
     */
    private int userId;

    /**
     * 剩余天数
     */
    private int days;

    /**
     * 备注
     */
    private String remark;

    /**
     * 年份
     */
    private int year;


    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ExcelResources(order = 1, title = "姓名")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @ExcelResources(order = 2, title = "年假天数")
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ExcelResources(order = 3, title = "年份")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
