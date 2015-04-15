package org.sdgas.service.Impl;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.model.Overtime;
import org.sdgas.service.OverTimeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015-04-14.
 */
public class OverTimeServiceImplTest {

    private static ApplicationContext ac;
    private static OverTimeService overTimeService;
    private Overtime overtime;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            overTimeService = (OverTimeService) ac.getBean("overTimeServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByUserAndDate(){
        overtime = overTimeService.findByUserAndDate(20214,"2015-04-02");
        Assert.assertEquals(0,(int)2.5);
    }

}