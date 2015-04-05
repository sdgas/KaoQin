package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Vacation;
import org.sdgas.service.VacationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 斌 on 2015/4/5.
 */
@Service
@Transactional
public class VacationServiceImpl extends DaoSupport<Vacation> implements VacationService {
    @Override
    public List<Vacation> findAll() {
        return this.find(Vacation.class);
    }
}
