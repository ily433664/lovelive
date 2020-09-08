package com.lovelive.audit.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.modules.sys.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 审核记录
 *
 * @author dHe
 */
@Entity(name = "t_audit_record")
public abstract class AuditRecord extends BaseEntity {


    /**
     * 所属审核对象
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private AuditTarget auditTarget;

    /**
     * 审批人
     */
    @ManyToOne(fetch = FetchType.EAGER)
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
    @ManyToOne(fetch = FetchType.EAGER)
    private AuditAction auditAction;

    public AuditRecord() {
        super();
    }

    public AuditRecord(Long id) {
        super(id);
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
