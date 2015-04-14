package org.sdgas.ajax;

import com.opensymphony.xwork2.ActionSupport;
import org.sdgas.model.Period;
import org.sdgas.model.ScheduleInfo;
import org.sdgas.model.USERINFO;
import org.sdgas.service.PeriodService;
import org.sdgas.service.ScheduleInfoService;
import org.sdgas.service.UserInfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by 120378 on 2015-04-14.
 */
@Component("periodActionAjax")
@Scope("prototype")
public class PeriodActionAjax extends ActionSupport {

    private PeriodService periodService;
    private ScheduleInfoService scheduleInfoService;
    private UserInfoService userInfoService;

    private String userId;
    private String date;
    private int str;
    private String msg;


    @Override
    public String execute() throws IOException {
        USERINFO userinfo = userInfoService.findById(userId);
        String temp = date.split("-")[0] + date.split("-")[1];
        ScheduleInfo scheduleInfo = scheduleInfoService.findByUserAndDate(userinfo.getUSERID(), temp);
        temp = date.split("-")[2];
        switch (Integer.valueOf(temp)) {
            case 21:
                str = scheduleInfo.get_21st();
                break;
            case 22:
                str = scheduleInfo.get_22nd();
                break;
            case 23:
                str = scheduleInfo.get_23rd();
                break;
            case 24:
                str = scheduleInfo.get_24th();
                break;
            case 25:
                str = scheduleInfo.get_25th();
                break;
            case 26:
                str = scheduleInfo.get_26th();
                break;
            case 27:
                str = scheduleInfo.get_27th();
                break;
            case 28:
                str = scheduleInfo.get_28th();
                break;
            case 29:
                str = scheduleInfo.get_29th();
                break;
            case 30:
                str = scheduleInfo.get_30th();
                break;
            case 31:
                str = scheduleInfo.get_31st();
                break;
            case 1:
                str = scheduleInfo.get_1st();
                break;
            case 2:
                str = scheduleInfo.get_2nd();
                break;
            case 3:
                str = scheduleInfo.get_3rd();
                break;
            case 4:
                str = scheduleInfo.get_4th();
                break;
            case 5:
                str = scheduleInfo.get_5th();
                break;
            case 6:
                str = scheduleInfo.get_6th();
                break;
            case 7:
                str = scheduleInfo.get_7th();
                break;
            case 8:
                str = scheduleInfo.get_8th();
                break;
            case 9:
                str = scheduleInfo.get_9th();
                break;
            case 10:
                str = scheduleInfo.get_10th();
                break;
            case 11:
                str = scheduleInfo.get_11st();
                break;
            case 12:
                str = scheduleInfo.get_12nd();
                break;
            case 13:
                str = scheduleInfo.get_13rd();
                break;
            case 14:
                str = scheduleInfo.get_14th();
                break;
            case 15:
                str = scheduleInfo.get_15th();
                break;
            case 16:
                str = scheduleInfo.get_16th();
                break;
            case 17:
                str = scheduleInfo.get_17th();
                break;
            case 18:
                str = scheduleInfo.get_18th();
                break;
            case 19:
                str = scheduleInfo.get_19th();
                break;
            case 20:
                str = scheduleInfo.get_20th();
                break;
        }

        if (str == 0)
            msg = "休假";
        else {
            Period period = periodService.find(Period.class, str);
            msg = period.getRemark() + "  " + period.getSymbol() + "班  " + period.getPeriod();
        }
        return SUCCESS;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Resource(name = "scheduleInfoServiceImpl")
    public void setScheduleInfoService(ScheduleInfoService scheduleInfoService) {
        this.scheduleInfoService = scheduleInfoService;
    }

    @Resource(name = "periodServiceImpl")
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }
}
