package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Holiday;
import org.sdgas.service.HolidayService;
import org.sdgas.util.ChangeTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
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


    @Override
    public List<Holiday> findByDate(String before, String after) {
        Query query = em.createQuery("select o from Holiday o where o.holidayBeginDate between ?1 AND ?2");
        query.setParameter(1, ChangeTime.parseShortDate(before));
        query.setParameter(2, ChangeTime.parseShortDate(after));
        return query.getResultList();
    }
}
