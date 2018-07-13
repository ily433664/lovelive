package com.lovelive.sys.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: LogAnnotation
 * @Description: 日志注解
 * @date 2018年1月18日 下午6:01:09
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    String mold() default "";//类型

    String methods() default "";//功能
}
