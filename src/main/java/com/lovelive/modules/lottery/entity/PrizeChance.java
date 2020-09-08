package com.lovelive.modules.lottery.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;

/**
 * 奖品概率
 *
 * @author dHe
 */
@Entity(name = "t_prize_chance")
public class PrizeChance extends BaseEntity {

    private static final long serialVersionUID = 4030894624216921291L;

    /**
     * 概率
     */
    private double chance = 0d;

    /**
     * 所属抽奖
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Lottery lottery;

    /**
     * 奖品
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Prize prize;

    public PrizeChance() {
        super();
    }

    public PrizeChance(Long id) {
        super(id);
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}
