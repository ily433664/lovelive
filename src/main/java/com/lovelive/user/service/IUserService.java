package com.lovelive.user.service;

import com.lovelive.user.entity.User;

public interface IUserService {

    User getUserById(int id);

    User getUserByAccount(String account);

    User getUserByUserName(String userName);

    int deleteUserById(int id);

    int deleteUserByAccount(String account);

    int insertUser(User user);

    int updateUserById(User user);

    int updateUserByAccount(User user);

    User getUserRoleByAccount(String account);

    boolean existedAccount(String account);

    boolean existedUserName(String userName);

    boolean existedAccountRole(String account, String roleCode);
}

