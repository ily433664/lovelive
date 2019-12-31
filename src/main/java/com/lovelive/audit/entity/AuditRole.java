package com.lovelive.audit.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.sys.entity.Role;

import javax.persistence.*;

/**
 * 审核-角色
 *
 * @author dHe
 * @date 2019-8-9
 */
@Entity
@Table(name = "t_audit_role")
public class AuditRole extends BaseEntity {

    private static final long serialVersionUID = 6428578342448380510L;

    /**
     * 用户
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private AuditAction auditAction;

    /**
     * 角色
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Role role;

    public AuditRole() {
        super();
    }

    public AuditRole(String id) {
        super(id);
    }

    public AuditRole(AuditAction auditAction, Role role) {
        this.auditAction = auditAction;
        this.role = role;
    }

    public AuditAction getAuditAction() {
        return auditAction;
    }

    public void setAuditAction(AuditAction auditAction) {
        this.auditAction = auditAction;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
