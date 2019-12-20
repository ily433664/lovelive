package com.lovelive.lottery.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;

/**
 * 奖品概率
 *
 * @author dHe
 * @date 2019-5-9
 */
@Entity
@Table(name = "t_prize_chance")
public class PrizeChance extends BaseEntity {

    private static final long serialVersionUID = 7955761296470806889L;

    /**
     * 概率
     */
    private double chance = 0d;

    /**
     * 所属抽奖
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Lottery lottery;

    /**
     * 奖品
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Prize prize;

    public PrizeChance() {
        super();
    }

    public PrizeChance(String id) {
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
