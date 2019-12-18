package com.lovelive.sys.service;

import com.lovelive.sys.entity.User;

public interface IUserService {

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据账号获取用户
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 根据用户名获取用户
     *
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    int deleteUserById(Long id);

    /**
     * 根据账号删除用户
     *
     * @param account
     * @return
     */
    int deleteUserByAccount(String account);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 根据账号获取UserRole
     *
     * @param account
     * @return
     */
    User getUserRoleByAccount(String account);

    /**
     * 判断是否存在账号
     *
     * @param account
     * @return
     */
    boolean existedAccount(String account);

    /**
     * 判断是否存在用户名
     *
     * @param userName
     * @return
     */
    boolean existedUserName(String userName);

    /**
     * 判断该账号是否拥有此角色
     *
     * @param account
     * @param roleCode
     * @return
     */
    boolean existedAccountRole(String account, String roleCode);
}
