package com.lovelive.modules.sys.service;

import com.lovelive.modules.sys.entity.Message;

/**
 * 消息 service
 *
 * @author dHe
 */
public interface IMessageService {

    Message saveMessage(Message message);
}
