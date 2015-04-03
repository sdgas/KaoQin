package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.DEPARTMENTS;

import java.util.List;

/**
 * Created by 120378 on 2015-04-03.
 */
public interface DepartmentService extends DAO {

    /**
     * 编列全部部门
     *
     * @return
     */
    public List<DEPARTMENTS> findAll();
}
