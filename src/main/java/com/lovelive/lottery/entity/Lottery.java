package com.lovelive.lottery.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.sys.entity.FileAttachment;

import javax.persistence.*;
import java.util.*;

/**
 * 抽奖池
 *
 * @author dHe
 */
@Entity(name = "t_lottery")
public class Lottery extends BaseEntity {

    private static final long serialVersionUID = 834216377509557479L;

    /**
     * 所属项目
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private LotteryProject lotteryProject;

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
     * 图片
     */
    @OneToOne(fetch = FetchType.EAGER)
    private FileAttachment picture;

    /**
     * 是否保底
     */
    private boolean guaranteed;

    /**
     * 保底奖励等级 PrizeLevelEnum
     */
    private Integer guaranteePrizeLevel;

    /**
     * 保底所需次数
     */
    private Integer guaranteeNum;

    /**
     * 是否连抽保底
     */
    private boolean continuity;

    /**
     * 连抽保底奖励等级 PrizeLevelEnum
     */
    private Integer continuityPrizeLevel;

    /**
     * 连抽保底所需次数
     */
    private Integer continuityNum;

    /**
     * 是否发布
     */
    private boolean publish = false;

    /**
     * 发布时间
     */
    private Date publishDate;

    /**
     * 奖品概率
     */
    @OneToMany(mappedBy = "lottery", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<PrizeChance> prizeChances = new LinkedHashSet<>();

    public Lottery() {
        super();
    }

    public Lottery(Long id) {
        super(id);
    }

    public LotteryProject getLotteryProject() {
        return lotteryProject;
    }

    public void setLotteryProject(LotteryProject lotteryProject) {
        this.lotteryProject = lotteryProject;
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

    public FileAttachment getPicture() {
        return picture;
    }

    public void setPicture(FileAttachment picture) {
        this.picture = picture;
    }

    public boolean isGuaranteed() {
        return guaranteed;
    }

    public void setGuaranteed(boolean guaranteed) {
        this.guaranteed = guaranteed;
    }

    public Integer getGuaranteePrizeLevel() {
        return guaranteePrizeLevel;
    }

    public void setGuaranteePrizeLevel(Integer guaranteePrizeLevel) {
        this.guaranteePrizeLevel = guaranteePrizeLevel;
    }

    public Integer getGuaranteeNum() {
        return guaranteeNum;
    }

    public void setGuaranteeNum(Integer guaranteeNum) {
        this.guaranteeNum = guaranteeNum;
    }

    public boolean isContinuity() {
        return continuity;
    }

    public void setContinuity(boolean continuity) {
        this.continuity = continuity;
    }

    public Integer getContinuityPrizeLevel() {
        return continuityPrizeLevel;
    }

    public void setContinuityPrizeLevel(Integer continuityPrizeLevel) {
        this.continuityPrizeLevel = continuityPrizeLevel;
    }

    public Integer getContinuityNum() {
        return continuityNum;
    }

    public void setContinuityNum(Integer continuityNum) {
        this.continuityNum = continuityNum;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Set<PrizeChance> getPrizeChances() {
        return prizeChances;
    }

    public void setPrizeChances(Set<PrizeChance> prizeChances) {
        this.prizeChances = prizeChances;
    }
}
