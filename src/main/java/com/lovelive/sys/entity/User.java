package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private static final long serialVersionUID = 4570703336086199675L;

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
    private AnnexFile photo;

    /**
     * 用户的角色
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<UserRole> userRoles = new ArrayList<>();

    /**
     * 修改密码记录
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<ModifyPassword> modifyPasswords = new ArrayList<>();

    public User() {
        super();
    }

    public User(String id) {
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

    public AnnexFile getPhoto() {
        return photo;
    }

    public void setPhoto(AnnexFile photo) {
        this.photo = photo;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<ModifyPassword> getModifyPasswords() {
        return modifyPasswords;
    }

    public void setModifyPasswords(List<ModifyPassword> modifyPasswords) {
        this.modifyPasswords = modifyPasswords;
    }
}