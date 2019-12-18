package com.lovelive.lottery.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.lottery.entity.PrizeRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrizeRecordDao extends BaseDao<PrizeRecord, String> {

}