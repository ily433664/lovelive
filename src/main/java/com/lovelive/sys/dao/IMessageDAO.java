package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageDAO extends BaseDao<Message, Long> {
}
