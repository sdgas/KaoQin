package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.VacationInfoVO;
import org.sdgas.model.Administrators;
import org.sdgas.model.USERINFO;
import org.sdgas.model.VacationInfo;
import org.sdgas.service.UserInfoService;
import org.sdgas.service.VacationInfoService;
import org.sdgas.util.ChangeTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 斌 on 2015/4/5.
 */
@Component("vacationInfo")
@Scope("prototype")
public class VacationInfoAction extends MyActionSupport implements ModelDriven<VacationInfoVO> {

    private VacationInfoVO vacationInfoVO = new VacationInfoVO();
    private VacationInfoService vacationInfoService;
    private UserInfoService userInfoService;

    private static final Logger logger = LogManager.getLogger(VacationInfoAction.class);

    //获取当前登录用户信息
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    @Override
    public String execute() {
        USERINFO userinfo = userInfoService.findByName(vacationInfoVO.getStaff());
        if(userinfo == null){
            vacationInfoVO.setResultMessage("<script>alert('找不到该用户！');location.href='/KaoQin/page/leave/report.jsp';</script>");
            return ERROR;
        }
        VacationInfo vacationInfo = new VacationInfo();
        vacationInfo.setUserinfo(userinfo.getUSERID());
        vacationInfo.setBeginDate(ChangeTime.parseStringToShortDate(vacationInfoVO.getBegin()));
        vacationInfo.setEndDate(ChangeTime.parseStringToShortDate(vacationInfoVO.getEnd()));
        vacationInfo.setLongTime(Double.valueOf(vacationInfoVO.getLongTime()));
        vacationInfo.setVacationSymbol(vacationInfoVO.getVacationId());
        String tep = vacationInfoVO.getBegin() + "," + vacationInfoVO.getRemarks()
                + ";" + vacationInfoVO.getEnd() + "," + vacationInfoVO.getRemarks2();
        vacationInfo.setRemarks(tep);
        vacationInfoService.save(vacationInfo);
        if ("A".equals(vacationInfo.getVacationSymbol())) {

        }
        logger.info("管理员：" + user.getUserId() + " 添加了一条休假记录(" + vacationInfo.getId() + ")。IP:" + ip);
        vacationInfoVO.setResultMessage("<script>alert('添加成功！');location.href='/KaoQin/page/leave/report.jsp';</script>");
        return SUCCESS;
    }

    @Override
    public VacationInfoVO getModel() {
        return vacationInfoVO;
    }

    @Resource(name = "vacationInfoServiceImpl")
    public void setVacationInfoService(VacationInfoService vacationInfoService) {
        this.vacationInfoService = vacationInfoService;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
}
