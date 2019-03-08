/*
 * Copyright (c)  2019. houbinbin Inc.
 * async All rights reserved.
 */

package com.github.houbb.async.core.model.async;

import com.github.houbb.async.api.core.async.impl.AbstractAsyncResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author binbin.hou
 * date 2019/3/7
 */
public class AsyncResult<T> extends AbstractAsyncResult<T> {

    /**
     * future 信息
     */
    private Future<T> future;

    /**
     * 结果
     */
    private Object value;

    /**
     * 获取执行的结果
     * @return 结果
     */
    public Object getResult() {
        // 直接返回结果
        if(future == null) {
            return this.getValue();
        }

        try {
            T t = future.get();
            // 这里拿到的 AsyncResult 对象
            if(null != t) {
                return ((AsyncResult)t).getValue();
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setFuture(Future<T> future) {
        this.future = future;
    }

}
