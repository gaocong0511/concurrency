package com.monkgow.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: gaocong
 * @Date: 2018/12/18
 * @Description:
 */

/**
 * 标记哪些线程或者写法不是线程安全的
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadNotSafe {
    String value() default "";
}
