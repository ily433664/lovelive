package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IOperationLogDao;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("operationLogService")
@Transactional(rollbackFor = Exception.class)
public class OperationLogServiceImpl extends BaseService implements IOperationLogService {

    private IOperationLogDao operationLogDao;

    @Autowired
    public OperationLogServiceImpl(IOperationLogDao operationLogDao) {
        this.operationLogDao = operationLogDao;
    }

    @Override
    public OperationLog saveOperationLog(OperationLog operationLog) {
        return operationLogDao.save(operationLog);
    }
}
