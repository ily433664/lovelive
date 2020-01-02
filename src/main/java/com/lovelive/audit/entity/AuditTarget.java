package com.lovelive.audit.entity;

import com.lovelive.audit.enums.AuditStatusEnums;
import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 审核对象
 *
 * @author dHe
 * @date 2019-8-9
 */
@Entity
@Table(name = "t_audit_target")
public class AuditTarget extends BaseEntity {

    private static final long serialVersionUID = 4442892203227139884L;

    /**
     * 所属审核
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private AuditInfo auditInfo;

    /**
     * 当前的审状态
     * AuditStatusEnums
     */
    private int nowAuditStatus = AuditStatusEnums.PENDING.getValue();

    /**
     * 当前的审核动作
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private AuditAction nowAuditAction;

    /**
     * 审核记录
     */
    @OneToMany(mappedBy = "auditTarget", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<AuditRecord> auditRecords = new LinkedHashSet<>();

    public AuditTarget() {
        super();
    }

    public AuditTarget(String id) {
        super(id);
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    public int getNowAuditStatus() {
        return nowAuditStatus;
    }

    public void setNowAuditStatus(int nowAuditStatus) {
        this.nowAuditStatus = nowAuditStatus;
    }

    public AuditAction getNowAuditAction() {
        return nowAuditAction;
    }

    public void setNowAuditAction(AuditAction nowAuditAction) {
        this.nowAuditAction = nowAuditAction;
    }

    public Set<AuditRecord> getAuditRecords() {
        return auditRecords;
    }

    public void setAuditRecords(Set<AuditRecord> auditRecords) {
        this.auditRecords = auditRecords;
    }
}
