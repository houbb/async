package com.github.houbb.async.core.proxy.cglib;

import com.github.houbb.async.api.core.proxy.IAsyncProxy;
import com.github.houbb.async.core.model.async.AsyncResult;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CGLIB 代理类
 * @author binbin.hou
 * @date 2019/3/7
 * @since 0.0.2
 */
public class CglibProxy implements MethodInterceptor, IAsyncProxy {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * 被代理的对象
     */
    private final Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        // 这里将待执行的方法，放在 Executor 中进行执行。
        Future future = executor.submit(() -> method.invoke(target, objects));
        AsyncResult asyncResult = new AsyncResult();
        asyncResult.setFuture(future);
        return asyncResult;
    }

    @Override
    public Object proxy() {
        Enhancer enhancer = new Enhancer();
        //目标对象类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        //通过字节码技术创建目标对象类的子类实例作为代理
        return enhancer.create();
    }

}
