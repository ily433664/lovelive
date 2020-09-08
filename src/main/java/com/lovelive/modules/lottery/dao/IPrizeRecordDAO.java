package com.lovelive.modules.lottery.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.modules.lottery.entity.PrizeRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrizeRecordDAO extends BaseDao<PrizeRecord, Long> {

}