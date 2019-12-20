package com.lovelive.sys.service;

import com.lovelive.sys.entity.OperationLog;
import org.springframework.data.domain.Page;

/**
 * 操作日志 service
 *
 * @author dhe
 * @date 2018-1-18
 */
public interface IOperationLogService {

    OperationLog getOperationLog(String id);

    OperationLog saveOperationLog(OperationLog operationLog);

    Page<OperationLog> find(OperationLog operationLog);

}
