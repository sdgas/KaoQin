package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.VacationInfo;
import org.sdgas.service.VacationInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by 斌 on 2015/4/5.
 */
@Service
@Transactional
public class VacationInfoServiceImpl extends DaoSupport<VacationInfo> implements VacationInfoService {


    @Override
    public VacationInfo findByUserAndDate(int userId, String date) {
        Query query = em.createQuery("select o from VacationInfo o where o.userinfo=?1 and o.remarks LIKE '%" + date + "%'");
        query.setParameter(1, userId);
        try {
            return (VacationInfo) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
