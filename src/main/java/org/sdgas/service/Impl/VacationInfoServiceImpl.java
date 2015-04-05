package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.VacationInfo;
import org.sdgas.service.VacationInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 斌 on 2015/4/5.
 */
@Service
@Transactional
public class VacationInfoServiceImpl extends DaoSupport<VacationInfo> implements VacationInfoService {
}
