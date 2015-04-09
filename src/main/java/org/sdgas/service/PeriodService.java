package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Period;

/**
 * Created by 120378 on 2015-04-08.
 */
public interface PeriodService extends DAO {

    /**
     * 查找时间段
     *
     * @param depId  部门编号
     * @param symbol 编号
     * @param remark 备注
     * @return
     */
    public Period findByDepAndSymbol(int depId, String symbol, String remark);

}
