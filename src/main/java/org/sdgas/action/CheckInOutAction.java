package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.CheckInOutVO;
import org.sdgas.base.PageView;
import org.sdgas.model.Administrators;
import org.sdgas.model.CHECKINOUT;
import org.sdgas.model.USERINFO;
import org.sdgas.service.CheckInOutService;
import org.sdgas.service.UserInfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 120378 on 2015/6/17.
 */
@Component("checkInOut")
@Scope("prototype")
public class CheckInOutAction extends MyActionSupport implements ModelDriven<CheckInOutVO> {

    CheckInOutVO checkInOutVO = new CheckInOutVO();
    private CheckInOutService checkInOutService;
    private UserInfoService userInfoService;

    private static final Logger logger = LogManager.getLogger(CheckInOutAction.class);

    //获取当前登录用户信息
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    //todo:待完善
    @Override
    public String execute() {
        /** 每页显示的结果数 **/
        int maxResult = 25;
        /** 封装的页面数据 **/
        PageView<CHECKINOUT> pageView = new PageView<CHECKINOUT>(maxResult,
                checkInOutVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("USERID", "DESC");
        /** 列表条件 **/
        StringBuffer jpql = new StringBuffer("");

        USERINFO userinfo = userInfoService.findByName(checkInOutVO.getUserInfo());
        jpql.append("USERID='" + userinfo.getUSERID()).append("'");
        checkInOutVO.setUserName(userinfo.getNAME());
        checkInOutVO.setNum(userinfo.getBADGENUMBER());

        String tem = checkInOutVO.getMonth().substring(5, 7);
        int month = Integer.valueOf(tem) - 1;
        String before;
        String after = checkInOutVO.getMonth().replace("年", "-").replace("月", "-") + "15";
        if (month == 0) {
            int year = Integer.valueOf(checkInOutVO.getMonth().substring(0, 4)) - 1;
            before = year + "-12-16";
        } else {
            before = checkInOutVO.getMonth().substring(0, 4) + "-" + month + "-16";
        }

        jpql.append(" AND CHECKTIME>'" + before + " 00:00:00' AND CHECKTIME<'" + after + " 23:59:59'");
        checkInOutVO.setMonth(checkInOutVO.getMonth());

        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(checkInOutService.getScrollData(CHECKINOUT.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        checkInOutVO.setPageView(pageView);

        session.setAttribute("month", checkInOutVO.getMonth());
        session.setAttribute("userinfo", checkInOutVO.getUserInfo());

        view = "/page/search/searchDetail.jsp";
        return VIEW;
    }

    @Override
    public CheckInOutVO getModel() {
        return checkInOutVO;
    }

    @Resource(name = "checkInOutServiceImpl")
    public void setCheckInOutService(CheckInOutService checkInOutService) {
        this.checkInOutService = checkInOutService;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
}
