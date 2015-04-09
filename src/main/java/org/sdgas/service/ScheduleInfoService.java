package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.ScheduleInfo;

/**
 * Created by 120378 on 2015-04-08.
 */
public interface ScheduleInfoService extends DAO {

    /**
     * 查找排班情况
     *
     * @param userId   用户编号
     * @param schedule 时间
     * @return 排班情况
     */
    public ScheduleInfo findByUserAndDate(int userId, String schedule);
}
