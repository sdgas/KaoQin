package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.CHECKINOUT;
import org.sdgas.service.CheckInOutService;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.WebTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by 120378 on 2015-04-14.
 */
@Service
@Transactional
public class CheckInOutServiceImpl extends DaoSupport<CHECKINOUT> implements CheckInOutService {

    @Override
    public List<CHECKINOUT> findByUserAndDate(int userId, String ym, int day) {
        Date one = ChangeTime.parseShortDate(ym + "-" + day + " 00:00:00");
        Date two = ChangeTime.parseShortDate(ym + "-" + day + " 23:59:59");
        Query query = em.createQuery("select o from CHECKINOUT o where o.USERID=?1 and o.CHECKTIME between ?2 AND ?3");
        query.setParameter(1, userId);
        query.setParameter(2, one);
        query.setParameter(3, two);
        return query.getResultList();
    }
}
