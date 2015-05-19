package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.USERINFO;

import java.util.List;

/**
 * Created by 120378 on 2015-04-03.
 */
public interface UserInfoService extends DAO {

    /**
     * 根据用户名查找用户
     *
     * @param name 用户名
     * @return 用户
     */
    public USERINFO findByName(String name);

    /**
     * @param userId
     * @return
     */
    public USERINFO findById(String userId);

    /**
     * 查找员工
     *
     * @param depId 部门编号
     * @return 员工信息
     */
    public List<USERINFO> findByDep(int depId);

    /**
     * 查找全部员工
     *
     * @return 员工信息
     */
    public List<USERINFO> findAll();

}
