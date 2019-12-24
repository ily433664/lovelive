package com.lovelive.sys.config;

import com.lovelive.jwt.Interceptor.JwtAuthTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration  // 默认单例模式
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtAuthTokenInterceptor jwtAuthTokenInterceptor;

    /**
     * 加入的顺序就是拦截器执行的顺序，如果添加order则按照order的顺序
     * 按顺序执行所有拦截器的preHandle
     * 所有的preHandle，执行完再执行全部postHandle，最后是afterCompletion
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtAuthTokenInterceptor)
                .addPathPatterns("/jwt/**")
                .excludePathPatterns("/static/**")
                .order(0);

    }

}
