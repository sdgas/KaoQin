package org.sdgas.service.Impl;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.model.VacationInfo;
import org.sdgas.service.VacationInfoService;
import org.sdgas.util.WebTool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015-04-14.
 */
public class VacationInfoServiceImplTest {

    private static ApplicationContext ac;
    private static VacationInfoService vacationInfoService;
    private VacationInfo vacationInfo;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            vacationInfoService = (VacationInfoService) ac.getBean("vacationInfoServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByUserAndDate(){
        vacationInfo = vacationInfoService.findByUserAndDate(62,"2015-05-21");
        Assert.assertEquals(null, vacationInfo);
    }

}