package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * 用户
 *
 * @author dHe
 * @date 2019-4-26
 */
@Entity
@Table(
        name = "t_user",
        indexes = {
                @Index(name = "idx_user_account", columnList = "account"),
                @Index(name = "idx_user_userName", columnList = "userName")
        }
)
public class User extends BaseEntity {

    private static final long serialVersionUID = 6137457354012580573L;

    /**
     * 用户类型
     * UserTypeEnums
     */
    @NotBlank
    private String userType;

    /**
     * 账号
     */
    @NotBlank
    private String account;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 是否修改过密码
     */
    private boolean modifyPassword = false;

    /**
     * 用户名称
     */
    @NotBlank
    private String username;

    /**
     * 密码错误次数
     */
    private Integer curErrorTimes = 0;

    /**
     * 账号锁定时间
     */
    private Date unlockTime;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 性别
     */
    private String gender;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    private String weChat;

    /**
     * 照片
     */
    @OneToOne(fetch = FetchType.EAGER)
    private FileAttachment photo;

    /**
     * 用户的角色
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new LinkedHashSet<>();

    public User() {
        super();
    }

    public User(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public boolean isModifyPassword() {
        return modifyPassword;
    }

    public void setModifyPassword(boolean modifyPassword) {
        this.modifyPassword = modifyPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCurErrorTimes() {
        return curErrorTimes;
    }

    public void setCurErrorTimes(Integer curErrorTimes) {
        this.curErrorTimes = curErrorTimes;
    }

    public Date getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public FileAttachment getPhoto() {
        return photo;
    }

    public void setPhoto(FileAttachment photo) {
        this.photo = photo;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}