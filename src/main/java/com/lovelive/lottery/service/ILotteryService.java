package com.lovelive.lottery.service;

import com.lovelive.lottery.entity.Prize;

import java.util.List;

/**
 * lottery service
 *
 * @author dHe
 * @date 2019-8-9
 */
public interface ILotteryService {

    /**
     * 抽奖
     *
     * @param lotteryId 抽奖池id
     * @param num       抽奖次数
     * @return 奖品列表
     */
    List<Prize> prizeDraw(String lotteryId, int num);

}
