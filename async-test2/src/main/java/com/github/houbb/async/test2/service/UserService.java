package com.github.houbb.async.test2.service;

import com.github.houbb.async.core.model.async.AsyncResult;

/**
 * 用户服务接口
 * @author binbin.hou
 * @since 0.0.4
 */
public interface UserService {

    /**
     * 查询用户信息
     * @param id 主键
     * @return 结果
     */
    AsyncResult<String> queryUser(final String id);

}
