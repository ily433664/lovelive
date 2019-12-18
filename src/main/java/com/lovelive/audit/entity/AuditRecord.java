package com.lovelive.audit.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.sys.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @Description 审核记录
 * @Author dHe
 * @Date 2019/8/9
 */
@MappedSuperclass
public abstract class AuditRecord extends BaseEntity {

    private static final long serialVersionUID = 83504985148449745L;

    /**
     * 所属审核对象
     */
    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private AuditTarget auditTarget;

    /**
     * 审批人
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User auditUser;

    /**
     * 审批状态
     */
    private Integer auditStatus;

    /**
     * 审批意见
     */
    private String opinion;

    /**
     * 审核动作
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private AuditAction auditAction;

    private AuditRecord() {
        super();
    }

    public AuditRecord(AuditTarget auditTarget, User auditUser, Integer auditStatus, String opinion, AuditAction auditAction) {
        super();
        this.auditTarget = auditTarget;
        this.auditUser = auditUser;
        this.auditStatus = auditStatus;
        this.opinion = opinion;
        this.auditAction = auditAction;
    }

    public AuditTarget getAuditTarget() {
        return auditTarget;
    }

    public void setAuditTarget(AuditTarget auditTarget) {
        this.auditTarget = auditTarget;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public User getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(User auditUser) {
        this.auditUser = auditUser;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public AuditAction getAuditAction() {
        return auditAction;
    }

    public void setAuditAction(AuditAction auditAction) {
        this.auditAction = auditAction;
    }

}
