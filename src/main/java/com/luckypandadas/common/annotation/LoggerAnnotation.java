package com.luckypandadas.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * luckypandadas
 * Created by yadong.zhang on com.luckypandadas.common.annotation
 * User：yadong.zhang
 * Date：2015/12/30
 * Time：15:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface LoggerAnnotation {

    /**  类名*/
    String className();

    /**  方法名*/
    String methodName();

    /**  第几级，controller为1级，被controller调用的为第二级，以此类推*/
    int level();

}

