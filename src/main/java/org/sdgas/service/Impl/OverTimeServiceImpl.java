package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Overtime;
import org.sdgas.service.OverTimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 斌 on 2015/4/5.
 */
@Service
@Transactional
public class OverTimeServiceImpl extends DaoSupport<Overtime> implements OverTimeService {

    @Override
    public List<Overtime> findByUserAndDate(int userId, String date) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userinfo", userId);
        params.put("day", date);
        return this.findByFields(Overtime.class, params);
    }
}
