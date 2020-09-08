package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionDAO extends BaseDao<Permission, Long> {
}
