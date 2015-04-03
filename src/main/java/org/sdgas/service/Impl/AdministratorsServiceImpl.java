package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Administrators;
import org.sdgas.service.AdministratorsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 120378 on 2015-04-02.
 */

@Service
@Transactional
public class AdministratorsServiceImpl extends DaoSupport<Administrators> implements AdministratorsService {

    public Administrators findById(int userId) {
        return this.find(Administrators.class, userId);
    }
}
