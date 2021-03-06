package org.sdgas.model;


import org.sdgas.util.ExcelResources;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 斌 on 2015/4/5.
 */

@Entity
public class Holiday {

    /**
     * 编号
     */
    private int holidayId;

    /**
     * 节假日
     */
    private String holidayName;

    /**
     * 开始放假时间
     */
    private Date holidayBeginDate;

    /**
     * 节假日日期
     */
    private Date holiday;

    /**
     * 放假天数
     */
    private int longtime;

    /**
     * 补上班
     */
    private String workDate;

    @Id
    @GeneratedValue
    public int getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(int holidayId) {
        this.holidayId = holidayId;
    }

    @ExcelResources(title = "节假日", order = 1)
    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    @ExcelResources(title = "开始放假时间", order = 2)
    public Date getHolidayBeginDate() {
        return holidayBeginDate;
    }

    public void setHolidayBeginDate(Date holidayBeginDate) {
        this.holidayBeginDate = holidayBeginDate;
    }

    @ExcelResources(title = "放假天数", order = 3)
    public int getLongtime() {
        return longtime;
    }

    public void setLongtime(int longtime) {
        this.longtime = longtime;
    }

    @ExcelResources(title = "节假日时间", order = 4)
    public Date getHoliday() {
        return holiday;
    }

    public void setHoliday(Date holiday) {
        this.holiday = holiday;
    }

    @Column(length = 50)
    @ExcelResources(title = "补班日期",order = 5)
    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }
}
