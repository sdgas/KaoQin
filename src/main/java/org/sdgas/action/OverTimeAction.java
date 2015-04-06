package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.sdgas.VO.OverTimeVO;
import org.sdgas.service.OverTimeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 斌 on 2015/4/5.
 */
@Component("overTime")
@Scope("prototype")
public class OverTimeAction extends MyActionSupport implements ModelDriven<OverTimeVO> {

    private OverTimeVO overTimeVO = new OverTimeVO();
    private OverTimeService overTimeService;

    @Override
    public String execute(){
        return SUCCESS;
    }

    @Override
    public OverTimeVO getModel() {
        return overTimeVO;
    }

    @Resource(name = "overTimeServiceImpl")
    public void setOverTimeService(OverTimeService overTimeService) {
        this.overTimeService = overTimeService;
    }
}
