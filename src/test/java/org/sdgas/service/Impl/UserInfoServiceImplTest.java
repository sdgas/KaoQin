package org.sdgas.service.Impl;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.model.USERINFO;
import org.sdgas.service.UserInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015-04-03.
 */
public class UserInfoServiceImplTest {

    private static ApplicationContext ac;
    private static UserInfoService userInfoService;
    private USERINFO userinfo;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            userInfoService = (UserInfoService) ac.getBean("userInfoServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByName() {
        userinfo = userInfoService.findByName("**");
        Assert.assertEquals("**", userinfo.getNAME());
    }

    @Test
    public void testFindById() {
        userinfo = userInfoService.findById("20378");
        Assert.assertEquals("何斌", userinfo.getNAME());
    }

    @Test
    public void testFindByDep(){
        List<USERINFO> userinfos = userInfoService.findByDep(2);
        Assert.assertEquals(0, userinfos.size());
    }
}