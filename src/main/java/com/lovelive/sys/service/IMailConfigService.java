package com.lovelive.sys.service;

import com.lovelive.sys.entity.MailConfig;

/**
 * 邮箱配置 service
 *
 * @author dHe
 * @date 2019-12-31
 */
public interface IMailConfigService {

    MailConfig getMailConfig();

    MailConfig saveMailConfig(MailConfig mailConfig);
}
