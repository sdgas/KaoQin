package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.CHECKINOUT;

import java.util.List;


/**
 * Created by 120378 on 2015-04-14.
 */
public interface CheckInOutService extends DAO {

    /**
     * 查找某天的打卡记录
     * @param userId 用户标志
     * @param ym 年月
     * @param day 日
     * @return
     */
    public List<CHECKINOUT> findByUserAndDate(int userId, String ym,int day);

    /**
     * 查找某个年月的打卡记录明细
     * @param userId 用户标志
     * @param ym 查询年月
     * @return 打卡记录
     */
    public List<CHECKINOUT> findByUserAndMonth(int userId,String ym);
}
