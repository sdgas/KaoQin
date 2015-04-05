package org.sdgas.ajax;

import com.opensymphony.xwork2.ActionSupport;
import org.sdgas.model.DEPARTMENTS;
import org.sdgas.service.DepartmentService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 斌 on 2015/4/5.
 */

@Component("departmentAjax")
@Scope("prototype")
public class DepartmentAjax extends ActionSupport {

    private DepartmentService departmentService;
    private String depName;
    private String depId;

    @Override
    public String execute() {
        DEPARTMENTS department = departmentService.findByID(Integer.valueOf(depId));
        depName = department.getDEPTNAME();
        return SUCCESS;
    }

    @Resource(name = "departmentServiceImpl")
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }
}
