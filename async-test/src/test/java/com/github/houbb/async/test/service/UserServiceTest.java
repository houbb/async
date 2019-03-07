package com.github.houbb.async.test.service;

import com.github.houbb.async.core.model.AsyncResult;
import com.github.houbb.async.core.proxy.dynamic.DynamicProxy;
import com.github.houbb.async.test.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

/**
 * @author binbin.hou
 * date 2019/3/7
 */
public class UserServiceTest {

    @Test
    public void queryUserProxyTest() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        UserService userService = (UserService) DynamicProxy.getProxy(new UserServiceImpl());
        AsyncResult<String> result = userService.queryUser("123");
        AsyncResult<String> result2 = userService.queryUser("1234");

        System.out.println("查询结果" + result.getHolderValue());
        System.out.println("查询结果" + result2.getHolderValue());
        long end = System.currentTimeMillis();
        System.out.println("共计耗时: " + (end-start));
    }

}
