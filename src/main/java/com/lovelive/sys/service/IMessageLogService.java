package com.lovelive.sys.service;

import com.lovelive.sys.entity.MessageLog;

/**
 * 消息通知日志 service
 *
 * @author dHe
 * @date 2019-4-26
 */
public interface IMessageLogService {

    MessageLog saveMessageLog(MessageLog messageLog);
}
