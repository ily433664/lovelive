package com.lovelive.jwt.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Token的Model类
 *
 * @author dHe
 * @date 2019-12-19
 */
@Entity
@Table(name = "t_token_model")
public class TokenModel extends BaseEntity {

    private static final long serialVersionUID = 4683545611495337664L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 秘钥
     */
    private String secretKey;

    /**
     * token
     */
    private String token;

    public TokenModel() {
        super();
    }

    public TokenModel(String userId, String secretKey, String token) {
        super();
        this.userId = userId;
        this.secretKey = secretKey;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
