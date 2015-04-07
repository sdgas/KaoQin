package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.OverTimeVO;
import org.sdgas.model.Administrators;
import org.sdgas.model.Overtime;
import org.sdgas.service.OverTimeService;
import org.sdgas.util.ChangeTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 斌 on 2015/4/5.
 */
@Component("overTime")
@Scope("prototype")
public class OverTimeAction extends MyActionSupport implements ModelDriven<OverTimeVO> {

    private OverTimeVO overTimeVO = new OverTimeVO();
    private OverTimeService overTimeService;

    private static final Logger logger = LogManager.getLogger(OverTimeAction.class);

    //获取当前登录用户信息
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");


    @Override
    public String execute() {
        Overtime overTime = new Overtime();
        overTime.setBeginTime(ChangeTime.parseDate(overTimeVO.getBeginTime()));
        overTime.setEndTime(ChangeTime.parseDate(overTimeVO.getEndTime()));
        overTime.setLongTime(Double.valueOf(overTimeVO.getLongTime()));
        overTime.setUserinfo(Integer.valueOf(overTimeVO.getUserinfo()));
        overTimeService.save(overTime);
        logger.info("管理员：" + user.getUserId() + " 添加了一条加班记录(" + overTime.getId() + ")。IP:" + ip);
        overTimeVO.setResultMessage("<script>alert('添加成功！');location.href='/page/ot/apply.jsp';</script>");
        return SUCCESS;
    }

    @Override
    public OverTimeVO getModel() {
        return overTimeVO;
    }

    @Resource(name = "overTimeServiceImpl")
    public void setOverTimeService(OverTimeService overTimeService) {
        this.overTimeService = overTimeService;
    }
}