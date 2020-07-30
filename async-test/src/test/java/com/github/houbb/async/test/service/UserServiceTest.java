package com.github.houbb.async.test.service;

import com.github.houbb.async.core.model.async.AsyncResult;
import com.github.houbb.async.core.proxy.AsyncProxy;
import com.github.houbb.async.test.service.impl.UserServiceDefault;
import com.github.houbb.async.test.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * 用户测试
 * @author binbin.hou
 * date 2019/3/7
 * @since 0.0.1
 */
public class UserServiceTest {

    /**
     * 默认不使用代理
     */
    @Test
    public void queryUserTest() {
        long start = System.currentTimeMillis();
        UserService userService = new UserServiceImpl();
        AsyncResult<String> result = userService.queryUser("123");
        AsyncResult<String> result2 = userService.queryUser("1234");

        System.out.println("查询结果" + result.getResult());
        System.out.println("查询结果" + result2.getResult());
        long end = System.currentTimeMillis();
        System.out.println("共计耗时: " + (end-start));
    }

    /**
     * 使用动态代理
     */
    @Test
    public void queryUserDynamicProxyTest() {
        long start = System.currentTimeMillis();
        UserService userService = new UserServiceImpl();
        UserService userServiceProxy = (UserService) AsyncProxy.getProxy(userService);
        AsyncResult<String> result = userServiceProxy.queryUser("123");
        AsyncResult<String> result2 = userServiceProxy.queryUser("1234");

        System.out.println("查询结果" + result.getResult());
        System.out.println("查询结果" + result2.getResult());
        long end = System.currentTimeMillis();
        System.out.println("共计耗时: " + (end-start));
    }

    /**
     * CGLIB 代理
     */
    @Test
    public void queryUserCglibProxyTest() {
        long start = System.currentTimeMillis();
        UserServiceDefault userService = new UserServiceDefault();
        UserServiceDefault userServiceProxy = (UserServiceDefault) AsyncProxy.getProxy(userService);
        AsyncResult<String> result = userServiceProxy.queryUser("123");
        AsyncResult<String> result2 = userServiceProxy.queryUser("1234");

        System.out.println("查询结果" + result.getResult());
        System.out.println("查询结果" + result2.getResult());
        long end = System.currentTimeMillis();
        System.out.println("共计耗时: " + (end-start));
    }



}
