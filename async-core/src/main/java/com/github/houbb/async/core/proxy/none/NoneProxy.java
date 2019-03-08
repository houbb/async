/*
 * Copyright (c)  2019. houbinbin Inc.
 * async All rights reserved.
 */

package com.github.houbb.async.core.proxy.none;

import com.github.houbb.async.api.core.proxy.IAsyncProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p> 没有代理 </p>
 *
 * <pre> Created: 2019/3/5 10:23 PM  </pre>
 * <pre> Project: async  </pre>
 *
 * @author houbinbin
 * @since 0.0.1
 */
public class NoneProxy implements InvocationHandler, IAsyncProxy {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxy, args);
    }

    /**
     * 返回原始对象，没有代理
     * @param object 原始对象
     * @return 原始对象
     */
    @Override
    public Object proxy(Object object) {
        return object;
    }

}
