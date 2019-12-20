package com.lovelive.common.base;

import com.lovelive.common.enums.RecordStatusEnums;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Record支持类
 *
 * @author dHe
 * @date 2019-4-26
 */
@MappedSuperclass
public abstract class BaseRecord implements Serializable {

    public static final long serialVersionUID = 2L;
    /**
     * 审核状态
     */
    @Column(name = "audit_status")
    private Integer auditStatus = RecordStatusEnums.UNSUBMITTED.getCode();
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 最近更新时间
     */
    @Column(name = "update_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /**
     * 是否删除
     */
    @Column(name = "deleted")
    private boolean deleted = false;

    protected BaseRecord() {
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
