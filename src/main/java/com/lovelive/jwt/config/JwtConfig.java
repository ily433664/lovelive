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
     * 这个加密key一般不开放
     */
    public static final String SECRET_KEY = "abcdefg1234567890";

    /**
     * 加密算法
     */
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * jwt 过期时间（1小时）
     */
    public static final Date EXPIRATION_DATE = new Date(System.currentTimeMillis() + 3600000);

    /**
     * token 的参数名称
     */
    public static final String AUTHORIZATION = "X-AUTH-TOKEN";

}
