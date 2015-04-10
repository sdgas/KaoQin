package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.AnnualLeave;
import org.sdgas.service.AnnualLeaveService;
import org.sdgas.util.ChangeTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-09.
 */
@Service
@Transactional
public class AnnualLeaveServiceImpl extends DaoSupport<AnnualLeave> implements AnnualLeaveService {
    @Override
    public AnnualLeave findByUser(int userId) {
        String date = ChangeTime.formatRealDate(new Date()).split("-")[0];
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("year", date);
        return (AnnualLeave) this.findSpecialObject(AnnualLeave.class, params);
    }
}
