package com.lovelive.jwt.aop;

import com.lovelive.jwt.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Restful请求， Token校验规则拦截器（JWT）
 *
 */
@Component
public class JwtAuthTokenInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

        // 如果Http请求是OPTIONS，那么只需返回状态码200，即代码中的HttpServletResponse.SC_OK
        // 除OPTIONS外，其他请求应由JWT检查
        if ("OPTIONS".equals(request.getMethod())) {
            //response.setStatus(HttpServletResponse.SC_OK);
            return true;
        } else {

            //从Http请求获取授权
            String authToken = request.getHeader(JwtConfig.AUTHORIZATION);
            log.info("token : {}", authToken);

            // check the authorization
            if (authToken == null) {
                throw new ServletException("Missing or invalid Authorization header");
            } else {
                // 使用JWT解析器检查签名是否对密钥“secret_key”有效
                Claims claims = Jwts.parser().setSigningKey(JwtConfig.SECRET_KEY).parseClaimsJws(authToken).getBody();
                log.info("claims : {}", claims);

                return true;
            }
        }

    }

}
