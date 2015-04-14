package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.CHECKINOUT;

import java.util.List;


/**
 * Created by 120378 on 2015-04-14.
 */
public interface CheckInOutService extends DAO {

    public List<CHECKINOUT> findByUserAndDate(int userId, String ym,int day);
}
