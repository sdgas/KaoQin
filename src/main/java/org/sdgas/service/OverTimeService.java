package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Overtime;

import java.util.Date;
import java.util.List;

/**
 * Created by 斌 on 2015/4/5.
 */
public interface OverTimeService extends DAO {

    /**
     * 查找加班信息
     * @param userId 员工编号
     * @param date 日期
     * @return
     */
    public List<Overtime> findByUserAndDate(int userId,String date);
}
