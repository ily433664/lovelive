package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IMessageDao;
import com.lovelive.sys.entity.Message;
import com.lovelive.sys.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageService")
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends BaseService implements IMessageService {

    private IMessageDao messageDao;

    @Autowired
    public MessageServiceImpl(IMessageDao messageDao){
        this.messageDao = messageDao;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageDao.save(message);
    }
}
