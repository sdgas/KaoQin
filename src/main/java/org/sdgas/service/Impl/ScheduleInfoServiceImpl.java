package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.ScheduleInfo;
import org.sdgas.service.ScheduleInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-08.
 */

@Service
@Transactional
public class ScheduleInfoServiceImpl extends DaoSupport<ScheduleInfo> implements ScheduleInfoService {

    @Override
    public ScheduleInfo findByUserAndDate(int userId, String schedule) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userinfo", userId);
        params.put("scheduleDate", schedule);
        return (ScheduleInfo) this.findSpecialObject(ScheduleInfo.class, params);
    }

    @Override
    public List<ScheduleInfo> findByDepAndDate(int depId, String year) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("depId", depId);
        params.put("scheduleDate", year);
        return this.findByFields(ScheduleInfo.class, params);
    }
}
