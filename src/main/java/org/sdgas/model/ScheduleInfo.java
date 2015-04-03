package org.sdgas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 120378 on 2015-04-02.
 */

@Entity
public class ScheduleInfo {

    /**
     * 标识列
     */
    private int id;

    /**
     * 人员信息
     */
    private int userinfo;

    /**
     * 排班日期
     */
    private Date scheduleDate;

    /**
     * 上班时间
     */
    private int periodId;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(int userinfo) {
        this.userinfo = userinfo;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }
}
