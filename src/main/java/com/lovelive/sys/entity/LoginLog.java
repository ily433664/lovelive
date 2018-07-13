package com.lovelive.sys.entity;

import com.lovelive.sys.base.BaseEntity;

import java.io.Serializable;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: LoginLog
 * @Description: 登录日志
 * @date 2018年1月19日 下午2:13:42
 */

public class LoginLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7609999497955264358L;
    private Integer id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * IP地址
     */
    private String loginIP;
    /**
     * 操作是否登录成功,true:成功  false:失败
     */
    private Boolean ifSuccess = true;
    /**
     * 本次操作的结果 ,如果系统异常则增加显示异常信息
     */
    private String result;

    public LoginLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public Boolean getIfSuccess() {
        return ifSuccess;
    }

    public void setIfSuccess(Boolean ifSuccess) {
        this.ifSuccess = ifSuccess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}