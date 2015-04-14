package org.sdgas.VO;

/**
 * Created by 120378 on 2015-04-14.
 */
public class ScheduleVO extends BaseVO {

    private String depS;
    private String staffId;
    private String date;
    private String sc;

    public String getDepS() {
        return depS;
    }

    public void setDepS(String depS) {
        this.depS = depS;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }
}
