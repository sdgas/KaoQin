package org.sdgas.ajax;

import com.opensymphony.xwork2.ActionSupport;
import org.sdgas.model.USERINFO;
import org.sdgas.service.UserInfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 斌 on 2015/4/5.
 */

@Component("userInfoAjax")
@Scope("prototype")
public class UserInfoAjax extends ActionSupport {

    private UserInfoService userInfoService;


    private List<USERINFO> userinfos;

    private String dep;

    public String getUserInfo() {
        userinfos = userInfoService.findByDep(Integer.valueOf(dep));
        System.out.println(userinfos.size());
        return SUCCESS;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public List<USERINFO> getUserinfos() {
        return userinfos;
    }

}
