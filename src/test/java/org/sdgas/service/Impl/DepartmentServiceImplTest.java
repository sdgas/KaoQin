package org.sdgas.service.Impl;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.model.DEPARTMENTS;
import org.sdgas.service.DepartmentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015/8/31.
 */
public class DepartmentServiceImplTest {

    private static ApplicationContext ac;
    private static DepartmentService departmentService;
    private static DEPARTMENTS department;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            departmentService = (DepartmentService) ac.getBean("departmentServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByDepName() {
        department = departmentService.findByDepName("生产运行部");
        Assert.assertEquals(7, department.getDEPTID());
    }
}