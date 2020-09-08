package com.lovelive.audit.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 审核动作
 *
 * @author dHe
 */
@Entity(name = "t_audit_action")
public class AuditAction extends BaseEntity {

    private static final long serialVersionUID = -7675322593718686108L;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属审核
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private AuditInfo auditInfo;

    /**
     * 起始
     */
    private boolean start = false;

    /**
     * 终点
     */
    private boolean end = false;

    /**
     * 上一个动作
     */
    @OneToOne(fetch = FetchType.EAGER)
    private AuditAction laseAuditAction;

    /**
     * 下一个动作
     */
    @OneToOne(fetch = FetchType.EAGER)
    private AuditAction nextAuditAction;

    /**
     * 是否可以被越级审核
     */
    private boolean leapfrog = false;

    /**
     * 审核角色
     */
    @OneToMany(mappedBy = "auditAction", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<AuditRole> auditRoles = new LinkedHashSet<>();

    public AuditAction() {
        super();
    }

    public AuditAction(Long id) {
        super(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public AuditAction getLaseAuditAction() {
        return laseAuditAction;
    }

    public void setLaseAuditAction(AuditAction laseAuditAction) {
        this.laseAuditAction = laseAuditAction;
    }

    public AuditAction getNextAuditAction() {
        return nextAuditAction;
    }

    public void setNextAuditAction(AuditAction nextAuditAction) {
        this.nextAuditAction = nextAuditAction;
    }

    public boolean isLeapfrog() {
        return leapfrog;
    }

    public void setLeapfrog(boolean leapfrog) {
        this.leapfrog = leapfrog;
    }

    public Set<AuditRole> getAuditRoles() {
        return auditRoles;
    }

    public void setAuditRoles(Set<AuditRole> auditRoles) {
        this.auditRoles = auditRoles;
    }
}
