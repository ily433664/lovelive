package com.lovelive.sys.service;

import com.lovelive.sys.entity.Message;

/**
 * 消息 service
 *
 * @author dHe
 * @date 2019-4-26
 */
public interface IMessageService {

    Message saveMessage(Message message);
}
