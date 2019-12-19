package com.lovelive.sys.service;

import com.lovelive.sys.entity.OperationLog;
import org.springframework.data.domain.Page;

public interface IOperationLogService {

    OperationLog getOperationLog(String id);

    OperationLog saveOperationLog(OperationLog operationLog);

    Page<OperationLog> find(OperationLog operationLog);

}
