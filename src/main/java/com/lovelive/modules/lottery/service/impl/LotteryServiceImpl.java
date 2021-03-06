package com.lovelive.modules.lottery.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.AliasMethod;
import com.lovelive.modules.lottery.dao.*;
import com.lovelive.modules.lottery.entity.Lottery;
import com.lovelive.modules.lottery.entity.Prize;
import com.lovelive.modules.lottery.entity.PrizeRecord;
import com.lovelive.modules.lottery.service.ILotteryService;
import com.lovelive.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("lotteryService")
@Transactional(rollbackFor = Exception.class)
public class LotteryServiceImpl extends BaseService implements ILotteryService {

    private ILotteryProjectDAO lotteryProjectDAO;

    private ILotteryDAO lotteryDAO;

    private IPrizeChanceDAO prizeChanceDAO;

    private IPrizeDAO prizeDAO;

    private IPrizeRecordDAO prizeRecordDAO;

    @Autowired
    public LotteryServiceImpl(ILotteryProjectDAO lotteryProjectDAO, ILotteryDAO lotteryDAO, IPrizeChanceDAO prizeChanceDAO, IPrizeDAO prizeDAO, IPrizeRecordDAO prizeRecordDAO) {
        this.lotteryProjectDAO = lotteryProjectDAO;
        this.lotteryDAO = lotteryDAO;
        this.prizeChanceDAO = prizeChanceDAO;
        this.prizeDAO = prizeDAO;
        this.prizeRecordDAO = prizeRecordDAO;
    }

    @Override
    public List<Prize> prizeDraw(Long lotteryId, int num) {

        Lottery lottery = lotteryDAO.getLotteryById(lotteryId);
        if (lottery == null) {
            throw new NoSuchElementException("not find " + Lottery.class.getName());
        }

        List<Prize> resultList = new ArrayList<>();

        // 初始化抽奖 Alias
        Map<Long, Prize> prizeMap = new HashMap<>();
        TreeMap<Long, Double> prizeChanceMap = new TreeMap<>();
        lottery.getPrizeChances().forEach(prizeChance -> {
            prizeMap.put(prizeChance.getId(), prizeChance.getPrize());
            prizeChanceMap.put(prizeChance.getId(), prizeChance.getChance());
        });

        List<Double> list = new ArrayList<>(prizeChanceMap.values());
        List<Long> gifts = new ArrayList<>(prizeChanceMap.keySet());
        AliasMethod aliasMethod = new AliasMethod(new ArrayList<>(list));

        // 抽奖
        List<PrizeRecord> prizeRecordList = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            int index = aliasMethod.next();
            Long key = gifts.get(index);
            if (!prizeMap.containsKey(key)) {
                Prize prize = prizeMap.get(key);
                resultList.add(prize);
                prizeRecordList.add(new PrizeRecord(UserUtils.getUser(), prize, lottery));
            } else {
                throw new RuntimeException("系统出错");
            }
        }
        prizeRecordDAO.saveAll(prizeRecordList);

        return resultList;
    }

}
