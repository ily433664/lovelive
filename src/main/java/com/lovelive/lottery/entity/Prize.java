package com.lovelive.lottery.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.lottery.enums.PrizeLevelEnums;
import com.lovelive.sys.entity.AnnexFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 奖励
 * @Author dHe
 * @Date 2019/5/9
 */
@Entity
@Table(name = "t_prize")
public class Prize extends BaseEntity {

    private static final long serialVersionUID = 3259783856081905819L;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    @Lob
    private String description;

    /**
     * 奖励等级
     */
    private int prizeLevel = PrizeLevelEnums.N.getValue();

    /**
     * 图片
     */
    @OneToOne(fetch = FetchType.EAGER)
    private AnnexFile picture;

    /**
     * 奖品概率
     */
    @OneToMany(mappedBy = "prize", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<PrizeChance> prizeChances = new ArrayList<>();

    public Prize() {
        super();
    }

    public Prize(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrizeLevel() {
        return prizeLevel;
    }

    public void setPrizeLevel(int prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    public AnnexFile getPicture() {
        return picture;
    }

    public void setPicture(AnnexFile picture) {
        this.picture = picture;
    }

    public List<PrizeChance> getPrizeChances() {
        return prizeChances;
    }

    public void setPrizeChances(List<PrizeChance> prizeChances) {
        this.prizeChances = prizeChances;
    }
}
