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

    private List<String> userNames = new ArrayList<String>();

    private String userId;

    @Override
    public String execute() throws IOException {
        USERINFO userinfo = userInfoService.findById(userId);
        userNames.add(userinfo.getNAME());
        return SUCCESS;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
