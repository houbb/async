package com.github.houbb.async.api.annotation;

import java.lang.annotation.*;

/**
 * 当方法添加这个注解的时候，则实际进行异步执行。
 * 1. 如果放在方法上，则当前方法会进行异步执行
 * 2. 如果放在类上，则当前类下面的所有方法都进行异步处理。
 *
 * 只有注解的方法被代理执行，其他线程正常走。
 * 方法执行的结果放在异步线程中实现。
 * 1. 如果方法没有返回值，则直接继续执行主线程。结果在线程中回调。
 * 2. 如果方法有返回值，则同时异步执行多个方法。并且所有方法获取结果之后，继续走主线程。
 *
 * 异步提交方法，拿到 future
 * Method 返回值为空，直接提交 executor 执行，然后返回结果为 null。
 * Method 返回值不为空，直接返回 class.newInstance。
 *
 * 对于结果的处理，依然使用对于方法的拦截处理。invoke 执行方法获得方法执行结果。
 *
 * (1）直接创建返回对象的 class.NewInstance
 * (2) future 加载获取
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
