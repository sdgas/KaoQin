package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.VacationInfoVO;
import org.sdgas.model.Administrators;
import org.sdgas.model.VacationInfo;
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

    private static final Logger logger = LogManager.getLogger(UserInfoAction.class);

    //获取当前登录用户信息
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    @Override
    public String execute() {
        VacationInfo vacationInfo = new VacationInfo();
        vacationInfo.setUserinfo(Integer.valueOf(vacationInfoVO.getStaffId()));
        vacationInfo.setBeginDate(ChangeTime.parseStringToShortDate(vacationInfoVO.getBegin()));
        vacationInfo.setEndDate(ChangeTime.parseStringToShortDate(vacationInfoVO.getEnd()));
        vacationInfo.setLongTime(Double.valueOf(vacationInfoVO.getLongTime()));
        vacationInfo.setVacationId(Integer.valueOf(vacationInfoVO.getVacationId()));
        String tep = vacationInfoVO.getBegin() + "," + vacationInfoVO.getRemarks()
                + ";" + vacationInfoVO.getEnd() + "," + vacationInfoVO.getRemarks2();
        vacationInfo.setRemarks(tep);
        vacationInfoService.save(vacationInfo);
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
}
