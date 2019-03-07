package com.github.houbb.async.core.model;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author binbin.hou
 * date 2019/3/7
 */
public class AsyncResult<T> implements Future<T> {

    private Future<T> future;

    private Object holder;

    public Future<T> getFuture() {
        return future;
    }

    public void setFuture(Future<T> future) {
        this.future = future;
    }

    public Object getHolderValue() {
        try {
            T t = future.get();
            // 这里拿到的 AsyncResult 对象
            if(null != t) {
                return ((AsyncResult)t).getHolder();
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getHolder() {
        return this.holder;
    }

    public void setHolder(Object holder) {
        this.holder = holder;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }

}
