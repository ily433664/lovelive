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
@Table(name = "t_perm")
public class Perm extends BaseEntity {

    private static final long serialVersionUID = -7189689049951258708L;

    /**
     * 操作名称
     */
    private String name;

    /**
     * 拥有此操作的角色
     */
    @OneToMany(mappedBy = "perm", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<RolePerm> rolePerms = new ArrayList<>();

    public Perm() {
        super();
    }

    public Perm(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RolePerm> getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(List<RolePerm> rolePerms) {
        this.rolePerms = rolePerms;
    }
}
