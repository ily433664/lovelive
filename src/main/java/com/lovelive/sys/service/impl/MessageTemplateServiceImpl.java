package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IMessageTemplateDAO;
import com.lovelive.sys.entity.MessageTemplate;
import com.lovelive.sys.service.IMessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageTemplateService")
@Transactional(rollbackFor = Exception.class)
public class MessageTemplateServiceImpl extends BaseService implements IMessageTemplateService {

    private IMessageTemplateDAO messageTemplateDAO;

    @Autowired
    public MessageTemplateServiceImpl(IMessageTemplateDAO messageTemplateDAO) {
        this.messageTemplateDAO = messageTemplateDAO;
    }

    @Override
    public MessageTemplate saveMessageTemplate(MessageTemplate messageTemplate) {
        return messageTemplateDAO.save(messageTemplate);
    }
}
