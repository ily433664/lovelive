package com.lovelive.sys.entity;


import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 修改密码记录
 *
 * @author dHe
 * @date 2019-5-5
 */
@Entity
@Table(name = "t_modify_password")
public class ModifyPassword extends BaseEntity {

    private static final long serialVersionUID = -3911015729385480428L;

    /**
     * 用户
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    /**
     * 修改后的密码
     */
    private String thisPassword;

    /**
     * 修改前的密码
     */
    private String lasePassword;

    public ModifyPassword() {
        super();
    }

    public ModifyPassword(String id) {
        super(id);
    }

    public ModifyPassword(User user, String thisPassword, String lasePassword) {
        super();
        this.user = user;
        this.thisPassword = thisPassword;
        this.lasePassword = lasePassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getThisPassword() {
        return thisPassword;
    }

    public void setThisPassword(String thisPassword) {
        this.thisPassword = thisPassword;
    }

    public String getLasePassword() {
        return lasePassword;
    }

    public void setLasePassword(String lasePassword) {
        this.lasePassword = lasePassword;
    }
}
