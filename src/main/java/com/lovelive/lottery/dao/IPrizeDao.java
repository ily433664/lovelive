package com.lovelive.lottery.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.lottery.entity.Prize;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrizeDao extends BaseDao<Prize, String> {

}