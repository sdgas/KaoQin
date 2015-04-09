package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.DEPARTMENTS;
import org.sdgas.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-03.
 */

@Service
@Transactional
public class DepartmentServiceImpl extends DaoSupport<DEPARTMENTS> implements DepartmentService {

    @Override
    public List<DEPARTMENTS> findAll() {
        return this.find(DEPARTMENTS.class);
    }

    @Override
    public DEPARTMENTS findByID(int depId) {
        return this.find(DEPARTMENTS.class, depId);
    }

    @Override
    public DEPARTMENTS findByDepName(String dep) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("DEPTNAME", dep);
        return (DEPARTMENTS) this.findSpecialObject(DEPARTMENTS.class, params);
    }
}
