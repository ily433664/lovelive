package com.lovelive.lottery.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.lottery.enums.PrizeLevelEnums;
import com.lovelive.sys.entity.FileAttachment;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 奖励
 *
 * @author dHe
 */
@Entity(name = "t_prize")
public class Prize extends BaseEntity {

    private static final long serialVersionUID = 3635118487901429735L;

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
     * PrizeLevelEnums
     */
    private int prizeLevel = PrizeLevelEnums.N.getValue();

    /**
     * 图片
     */
    @OneToOne(fetch = FetchType.EAGER)
    private FileAttachment picture;

    /**
     * 奖品概率
     */
    @OneToMany(mappedBy = "prize", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<PrizeChance> prizeChances = new LinkedHashSet<>();

    public Prize() {
        super();
    }

    public Prize(Long id) {
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

    public FileAttachment getPicture() {
        return picture;
    }

    public void setPicture(FileAttachment picture) {
        this.picture = picture;
    }

    public Set<PrizeChance> getPrizeChances() {
        return prizeChances;
    }

    public void setPrizeChances(Set<PrizeChance> prizeChances) {
        this.prizeChances = prizeChances;
    }
}
