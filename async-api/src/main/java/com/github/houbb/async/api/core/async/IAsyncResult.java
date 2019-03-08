/*
 * Copyright (c)  2019. houbinbin Inc.
 * async All rights reserved.
 */

package com.github.houbb.async.api.core.async;

import java.util.concurrent.Future;

/**
 * <p> 异步执行结果 </p>
 *
 * <pre> Created: 2019/3/8 10:03 AM  </pre>
 * <pre> Project: async  </pre>
 *
 * @author houbinbin
 * @since 0.0.1
 * @param <T> 泛型类型
 */
public interface IAsyncResult<T> extends Future<T> {
}
