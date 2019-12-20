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
@Table(name = "t_role_perm")
public class RolePerm extends BaseEntity {

    private static final long serialVersionUID = 4436566964126959541L;

    /**
     * 角色
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Role role;

    /**
     * 操作
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Perm perm;

    public RolePerm() {
        super();
    }

    public RolePerm(String id) {
        super(id);
    }

    public RolePerm(Role role, Perm perm) {
        super();
        this.role = role;
        this.perm = perm;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Perm getPerm() {
        return perm;
    }

    public void setPerm(Perm perm) {
        this.perm = perm;
    }

}
