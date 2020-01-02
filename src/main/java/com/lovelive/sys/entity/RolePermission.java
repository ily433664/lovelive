package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;

/**
 * 角色-操作权限
 *
 * @author dHe
 * @date 2019-4-26
 */
@Entity
@Table(name = "t_role_permission")
public class RolePermission extends BaseEntity {

    private static final long serialVersionUID = -720192517064543159L;

    /**
     * 角色
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    /**
     * 操作
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Permission permission;

    public RolePermission() {
        super();
    }

    public RolePermission(String id) {
        super(id);
    }

    public RolePermission(Role role, Permission permission) {
        super();
        this.role = role;
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}
