package com.lovelive.audit.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 审核动作
 *
 * @author dHe
 * @date 2019-8-9
 */
@Entity
@Table(name = "t_audit_action")
public class AuditAction extends BaseEntity {

    private static final long serialVersionUID = 977921037242293249L;

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
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
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
    private List<AuditRole> auditRoles = new ArrayList<>();

    public AuditAction() {
        super();
    }

    public AuditAction(String id) {
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

    public List<AuditRole> getAuditRoles() {
        return auditRoles;
    }

    public void setAuditRoles(List<AuditRole> auditRoles) {
        this.auditRoles = auditRoles;
    }
}
