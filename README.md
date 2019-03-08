# 项目简介

基于注解的 Java 异步处理框架。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/async/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/async)
[![Build Status](https://www.travis-ci.org/houbb/async.svg?branch=master)](https://www.travis-ci.org/houbb/async?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/async/badge.svg?branch=master)](https://coveralls.io/github/houbb/async?branch=master)


## 设计目的

并行执行可以大幅度提升程序的运行速度，有效利用 CPU 资源。

但是单独为每次方法都使用线程池手写，显然不够优雅，复用性也很差。

## 版本特性

- 支持接口类的动态代理异步

# 快速入门

具体测试代码，参见 async-test 模块。 

## 引入 maven

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>async-core</artifactId>
    <version>0.0.1</version>
</dependency>
```

## 定义测试对象

- 定义接口

当前版本没有引入 CGLIB 等字节码包，需要实现接口才能异步并行。

如果不实现接口，则不实现异步并行。

下个版本会添加 CGLIB，则不用实现接口。

```java
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
```

- 定义测试实现类

```java
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
```

## 测试

### 不使用代理

常规使用方式

```java
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
```

- 日志信息

```
开始根据用户id 查询用户信息 123
结束根据用户id 查询用户信息 123-result
开始根据用户id 查询用户信息 1234
结束根据用户id 查询用户信息 1234-result
查询结果123-result
查询结果1234-result
共计耗时: 6009
```

### 使用代理

```java
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
```

- 日志信息

```
开始根据用户id 查询用户信息 123
开始根据用户id 查询用户信息 1234
结束根据用户id 查询用户信息 123-result
结束根据用户id 查询用户信息 1234-result
查询结果123-result
查询结果1234-result
共计耗时: 3009
```

同样的功能实现，节约了将近一半的时间。

# 拓展阅读

[Async-01-项目模块说明](doc/blog/async-01-项目模块介绍.md)
