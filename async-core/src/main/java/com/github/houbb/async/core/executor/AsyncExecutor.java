package com.github.houbb.async.core.executor;

import com.github.houbb.async.api.core.async.IAsyncResult;
import com.github.houbb.async.api.core.executor.IAsyncExecutor;
import com.github.houbb.async.core.exception.AsyncRuntimeException;
import com.github.houbb.async.core.model.async.AsyncResult;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * 异步执行器
 * @author binbin.hou
 * date 2019/3/8
 * @since 0.0.2
 */
public class AsyncExecutor extends ThreadPoolExecutor implements IAsyncExecutor {

    private static final Log log = LogFactory.getLog(AsyncExecutor.class);

    //region 私有属性
    /**
     * 是否初始化
     */
    private static volatile boolean isInit = false;

    /**
     * 是否被销毁
     */
    private static volatile boolean isDestroy = false;

    /**
     * 线程执行器
     */
    private static ExecutorService executorService = null;
    //endregion

    //region 构造器
    public AsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public AsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public AsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public AsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
    //endregion

    @SuppressWarnings("all")
    public static <T> IAsyncResult<T> submit(final Object target, final Method method, final Object[] objects) {
        // 初始化的判断
        if(!isInit) {
            init();
        }

        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    method.invoke(target, objects);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("异步执行遇到异常: ", e);
                }
            }
        });
        AsyncResult<T> asyncResult = new AsyncResult<>();
        asyncResult.setFuture(future);
        return asyncResult;
    }

    /**
     * 初始化
     * 1. 暂时不添加配置相关的信息
     * 2. 最后调整状态
     */
    private static synchronized void init() {
        try {
            if(isInit) {
                return;
            }

            // 各种属性配置
            // 淘汰策略
            // 最佳线程数量
            executorService = Executors.newFixedThreadPool(10);
            updateExecutorStatus(true);
        } catch (Exception e) {
            throw new AsyncRuntimeException(e);
        }
    }



    /**
     * 销毁容器
     * 1. 销毁的时候进行等待，确保任务的正常执行完成。
     * 2. 任务执行的统计信息，后期添加。
     */
    private static synchronized void destroy() {
        if(isDestroy) {
            return;
        }

        executorService = null;
        updateExecutorStatus(false);
    }

    /**
     * 更新执行器的状态
     * @param initStatus 初始化状态
     */
    private static void updateExecutorStatus(final boolean initStatus) {
        isInit = initStatus;
        isDestroy = !isInit;
    }

}
