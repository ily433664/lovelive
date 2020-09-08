package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IUserDAO;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService {

    private IUserDAO userDAO;

    @Autowired
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByAccount(String account) {
        return userDAO.getUserByAccount(account);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public int deleteUserById(Long id) {
        return userDAO.deleteUserById(id);
    }

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public boolean existedAccount(String account) {
        return (userDAO.existedAccount(account) != null);
    }

    @Override
    public boolean existedUsername(String username) {
        return (userDAO.existedUsername(username) != null);
    }

    @Override
    public boolean existedAccountRole(String account, String roleCode) {
        return (userDAO.existedAccountRole(account, roleCode) != null);
    }
}
