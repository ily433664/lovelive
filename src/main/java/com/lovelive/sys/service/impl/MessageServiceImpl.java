package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IMessageDAO;
import com.lovelive.sys.entity.Message;
import com.lovelive.sys.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageService")
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends BaseService implements IMessageService {

    private IMessageDAO messageDAO;

    @Autowired
    public MessageServiceImpl(IMessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageDAO.save(message);
    }
}
