package com.lovelive.sys.service;

import com.lovelive.sys.entity.MessageLog;

/**
 * 消息通知日志 service
 *
 * @author dHe
 */
public interface IMessageLogService {

    MessageLog saveMessageLog(MessageLog messageLog);
}
