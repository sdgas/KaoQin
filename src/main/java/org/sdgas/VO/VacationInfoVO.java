package org.sdgas.VO;

/**
 * Created by 斌 on 2015/4/5.
 */
public class VacationInfoVO extends BaseVO {

    private String staffId;
    private String vacationId;
    private String begin;
    private String end;
    private String longTime;
    private String remarks;
    private String remarks2;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getVacationId() {
        return vacationId;
    }

    public void setVacationId(String vacationId) {
        this.vacationId = vacationId;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public String getRemarks2() {
        return remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
