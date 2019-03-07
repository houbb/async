package com.github.houbb.async.test.service.impl;

import com.github.houbb.async.core.model.AsyncResult;
import com.github.houbb.async.test.service.UserService;

import java.util.concurrent.TimeUnit;

/**
 * @author binbin.hou
 * date 2019/3/7
 */
public class UserServiceImpl implements UserService {
    @Override
    public AsyncResult<String> queryUser(String id) {
        System.out.println("开始根据用户id 查询用户信息 " + id);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String result = id + "-result";
        System.out.println("结束根据用户id 查询用户信息 " + result);

        AsyncResult<String> asyncResult = new AsyncResult<>();
        asyncResult.setHolder(result);
        return asyncResult;
    }
}
