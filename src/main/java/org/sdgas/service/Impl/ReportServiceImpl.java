package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.Report;
import org.sdgas.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-15.
 */

@Service
@Transactional
public class ReportServiceImpl extends DaoSupport<Report> implements ReportService {
    @Override
    public Report fineByFileName(String fileName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("filePath", fileName);
        return (Report) this.findSpecialObject(Report.class, params);
    }
}
