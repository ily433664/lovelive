package com.lovelive.modules.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.modules.sys.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageDAO extends BaseDao<Message, Long> {
}
