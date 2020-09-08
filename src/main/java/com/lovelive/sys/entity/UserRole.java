package com.lovelive.sys.entity;


import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;

/**
 * 用户-角色
 *
 * @author dHe
 */
@Entity(name = "t_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = -2913150068857916009L;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    /**
     * 角色
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public UserRole() {
        super();
    }

    public UserRole(Long id) {
        super(id);
    }

    public UserRole(User user, Role role) {
        super();
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
