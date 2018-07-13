package com.lovelive.user.entity;

import com.lovelive.sys.base.BaseEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: Perm
 * @Description: 操作权限 Permission 对象
 * @date 2018年1月18日 下午6:10:14
 */

public class Perm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8657790061556668893L;

    private String id;

    /**
     * 操作名称
     */
    private String name;

    /**
     * 拥有此操作的角色
     */
    private Set<RolePerm> rolePerms = new HashSet<>();

    public Perm() {
        super();
    }

    public Perm(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RolePerm> getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(Set<RolePerm> rolePerms) {
        this.rolePerms = rolePerms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
