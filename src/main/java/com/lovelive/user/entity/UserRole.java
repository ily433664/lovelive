package com.lovelive.user.entity;

import com.lovelive.sys.base.BaseEntity;

import java.io.Serializable;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: UserRole
 * @Description: 用户-角色
 * @date 2018年1月18日 下午6:11:11
 */

public class UserRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6804557699582604003L;

    private Long id;

    /**
     * 用户
     */
    private User user;

    /**
     * 角色
     */
    private Role role;

    public UserRole() {
        super();
    }

    public UserRole(Long id) {
        this.id = id;
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
