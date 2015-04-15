package org.sdgas.service.Impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.model.Period;
import org.sdgas.service.PeriodService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015-04-08.
 */
public class PeriodServiceImplTest {

    private static ApplicationContext ac;
    private static PeriodService periodService;
    private Period period;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            periodService = (PeriodService) ac.getBean("periodServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByDepAndSymbol(){
        period = periodService.find(Period.class,21);
        System.out.println(period);
    }

}