package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IUserDao;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService {

    private IUserDao userDao;

    @Autowired
    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(Long id) {
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
    public int deleteUserById(Long id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int deleteUserByAccount(String account) {
        return userDao.deleteUserByAccount(account);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
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
