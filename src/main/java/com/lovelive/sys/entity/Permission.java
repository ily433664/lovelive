package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作权限
 *
 * @author dHe
 * @date 2019-4-26
 */
@Entity
@Table(name = "t_permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = -1909252799089400106L;

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
    private List<RolePermission> rolePermissions = new ArrayList<>();

    public Permission() {
        super();
    }

    public Permission(String id) {
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

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
