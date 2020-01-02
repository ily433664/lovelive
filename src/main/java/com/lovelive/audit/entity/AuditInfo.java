package com.lovelive.audit.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 审核信息
 *
 * @author dHe
 * @date 2019-8-9
 */
@Entity
@Table(name = "t_audit_info")
public class AuditInfo extends BaseEntity {

    private static final long serialVersionUID = -5812297009811974724L;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 拥有的审核动作
     */
    @OneToMany(mappedBy = "auditInfo", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<AuditAction> auditActions = new LinkedHashSet<>();

    public AuditInfo() {
        super();
    }

    public AuditInfo(String id) {
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

    public Set<AuditAction> getAuditActions() {
        return auditActions;
    }

    public void setAuditActions(Set<AuditAction> auditActions) {
        this.auditActions = auditActions;
    }
}
