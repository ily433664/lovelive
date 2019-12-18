package com.lovelive.lottery.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.AliasMethod;
import com.lovelive.lottery.dao.*;
import com.lovelive.lottery.entity.Lottery;
import com.lovelive.lottery.entity.Prize;
import com.lovelive.lottery.entity.PrizeRecord;
import com.lovelive.lottery.service.ILotteryService;
import com.lovelive.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("lotteryService")
@Transactional(rollbackFor = Exception.class)
public class LotteryServiceImpl extends BaseService implements ILotteryService {

    private ILotteryProjectDao lotteryProjectDao;

    private ILotteryDao lotteryDao;

    private IPrizeChanceDao prizeChanceDao;

    private IPrizeDao prizeDao;

    private IPrizeRecordDao prizeRecordDao;

    @Autowired
    public LotteryServiceImpl(ILotteryProjectDao lotteryProjectDao, ILotteryDao lotteryDao, IPrizeChanceDao prizeChanceDao, IPrizeDao prizeDao, IPrizeRecordDao prizeRecordDao) {
        this.lotteryProjectDao = lotteryProjectDao;
        this.lotteryDao = lotteryDao;
        this.prizeChanceDao = prizeChanceDao;
        this.prizeDao = prizeDao;
        this.prizeRecordDao = prizeRecordDao;
    }

    @Override
    public List<Prize> prizeDraw(String lotteryId, int num) {

        Optional<Lottery> optionalLottery = lotteryDao.findById(lotteryId);
        Lottery lottery;
        if (optionalLottery.isPresent()) {
            lottery = optionalLottery.get();
        } else {
            throw new NoSuchElementException("not find " + Lottery.class.getName());
        }

        List<Prize> resultList = new ArrayList<>();

        //初始化抽奖Alias
        Map<String, Prize> prizeMap = new HashMap<>();
        TreeMap<String, Double> prizeChanceMap = new TreeMap<>();
        lottery.getPrizeChances().forEach(prizeChance -> {
            prizeMap.put(prizeChance.getId(), prizeChance.getPrize());
            prizeChanceMap.put(prizeChance.getId(), prizeChance.getChance());
        });

        List<Double> list = new ArrayList<>(prizeChanceMap.values());
        List<String> gifts = new ArrayList<>(prizeChanceMap.keySet());
        AliasMethod aliasMethod = new AliasMethod(new ArrayList<>(list));

        //抽奖
        List<PrizeRecord> prizeRecordList = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            int index = aliasMethod.next();
            String key = gifts.get(index);
            if (!prizeMap.containsKey(key)) {
                Prize prize = prizeMap.get(key);
                resultList.add(prize);
                prizeRecordList.add(new PrizeRecord(UserUtils.getUser(), prize, lottery));
            } else {
                throw new RuntimeException("系统出错");
            }
        }
        prizeRecordDao.saveAll(prizeRecordList);

        return resultList;
    }

}
