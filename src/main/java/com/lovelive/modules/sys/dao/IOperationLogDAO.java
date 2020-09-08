package com.lovelive.modules.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.modules.sys.entity.OperationLog;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationLogDAO extends BaseDao<OperationLog, Long> {

    OperationLog getOperationLogById(Long id);

}
