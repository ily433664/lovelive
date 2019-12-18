package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends BaseDao<User, String> {

    User getUserById(Long id);

    User getUserByAccount(String account);

    User getUserByUserName(String userName);

    int deleteUserById(Long id);

    int deleteUserByAccount(String account);

    User getUserRoleByAccount(String account);

    @Query(" select 1 from User u where u.account=:account ")
    Object existedAccount(@Param("account") String account);

    @Query(" select 1 from User u where u.userName=:userName ")
    Object existedUserName(@Param("userName") String userName);

    @Query(" select 1 from UserRole ur where ur.user.account=:account and ur.role.code=:roleCode ")
    Object existedAccountRole(@Param("account") String account, @Param("roleCode") String roleCode);
}