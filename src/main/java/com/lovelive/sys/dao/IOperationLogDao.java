package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.OperationLog;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationLogDao extends BaseDao<OperationLog, String> {

    OperationLog getOperationLogById(String id);

}
