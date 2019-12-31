package com.lovelive.jwt.Interceptor;

import com.lovelive.jwt.service.ITokenModelService;
import com.lovelive.jwt.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Restful请求， Token 校验规则拦截器（JWT）
 *
 * @author dHe
 * @date 2019-8-9
 */
@Component
public class JwtAuthTokenInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthTokenInterceptor.class);

    private ITokenModelService tokenModelService;

    @Autowired
    public JwtAuthTokenInterceptor(ITokenModelService tokenModelService) {
        this.tokenModelService = tokenModelService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

        // 如果 Http 请求是 OPTIONS，则不用检查权限
        // 除 OPTIONS 外，其他请求应由JWT检查
        if ("OPTIONS".equals(request.getMethod())) {
            //response.setStatus(HttpServletResponse.SC_OK);
            return true;
        } else {

            Claims claims = JwtUtils.checkToken();
            if (claims != null) {
                return true;
            } else {
                //如果验证 token 失败，则返回 401 错误
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

        }

    }

}
