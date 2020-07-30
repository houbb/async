package com.github.houbb.async.test.spring;


import com.github.houbb.async.core.model.async.AsyncResult;
import com.github.houbb.async.test.config.SpringConfig;
import com.github.houbb.async.test.service.UserService;
import com.github.houbb.async.test.service.impl.UserServiceDefault;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceDefault userServiceDefault;

    /**
     * 未使用注解
     *
     * @since 0.0.3
     */
    @Test
    public void userAysncTest() {
        long start = System.currentTimeMillis();
        AsyncResult<String> result = userServiceDefault.queryUser("123");
        AsyncResult<String> result2 = userServiceDefault.queryUser("1234");

        System.out.println("查询结果" + result.getResult());
        System.out.println("查询结果" + result2.getResult());
        long end = System.currentTimeMillis();
        System.out.println("共计耗时: " + (end-start));
    }

    /**
     * 未使用注解
     *
     * @since 0.0.3
     */
    @Test
    public void notUseAsyncTest() {
        long start = System.currentTimeMillis();
        AsyncResult<String> result = userService.queryUser("123");
        AsyncResult<String> result2 = userService.queryUser("1234");

        System.out.println("查询结果" + result.getResult());
        System.out.println("查询结果" + result2.getResult());
        long end = System.currentTimeMillis();
        System.out.println("共计耗时: " + (end-start));
    }

}
