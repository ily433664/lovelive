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
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public int deleteUserById(String id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean existedAccount(String account) {
        return (userDao.existedAccount(account) != null);
    }

    @Override
    public boolean existedUsername(String username) {
        return (userDao.existedUsername(username) != null);
    }

    @Override
    public boolean existedAccountRole(String account, String roleCode) {
        return (userDao.existedAccountRole(account, roleCode) != null);
    }
}
