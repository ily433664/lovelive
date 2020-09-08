package com.lovelive.modules.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 操作权限
 *
 * @author dHe
 */
@Entity(name = "t_permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 2600696712388056076L;

    /**
     * 代码
     */
    private String code;

    /**
     * 操作名称
     */
    private String name;

    /**
     * 拥有此操作的角色
     */
    @OneToMany(mappedBy = "permission", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<RolePermission> rolePermissions = new LinkedHashSet<>();

    public Permission() {
        super();
    }

    public Permission(Long id) {
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

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
