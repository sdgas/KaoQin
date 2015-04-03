package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.UserInfoVO;
import org.sdgas.model.Administrators;
import org.sdgas.model.USERINFO;
import org.sdgas.service.AdministratorsService;
import org.sdgas.util.SystemHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 120378 on 2015-03-05.
 */
@Component("login")
@Scope("prototype")
public class LoginAction extends MyActionSupport implements ModelDriven<UserInfoVO> {

    private UserInfoVO userVO = new UserInfoVO();
    private AdministratorsService administratorsService;

    private static String ip = SystemHelper.getIpAddress();

    private static final Logger logger = LogManager.getLogger(LoginAction.class);

    //用户登录
    @Override
    public String execute() throws Exception {
        if (userVO.getUSERID().isEmpty() || userVO.getPwd().isEmpty()) {
            userVO.setResultMessage("<script>alert('请填写佛燃工号或密码！');location.href='login.jsp';</script>");
            return ERROR;
        }

        Administrators user = administratorsService.findById(Integer.valueOf(userVO.getUSERID()));
        if (user == null) {
            userVO.setResultMessage("<script>alert('用户不存在！');location.href='login.jsp';</script>");
            return ERROR;
        }
        logger.entry(user.getUserId());

        if (userVO.getPwd().equals(user.getPwd())) {

            storePersonToSession(user);
            logger.info("用户:" + user.getUserId() + ",登录系统 IP:" + ip);
            HttpServletRequest request = ServletActionContext.getRequest();
            Object obj = request.getSession().getAttribute("requestURI");
            if (obj != null) {
                view = obj.toString();
            } else {
                view = "/index.jsp";
                return VIEW;
            }
            logger.trace(obj.toString());
            return VIEW;
        }
        logger.info("用户 " + user.getUserId() + "登录密码错误. IP:" + ip);
        logger.exit(user.getUserId());
        userVO.setResultMessage("<script>alert('密码错误！');location.href='login.jsp';</script>");
        return ERROR;
    }

    //退出登录
    public String loginOut() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Administrators person = (Administrators) session.getAttribute("person");
        if (person != null) {
            logger.info("用户 " + person.getUserId() + "登出系统。IP:" + ip);
            session.removeAttribute("person");
            //session.invalidate();
        }
        view = "/login.jsp";
        return VIEW;
    }


    //修改密码
    /*public String alterPwd() {

        USERINFO user = userService.findById(userVO.getUserId());
        if (!user.getPwd().equals(userVO.getPwd())) {
            userVO.setResultMessage("<script>alert('原密码错误');location.href='/page/user/alterPwd.jsp';</script>");
            logger.info(user.getPosition().getPositionName() + ":" + user.getUserName() + " 修改用户密码，原密码错误）操作IP：" + ip);
            return ERROR;
        } else {
            user.setPwd(userVO.getPwd2());
            userService.update(user);
            userVO.setResultMessage("<script>alert('修改密码成功！请重新登录系统');location.href='login.jsp';</script>");
            logger.info(user.getPosition().getPositionName() + ":" + user.getUserName() + " 修改用户密码成功。操作IP：" + ip);
            return SUCCESS;
        }
    }*/

    @Override
    public UserInfoVO getModel() {
        return userVO;
    }

    @Resource(name = "administratorsServiceImpl")
    public void setAdministratorsService(AdministratorsService administratorsService) {
        this.administratorsService = administratorsService;
    }


    private void storePersonToSession(Administrators target) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Administrators storedPerson = (Administrators) session.getAttribute("person");
        if (!ip.trim().equals("") && (storedPerson == null || !storedPerson.equals(target))) {
            session.setAttribute("person", target);
            session.setAttribute("ip", ip);
            session.setAttribute("loginKey", "success");
            session.setMaxInactiveInterval(-1);
        }
    }
}