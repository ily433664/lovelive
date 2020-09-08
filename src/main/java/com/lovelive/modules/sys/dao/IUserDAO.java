package com.lovelive.modules.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.modules.sys.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends BaseDao<User, Long> {

    User getUserById(Long id);

    User getUserByAccount(String account);

    User getUserByUsername(String username);

    int deleteUserById(Long id);

    @Query(nativeQuery = true, value = " select 1 from t_user u where u.account=:account ")
    Integer existedAccount(@Param("account") String account);

    @Query(nativeQuery = true, value = " select 1 from t_user u where u.username=:username ")
    Integer existedUsername(@Param("username") String username);
    
}