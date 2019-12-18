package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 角色
 * @Author dHe
 * @Date 2019/4/26
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 8022546963812075636L;

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 拥有此角色的用户
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<UserRole> userRoles = new ArrayList<>();

    /**
     * 此角色拥有的操作
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<RolePerm> rolePerms = new ArrayList<>();

    public Role() {
        super();
    }

    public Role(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<RolePerm> getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(List<RolePerm> rolePerms) {
        this.rolePerms = rolePerms;
    }
}
