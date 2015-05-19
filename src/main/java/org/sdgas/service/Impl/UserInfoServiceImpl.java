package org.sdgas.service.Impl;

import org.sdgas.VO.UserInfoVO;
import org.sdgas.base.DaoSupport;
import org.sdgas.model.USERINFO;
import org.sdgas.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 120378 on 2015-04-03.
 */

@Service
@Transactional
public class UserInfoServiceImpl extends DaoSupport<USERINFO> implements UserInfoService {

    @Override
    public USERINFO findByName(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("NAME", name);
        return (USERINFO) this.findSpecialObject(USERINFO.class, params);
    }

    @Override
    public USERINFO findById(String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("BADGENUMBER", userId);
        return (USERINFO) this.findSpecialObject(USERINFO.class, params);
    }

    @Override
    public List<USERINFO> findByDep(int depId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("DEFAULTDEPTID", depId);
        return this.findByFields(USERINFO.class, params);
    }

    @Override
    public List<USERINFO> findAll() {
        return this.find(USERINFO.class);
    }
}
