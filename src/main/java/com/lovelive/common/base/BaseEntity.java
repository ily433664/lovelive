package com.lovelive.common.base;

import com.lovelive.common.enums.EntityStatusEnums;
import com.lovelive.sys.entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Entity支持类
 *
 * @author dHe
 * @date 2019-4-26
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "com.lovelive.common.base.CustomUUIDGenerator")
    protected String id;

    /**
     * 状态
     */
    protected int status = EntityStatusEnums.NORMAL.getValue();

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createTime;

    /**
     * 创建者
     */
    protected User createBy;

    /**
     * 最近更新时间
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updateTime;

    /**
     * 更新者
     */
    protected User updateBy;

    /**
     * 是否删除
     */
    protected boolean deleted = false;

    /**
     * 删除时间
     */
    protected Date deleteTime;

    /**
     * 删除者
     */
    protected User deleteBy;

    /**
     * 更新标记
     */
    protected int flag = 0;

    /**
     * 备注
     */
    @Lob
    protected String remark;

    /**
     * 年份，如果需要按照年份进行归类
     */
    protected Integer year;

    /**
     * 序号
     */
    protected Double sequence;

    public BaseEntity() {
        super();
    }

    public BaseEntity(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public User getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(User deleteBy) {
        this.deleteBy = deleteBy;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getSequence() {
        return sequence;
    }

    public void setSequence(Double sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

}
