package com.lovelive.user.dao;

import com.lovelive.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//@CacheConfig(cacheNames = "users")
@Repository
@Mapper
public interface IUserDao {

    User getUserById(Integer id);

    //@CachePut(key = "#p0")
    User getUserByAccount(String account);

    User getUserByUserName(String userName);

    int deleteUserById(Integer id);

    int deleteUserByAccount(String account);

    int insertUser(User user);

    int updateUserById(User user);

    int updateUserByAccount(User user);

    User getUserRoleByAccount(String account);

    Object existedAccount(@Param("account") String account);

    Object existedUserName(@Param("userName") String userName);

    Object existedAccountRole(@Param("account") String account, @Param("roleCode") String roleCode);
}