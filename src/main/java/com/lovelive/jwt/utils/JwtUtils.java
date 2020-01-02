package com.lovelive.jwt.utils;

import com.lovelive.common.uitls.IdGenerator;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.jwt.config.JwtConfig;
import com.lovelive.jwt.entity.TokenModel;
import com.lovelive.jwt.exception.JwtAuthenticationException;
import com.lovelive.jwt.service.ITokenModelService;
import com.lovelive.sys.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户工具类
 *
 * @author dHe
 * @date 2018-1-18
 */
@Component
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private static ITokenModelService tokenModelService;

    @Autowired
    public JwtUtils(ITokenModelService tokenModelService) {
        JwtUtils.tokenModelService = tokenModelService;
    }

    /**
     * 根据 user 生成 TokenModel
     *
     * @param user
     * @return
     */
    public static TokenModel createTokenModel(User user) {
        if (user == null || StringUtils.isEmpty(user.getId())) {
            throw new IllegalArgumentException("user can not null");
        }

        // 生成随机 key
        String secretKey = IdGenerator.uuid();
        // 生成 token
        String token = Jwts.builder()
                .setId(user.getId())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                //.setExpiration(JwtConfig.EXPIRATION_DATE)
                .signWith(JwtConfig.SIGNATURE_ALGORITHM, secretKey)
                .compact();
        // 生成 TokenModel
        TokenModel tokenModel = new TokenModel(user.getId(), secretKey, token);
        return tokenModelService.saveTokenModel(tokenModel);
    }

    /**
     * 获取当前的请求 token
     *
     * @return
     */
    public static String getToken() {
        return getToken(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
    }

    /**
     * 获取 request 的 token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        try {
            return request.getHeader(JwtConfig.AUTHORIZATION);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 验证当前的请求 token
     *
     * @return
     */
    public static Claims checkToken() {
        try {
            return checkToken(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 验证 request 的 token
     *
     * @param request
     * @return
     */
    public static Claims checkToken(HttpServletRequest request) {
        try {
            checkToken(request.getHeader(JwtConfig.AUTHORIZATION));
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 验证 request 的 token
     *
     * @param token
     * @return
     */
    public static Claims checkToken(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return null;
            } else {
                // 根据 token 获取 TokenModel
                TokenModel tokenModel = tokenModelService.getTokenModelByToken(token);
                if (!tokenModelService.checkToken(tokenModel)) {
                    throw new JwtAuthenticationException("invalid token");
                }
                // 检查 token 是否有效
                return Jwts.parser().setSigningKey(tokenModel.getSecretKey()).parseClaimsJws(tokenModel.getToken()).getBody();
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

}
