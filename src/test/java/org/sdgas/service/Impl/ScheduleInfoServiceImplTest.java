package org.sdgas.service.Impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.model.ScheduleInfo;
import org.sdgas.service.ScheduleInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015-04-14.
 */
public class ScheduleInfoServiceImplTest {


    private static ApplicationContext ac;
    private static ScheduleInfoService scheduleInfoService;
    private ScheduleInfo scheduleInfo;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            scheduleInfoService = (ScheduleInfoService) ac.getBean("scheduleInfoServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFindByDepAndDate() {
        List<ScheduleInfo> scheduleInfos = scheduleInfoService.findByDepAndDate(10, "201504");
        System.out.println(scheduleInfos.size());

    }

}