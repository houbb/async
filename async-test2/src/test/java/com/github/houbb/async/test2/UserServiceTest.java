package com.github.houbb.async.test2;

import com.github.houbb.async.core.model.async.AsyncResult;
import com.github.houbb.async.test2.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AsyncApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userAysncTest() {
        long start = System.currentTimeMillis();
        AsyncResult<String> result = userService.queryUser("123");
        AsyncResult<String> result2 = userService.queryUser("1234");

        System.out.println("查询结果" + result.getResult());
        System.out.println("查询结果" + result2.getResult());
        long end = System.currentTimeMillis();
        System.out.println("共计耗时: " + (end-start));
    }

}
