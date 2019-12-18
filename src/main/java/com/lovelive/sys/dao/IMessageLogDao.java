package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.MessageLog;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageLogDao extends BaseDao<MessageLog, String> {
}
