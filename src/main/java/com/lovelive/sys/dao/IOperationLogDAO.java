package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.OperationLog;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationLogDAO extends BaseDao<OperationLog, Long> {

    OperationLog getOperationLogById(Long id);

}
