package com.lovelive.jwt.config;

import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT 常规配置
 *
 * @author dHe
 * @date 2019-8-9
 */
public class JwtConfig {

    /**
     * 加密算法
     */
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * jwt 过期时间
     */
    public static final long EXPIRATION_TIME = 30 * 24 * 60 * 60 * 1000;

    /**
     * jwt 过期时间
     */
    public static final Date EXPIRATION_DATE = new Date(System.currentTimeMillis() + JwtConfig.EXPIRATION_TIME);

    /**
     * token 的参数名称
     */
    public static final String AUTHORIZATION = "X-AUTH-TOKEN";

}
