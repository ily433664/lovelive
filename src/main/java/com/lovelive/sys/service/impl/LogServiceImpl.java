package com.lovelive.sys.service.impl;

import com.lovelive.sys.dao.ILogDao;
import com.lovelive.sys.entity.LoginLog;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("logService")
public class LogServiceImpl implements ILogService {

    private ILogDao logDao;

    @Autowired
    public LogServiceImpl(ILogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public int saveLoginLog(LoginLog loginLog) {
        return logDao.saveLoginLog(loginLog);
    }

    @Override
    public int saveOperationLog(OperationLog operLog) {
        return logDao.saveOperationLog(operLog);
    }

    @Override
    public List<OperationLog> getOperationLog() {
        return logDao.getOperationLog();
    }
}
