package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.AnnualLeave;

/**
 * Created by 120378 on 2015-04-09.
 */
public interface AnnualLeaveService extends DAO {

    /**
     * 查找年假信息
     *
     * @param userId 用户编号
     * @return 年假
     */
    public AnnualLeave findByUser(int userId);
}
