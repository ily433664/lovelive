package com.lovelive.modules.sys.config;

import com.lovelive.jwt.Interceptor.JwtAuthTokenInterceptor;
import com.lovelive.security.Interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtAuthTokenInterceptor jwtAuthTokenInterceptor;

    @Resource
    private SecurityInterceptor securityInterceptor;

    /**
     * 加入的顺序就是拦截器执行的顺序，如果添加 order 则按照 order 的顺序
     * 按顺序执行所有拦截器的 preHandle
     * 所有的 preHandle，执行完再执行全部 postHandle，最后是 afterCompletion
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // jwt拦截器
        registry.addInterceptor(jwtAuthTokenInterceptor)
                .addPathPatterns("/jwt/**")
                .excludePathPatterns("/static/**")
                .order(0);

        // 权限控制拦截器
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .order(1);

    }

}
