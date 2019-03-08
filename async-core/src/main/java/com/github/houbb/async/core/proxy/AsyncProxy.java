/*
 * Copyright (c)  2019. houbinbin Inc.
 * async All rights reserved.
 */

package com.github.houbb.async.core.proxy;

import com.github.houbb.async.core.proxy.cglib.CglibProxy;
import com.github.houbb.async.core.proxy.dynamic.DynamicProxy;
import com.github.houbb.async.core.proxy.none.NoneProxy;
import com.github.houbb.async.core.util.AsyncClassUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;

import java.lang.reflect.Proxy;

/**
 * <p> 代理信息 </p>
 *
 * <pre> Created: 2019/3/8 10:38 AM  </pre>
 * <pre> Project: async  </pre>
 *
 * @author houbinbin
 * @since 0.0.1
 */
public final class AsyncProxy {

    /**
     * 获取对象代理
     * @param object 对象代理
     * @return 代理信息
     */
    public static Object getProxy(final Object object) {
        if(ObjectUtil.isNull(object)) {
            return new NoneProxy(object).proxy();
        }

        final Class clazz = object.getClass();

        // 如果targetClass本身是个接口或者targetClass是JDK Proxy生成的,则使用JDK动态代理。
        // 参考 spring 的 AOP 判断
        if (clazz.isInterface() || Proxy.isProxyClass(clazz)) {
            return new DynamicProxy(object).proxy();
        }

        return new CglibProxy(object).proxy();
    }




}
