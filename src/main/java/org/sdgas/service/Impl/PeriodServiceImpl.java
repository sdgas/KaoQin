package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Period;
import org.sdgas.service.PeriodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-08.
 */

@Service
@Transactional
public class PeriodServiceImpl extends DaoSupport<Period> implements PeriodService {


    @Override
    public Period findByDepAndSymbol(int depId, String symbol, String remark) {
        int flag = -1;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("depId", depId);
        params.put("symbol", symbol);
        List<Period> periods = this.findByFields(Period.class, params);
        for (int i = 0; i < periods.size(); i++) {
            if (periods.get(i).getRemark().contains(remark)) {
                flag = i;
                break;
            }

        }
        return flag == -1 ? periods.size() == 1 ? periods.get(0) : null : periods.get(flag);
    }
}
