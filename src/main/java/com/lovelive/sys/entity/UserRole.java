package com.lovelive.sys.entity;


import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;

/**
 * 用户-角色
 *
 * @author dHe
 * @date 2019-4-26
 */
@Entity
@Table(name = "t_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = -1661366539649781545L;

    /**
     * 用户
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    /**
     * 角色
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Role role;

    public UserRole() {
        super();
    }

    public UserRole(String id) {
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
