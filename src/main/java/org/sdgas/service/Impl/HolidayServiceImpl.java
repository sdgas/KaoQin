package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Holiday;
import org.sdgas.service.HolidayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 斌 on 2015/4/5.
 */
@Service
@Transactional
public class HolidayServiceImpl extends DaoSupport<Holiday> implements HolidayService {
    @Override
    public Holiday findByName(String holiday) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("holidayName", holiday);
        return (Holiday) this.findSpecialObject(Holiday.class, params);
    }
}
