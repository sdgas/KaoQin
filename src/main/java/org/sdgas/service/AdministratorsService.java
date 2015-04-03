package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Administrators;

/**
 * Created by 120378 on 2015-04-02.
 */
public interface AdministratorsService  extends DAO {

    /**
     * 根据ID查找
     * @param userId
     * @return
     */
    public Administrators findById(int userId);
}
