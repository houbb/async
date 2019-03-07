package com.github.houbb.async.test.service;

import com.github.houbb.async.core.model.AsyncResult;

/**
 * @author binbin.hou
 * date 2019/3/7
 */
public interface UserService {

    AsyncResult<String> queryUser(final String id);

}
