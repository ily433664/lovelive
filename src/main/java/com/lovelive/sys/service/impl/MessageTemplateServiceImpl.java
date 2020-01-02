package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IMessageTemplateDao;
import com.lovelive.sys.entity.MessageTemplate;
import com.lovelive.sys.service.IMessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageTemplateService")
@Transactional(rollbackFor = Exception.class)
public class MessageTemplateServiceImpl extends BaseService implements IMessageTemplateService {

    private IMessageTemplateDao messageTemplateDao;

    @Autowired
    public MessageTemplateServiceImpl(IMessageTemplateDao messageTemplateDao) {
        this.messageTemplateDao = messageTemplateDao;
    }

    @Override
    public MessageTemplate saveMessageTemplate(MessageTemplate messageTemplate) {
        return messageTemplateDao.save(messageTemplate);
    }
}
