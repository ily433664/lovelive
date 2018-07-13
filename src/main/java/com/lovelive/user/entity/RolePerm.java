package com.lovelive.user.entity;

import com.lovelive.sys.base.BaseEntity;

import java.io.Serializable;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: RolePerm
 * @Description: 角色-操作权限
 * @date 2018年1月18日 下午6:10:43
 */

public class RolePerm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Perm perm;

    private Role role;

    public RolePerm() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perm getPerm() {
        return perm;
    }

    public void setPerm(Perm perm) {
        this.perm = perm;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
