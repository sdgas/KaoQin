package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.CheckInOutVO;
import org.sdgas.model.Administrators;
import org.sdgas.service.CheckInOutService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 120378 on 2015/6/17.
 */
@Component("checkInOut")
@Scope("prototype")
public class CheckInOutAction extends MyActionSupport implements ModelDriven<CheckInOutVO> {

    CheckInOutVO checkInOutVO = new CheckInOutVO();
    private CheckInOutService checkInOutService;

    private static final Logger logger = LogManager.getLogger(CheckInOutAction.class);

    //获取当前登录用户信息
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    @Override
    public String execute() {

        return SUCCESS;
    }

    @Override
    public CheckInOutVO getModel() {
        return checkInOutVO;
    }

    @Resource(name = "checkInOutServiceImpl")
    public void setCheckInOutService(CheckInOutService checkInOutService) {
        this.checkInOutService = checkInOutService;
    }
}
