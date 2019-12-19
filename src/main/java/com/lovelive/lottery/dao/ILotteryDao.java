package com.lovelive.lottery.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.lottery.entity.Lottery;
import org.springframework.stereotype.Repository;

@Repository
public interface ILotteryDao extends BaseDao<Lottery, String> {

    Lottery getLotteryById(String id);

}