package org.sdgas.ajax;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.sdgas.model.USERINFO;
import org.sdgas.service.UserInfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 斌 on 2015/4/5.
 */

@Component("userInfoAjax")
@Scope("prototype")
public class UserInfoAjax extends ActionSupport {

    private UserInfoService userInfoService;

    private String userName;
    private List<USERINFO> userinfos;

    private String userId;
    private String dep;

    @Override
    public String execute() throws IOException {
        USERINFO userinfo = userInfoService.findById(userId);
        userName = userinfo.getNAME();
        System.out.println(userName);
        return SUCCESS;
    }

    public String getUserInfo() {
        userinfos = userInfoService.findByDep(Integer.valueOf(dep));
        return SUCCESS;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public List<USERINFO> getUserinfos() {
        return userinfos;
    }
}
