package com.lovelive.modules.sys.service;

import com.lovelive.modules.sys.entity.OperationLog;
import org.springframework.data.domain.Page;

/**
 * 操作日志 service
 *
 * @author dhe
 */
public interface IOperationLogService {

    OperationLog getOperationLog(Long id);

    OperationLog saveOperationLog(OperationLog operationLog);

    void deleteOperationLog(OperationLog operationLog);

    Page<OperationLog> find(OperationLog operationLog);

}
