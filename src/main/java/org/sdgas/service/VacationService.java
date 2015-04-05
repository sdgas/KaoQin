package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Vacation;

import java.util.List;

/**
 * Created by 斌 on 2015/4/5.
 */
public interface VacationService extends DAO{

    /**
     * 查找休假类型
     * @return
     */
    public List<Vacation> findAll();
}
