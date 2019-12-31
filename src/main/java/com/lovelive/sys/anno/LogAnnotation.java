package com.lovelive.sys.anno;

import com.lovelive.sys.enums.OperationTypeEnums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 *
 * @author dHe
 * @date 2018-1-18
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    /**
     * 操作类型
     */
    OperationTypeEnums mold() default OperationTypeEnums.MULTIPLE;

    /**
     * 方法说明
     */
    String description() default "";
}
