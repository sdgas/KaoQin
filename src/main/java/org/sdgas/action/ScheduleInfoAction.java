package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.ScheduleVO;
import org.sdgas.model.Administrators;
import org.sdgas.model.Period;
import org.sdgas.model.ScheduleInfo;
import org.sdgas.model.USERINFO;
import org.sdgas.service.PeriodService;
import org.sdgas.service.ScheduleInfoService;
import org.sdgas.service.UserInfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 120378 on 2015-04-14.
 */

@Component("scheduleInfo")
@Scope("prototype")
public class ScheduleInfoAction extends MyActionSupport implements ModelDriven<ScheduleVO> {

    private ScheduleInfoService scheduleInfoService;
    private PeriodService periodService;
    private UserInfoService userInfoService;

    private ScheduleVO scheduleVO = new ScheduleVO();

    private static final Logger logger = LogManager.getLogger(ScheduleInfoAction.class);

    //获取当前登录用户信息
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    @Override
    public String execute() {

        Period period = periodService.find(Period.class, Integer.valueOf(scheduleVO.getSc()));
        int periodId = period == null ? 0 : period.getId();

        USERINFO userinfo = userInfoService.findById(scheduleVO.getStaffId());
        String temp = scheduleVO.getDate().split("-")[0] + scheduleVO.getDate().split("-")[1];
        ScheduleInfo scheduleInfo = scheduleInfoService.findByUserAndDate(userinfo.getUSERID(), temp);

        temp = scheduleVO.getDate().split("-")[2];
        switch (Integer.valueOf(temp)) {
            case 21:
                scheduleInfo.set_21st(periodId);
                break;
            case 22:
                scheduleInfo.set_22nd(periodId);
                break;
            case 23:
                scheduleInfo.set_23rd(periodId);
                break;
            case 24:
                scheduleInfo.set_24th(periodId);
                break;
            case 25:
                scheduleInfo.set_25th(periodId);
                break;
            case 26:
                scheduleInfo.set_26th(periodId);
                break;
            case 27:
                scheduleInfo.set_27th(periodId);
                break;
            case 28:
                scheduleInfo.set_28th(periodId);
                break;
            case 29:
                scheduleInfo.set_29th(periodId);
                break;
            case 30:
                scheduleInfo.set_30th(periodId);
                break;
            case 31:
                scheduleInfo.set_31st(periodId);
                break;
            case 1:
                scheduleInfo.set_1st(periodId);
                break;
            case 2:
                scheduleInfo.set_2nd(periodId);
                break;
            case 3:
                scheduleInfo.set_3rd(periodId);
                break;
            case 4:
                scheduleInfo.set_4th(periodId);
                break;
            case 5:
                scheduleInfo.set_5th(periodId);
                break;
            case 6:
                scheduleInfo.set_6th(periodId);
                break;
            case 7:
                scheduleInfo.set_7th(periodId);
                break;
            case 8:
                scheduleInfo.set_8th(periodId);
                break;
            case 9:
                scheduleInfo.set_9th(periodId);
                break;
            case 10:
                scheduleInfo.set_10th(periodId);
                break;
            case 11:
                scheduleInfo.set_11st(periodId);
                break;
            case 12:
                scheduleInfo.set_12nd(periodId);
                break;
            case 13:
                scheduleInfo.set_13rd(periodId);
                break;
            case 14:
                scheduleInfo.set_14th(periodId);
                break;
            case 15:
                scheduleInfo.set_15th(periodId);
                break;
            case 16:
                scheduleInfo.set_16th(periodId);
                break;
            case 17:
                scheduleInfo.set_17th(periodId);
                break;
            case 18:
                scheduleInfo.set_18th(periodId);
                break;
            case 19:
                scheduleInfo.set_19th(periodId);
                break;
            case 20:
                scheduleInfo.set_20th(periodId);
                break;
        }
        scheduleInfoService.update(scheduleInfo);
        logger.info("管理员:" + user.getUserId() + "修改了员工：" + scheduleInfo.getUserinfo() + " " + scheduleVO.getDate() + "的排班信息。IP：" + ip);
        scheduleVO.setResultMessage("<script>alert('修改成功！');location.href='/KaoQinpage/scheduleInfo/report.jsp';</script>");
        return SUCCESS;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Resource(name = "periodServiceImpl")
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    @Resource(name = "scheduleInfoServiceImpl")
    public void setScheduleInfoService(ScheduleInfoService scheduleInfoService) {
        this.scheduleInfoService = scheduleInfoService;
    }

    @Override
    public ScheduleVO getModel() {
        return scheduleVO;
    }
}
