package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Report;

/**
 * Created by 120378 on 2015-04-15.
 */
public interface ReportService extends DAO {


    /**
     *
     * @param fileName
     * @return
     */
    public Report fineByFileName(String fileName);
}
