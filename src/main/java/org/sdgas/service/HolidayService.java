package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Holiday;

import java.util.List;

/**
 * Created by 斌 on 2015/4/5.
 */
public interface HolidayService extends DAO {

    /**
     * 查找节假日
     *
     * @param holiday 节假日名称
     * @return
     */
    public Holiday findByName(String holiday);

    /**
     * @param before YYYY-MM-dd
     * @param after  YYYY-MM-dd
     * @return
     */
    public List<Holiday> findByDate(String before, String after);
}
