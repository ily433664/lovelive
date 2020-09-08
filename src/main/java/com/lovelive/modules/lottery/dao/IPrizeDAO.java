package com.lovelive.modules.lottery.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.modules.lottery.entity.Prize;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrizeDAO extends BaseDao<Prize, Long> {

}