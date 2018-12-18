package com.monkgow.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: gaocong
 * @Date: 2018/12/18
 * @Description: 线程安全注解
 */

//注解哪个类是线程安全的 哪个类不是线程安全的 或者是某些写法
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
    String value() default "";
}
