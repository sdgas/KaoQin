package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.VacationInfo;

import java.util.List;

/**
 * Created by 斌 on 2015/4/5.
 */
public interface VacationInfoService extends DAO{

    public List<VacationInfo> findByUserAndDate(int userId, String date);
}
