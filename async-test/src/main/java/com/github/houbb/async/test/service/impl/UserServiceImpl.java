package com.github.houbb.async.test.service.impl;

import com.github.houbb.async.core.model.async.AsyncResult;
import com.github.houbb.async.test.service.UserService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用户实现类
 * @author binbin.hou
 * date 2019/3/7
 * @since 0.0.1
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public AsyncResult<String> queryUser(String id) {
        System.out.println("开始根据用户id 查询用户信息 " + id);
        try {
            // 沉睡模拟处理耗时
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String result = id + "-result";
        System.out.println("结束根据用户id 查询用户信息 " + result);

        AsyncResult<String> asyncResult = new AsyncResult<>();
        asyncResult.setValue(result);
        return asyncResult;
    }

}
