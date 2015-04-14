package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.VacationInfo;

/**
 * Created by 斌 on 2015/4/5.
 */
public interface VacationInfoService extends DAO{

    public VacationInfo findByUserAndDate(int userId, String date);
}
