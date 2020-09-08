package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends BaseDao<User, Long> {

    User getUserById(Long id);

    User getUserByAccount(String account);

    User getUserByUsername(String username);

    int deleteUserById(Long id);

    @Query(" select 1 from User u where u.account=:account ")
    Object existedAccount(@Param("account") String account);

    @Query(" select 1 from User u where u.username=:username ")
    Object existedUsername(@Param("username") String username);

    @Query(" select 1 from UserRole ur where ur.user.account=:account and ur.role.code=:roleCode ")
    Object existedAccountRole(@Param("account") String account, @Param("roleCode") String roleCode);
}