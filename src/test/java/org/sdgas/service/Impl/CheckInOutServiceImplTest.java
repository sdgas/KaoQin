package org.sdgas.service.Impl;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.model.CHECKINOUT;
import org.sdgas.service.CheckInOutService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015-04-14.
 */
public class CheckInOutServiceImplTest {

    private static ApplicationContext ac;
    private static CheckInOutService checkInOutService;
    private List<CHECKINOUT> checkinouts;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            checkInOutService = (CheckInOutService) ac.getBean("checkInOutServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByUserAndDate(){
        checkinouts = checkInOutService.findByUserAndDate(15,"2015-03",31);
        Assert.assertEquals(2,checkinouts.size());
    }

}