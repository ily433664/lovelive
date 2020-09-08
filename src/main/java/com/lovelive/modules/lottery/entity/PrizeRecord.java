package com.lovelive.modules.lottery.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.modules.sys.entity.User;

import javax.persistence.*;

/**
 * 奖品记录
 *
 * @author dHe
 */
@Entity(name = "t_prize_record")
public class PrizeRecord extends BaseEntity {

    private static final long serialVersionUID = 4521568068014986485L;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    /**
     * 奖品
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Prize prize;

    /**
     * 所属奖池
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Lottery lottery;


    public PrizeRecord() {
        super();
    }

    public PrizeRecord(Long id) {
        super(id);
    }

    public PrizeRecord(User user, Prize prize, Lottery lottery) {
        super();
        this.user = user;
        this.prize = prize;
        this.lottery = lottery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }
}
