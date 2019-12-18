package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IMessageLogDao;
import com.lovelive.sys.entity.MessageLog;
import com.lovelive.sys.service.IMessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageLogService")
@Transactional(rollbackFor = Exception.class)
public class MessageLogServiceImpl extends BaseService implements IMessageLogService {

    private IMessageLogDao messageLogDao;

    @Autowired
    public MessageLogServiceImpl(IMessageLogDao messageLogDao){
        this.messageLogDao = messageLogDao;
    }

    @Override
    public MessageLog saveMessageLog(MessageLog messageLog) {
        return messageLogDao.save(messageLog);
    }
}
