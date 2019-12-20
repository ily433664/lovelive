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

    private static final long serialVersionUID = 7491440130007581817L;

    private String token;

    public TokenModel() {
        super();
    }

    public TokenModel(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
