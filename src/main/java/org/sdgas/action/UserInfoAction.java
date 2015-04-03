package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.UserInfoVO;
import org.sdgas.model.USERINFO;
import org.sdgas.service.UserInfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-03.
 */

@Component("userInfo")
@Scope("prototype")
public class UserInfoAction extends MyActionSupport implements ModelDriven<UserInfoVO> {

    private UserInfoVO userInfoVO = new UserInfoVO();
    private UserInfoService userInfoService;

    @Override

    public String execute() {
        USERINFO userinfo = userInfoService.findByName(userInfoVO.getName());
        userinfo.setDEFAULTDEPTID(Integer.valueOf(userInfoVO.getDepId()));
        return SUCCESS;
    }

    public String userNameGet() throws IOException {
        String userId = ServletActionContext.getRequest().getParameter("user");
        System.out.println("userId:" + userId);
        USERINFO userinfo = userInfoService.findById(userId);
        System.out.println("userName:" + userinfo.getNAME());
        //ServletActionContext.getResponse().getWriter().println(userinfo.getNAME());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userinfo",userinfo.getNAME());
        //todo:http://www.cnblogs.com/lraa/p/3249990.html
        //JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
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
}
