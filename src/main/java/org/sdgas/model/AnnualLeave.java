package org.sdgas.model;

/**
 * Created by 120378 on 2015-04-07.
 */
public class AnnualLeave {

    /**
     * 用户信息
     */
    private String userId;

    /**
     * 剩余天数
     */
    private int days;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否限制
     */
    private String limited;

    /**
     * 年份
     */
    private int year;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getLimited() {
        return limited;
    }

    public void setLimited(String limited) {
        this.limited = limited;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
