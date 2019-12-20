package com.lovelive.lottery.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.sys.entity.AnnexFile;

import javax.persistence.*;
import java.util.*;

/**
 * 抽奖项目
 *
 * @author dHe
 * @date 2019-5-7
 */
@Entity
@Table(name = "t_lottery_project")
public class LotteryProject extends BaseEntity {

    private static final long serialVersionUID = 178363074197982142L;

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
    private AnnexFile picture;

    /**
     * 是否发布
     */
    private boolean publish = false;

    /**
     * 发布时间
     */
    private Date publishDate;

    /**
     * 抽奖
     */
    @OneToMany(mappedBy = "lotteryProject", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Lottery> lotteries = new ArrayList<>();

    public LotteryProject() {
        super();
    }

    public LotteryProject(String id) {
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

    public AnnexFile getPicture() {
        return picture;
    }

    public void setPicture(AnnexFile picture) {
        this.picture = picture;
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

    public List<Lottery> getLotteries() {
        return lotteries;
    }

    public void setLotteries(List<Lottery> lotteries) {
        this.lotteries = lotteries;
    }
}
