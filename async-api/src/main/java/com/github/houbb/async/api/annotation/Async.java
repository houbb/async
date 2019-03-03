package com.github.houbb.async.api.annotation;

import java.lang.annotation.*;

/**
 * 当方法添加这个注解的时候，则实际进行异步执行。
 * 1. 如果放在方法上，则当前方法会进行异步执行
 * 2. 如果放在类上，则当前类下面的所有方法都进行异步处理。
 *
 * 实现原理：基于 CGLIB 实现代理，基于 aop 实现方法的切面处理。
 * 基于线程池实现真正的任务处理。
 * @author binbin.hou
 * @since 0.0.1
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Async {
}
