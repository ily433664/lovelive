package com.lovelive.modules.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.modules.sys.entity.MessageLog;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageLogDAO extends BaseDao<MessageLog, Long> {
}
