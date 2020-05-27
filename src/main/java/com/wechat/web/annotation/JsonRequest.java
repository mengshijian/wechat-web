package com.wechat.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.ValueConstants;

/**
 * @Auther: win7
 * @Date: 2019/4/11 14:47
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface
JsonRequest {

  String value() default "";

  boolean required() default true;

  String defaultValue() default ValueConstants.DEFAULT_NONE;
}