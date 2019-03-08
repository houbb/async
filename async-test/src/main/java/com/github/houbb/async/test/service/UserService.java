package com.github.houbb.async.test.service;

import com.github.houbb.async.core.model.async.AsyncResult;

/**
 * 用户服务接口
 * @author binbin.hou
 * date 2019/3/7
 * @since 0.0.1
 */
public interface UserService {

    /**
     * 查询用户信息
     * @param id 主键
     * @return 结果
     */
    AsyncResult<String> queryUser(final String id);

}
