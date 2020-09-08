package com.lovelive.modules.lottery.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.modules.lottery.entity.Lottery;
import org.springframework.stereotype.Repository;

@Repository
public interface ILotteryDAO extends BaseDao<Lottery, Long> {

    Lottery getLotteryById(Long id);

}