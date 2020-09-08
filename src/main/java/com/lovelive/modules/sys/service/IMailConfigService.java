package com.lovelive.modules.sys.service;

import com.lovelive.modules.sys.entity.MailConfig;

/**
 * 邮箱配置 service
 *
 * @author dHe
 */
public interface IMailConfigService {

    MailConfig getMailConfig();

    MailConfig saveMailConfig(MailConfig mailConfig);
}
