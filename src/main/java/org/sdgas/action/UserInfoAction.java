package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.UserInfoVO;
import org.sdgas.model.Administrators;
import org.sdgas.model.DEPARTMENTS;
import org.sdgas.model.USERINFO;
import org.sdgas.service.DepartmentService;
import org.sdgas.service.UserInfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-03.
 */

@Component("userInfo")
@Scope("prototype")
public class UserInfoAction extends MyActionSupport implements ModelDriven<UserInfoVO> {

    private UserInfoVO userInfoVO = new UserInfoVO();
    private UserInfoService userInfoService;
    private DepartmentService departmentService;

    private static final Logger logger = LogManager.getLogger(UserInfoAction.class);

    //获取当前登录用户信息
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    @Override
    public String execute() {
        USERINFO userinfo = userInfoService.findById(userInfoVO.getUSERID());
        userinfo.setDEFAULTDEPTID(Integer.valueOf(userInfoVO.getDepId()));
        userInfoService.update(userinfo);
        DEPARTMENTS department = departmentService.findByID(Integer.valueOf(userInfoVO.getDepId()));
        logger.info("管理员：" + user.getUserId() + " 将用户" + userinfo.getNAME() + "的部门调整为"
                + department.getDEPTNAME() + "。IP:" + ip);
        userInfoVO.setResultMessage("<script>alert('员工：" + userinfo.getNAME() + ",成功调整到"
                + department.getDEPTNAME() + "');location.href='/KaoQin/page/user/person.jsp';</script>");
        return SUCCESS;
    }

    @Override
    public UserInfoVO getModel() {
        return userInfoVO;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Resource(name = "departmentServiceImpl")
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
