package com.lovelive.user.entity;

import com.lovelive.sys.base.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: User
 * @Description: 用户
 * @date 2018年1月18日 下午6:10:58
 */

public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5471787532949406698L;

    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户的角色
     */
    private Set<UserRole> userRoles = new HashSet<>();

    /**
     * 最后一次登录时间
     */
    private Timestamp lastLoginTime;

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", userRoles=" + userRoles +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}