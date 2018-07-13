package com.lovelive.sys.service;

import com.lovelive.sys.entity.LoginLog;
import com.lovelive.sys.entity.OperationLog;

import java.util.List;

public interface ILogService {

    int saveLoginLog(LoginLog loginLog);

    int saveOperationLog(OperationLog operLog);

    List<OperationLog> getOperationLog();

}

