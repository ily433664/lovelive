package com.lovelive.user.service.impl;

import com.lovelive.user.dao.IUserDao;
import com.lovelive.user.entity.User;
import com.lovelive.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    @Autowired
    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int deleteUserByAccount(String account) {
        return userDao.deleteUserByAccount(account);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public int updateUserByAccount(User user) {
        return userDao.updateUserByAccount(user);
    }

    @Override
    public User getUserRoleByAccount(String account) {
        return userDao.getUserRoleByAccount(account);
    }

    @Override
    public boolean existedAccount(String account) {
        return (userDao.existedAccount(account) != null);
    }

    @Override
    public boolean existedUserName(String userName) {
        return (userDao.existedUserName(userName) != null);
    }

    @Override
    public boolean existedAccountRole(String account, String roleCode) {
        return (userDao.existedAccountRole(account, roleCode) != null);
    }
}
