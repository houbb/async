/*
 * Copyright (c)  2019. houbinbin Inc.
 * async All rights reserved.
 */

package com.github.houbb.async.api.core.proxy;

/**
 * <p> 代理接口 </p>
 *
 * <pre> Created: 2019/3/8 10:03 AM  </pre>
 * <pre> Project: async  </pre>
 *
 * @author houbinbin
 * @since 0.0.1
 */
public interface IAsyncProxy {

    /**
     * 获取代理对象
     * 1. 如果是实现了接口，默认使用 dynamic proxy 即可。
     * 2. 如果没有实现接口，默认使用 CGLIB 实现代理。
     * @return 代理对象
     */
    Object proxy();

}
