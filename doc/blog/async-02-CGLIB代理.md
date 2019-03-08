# CGLIB 代理

和 Spring 的 AOP 实现一样。

我们有些方法是没有实现接口的，这时就需要依赖于字节码技术。

此处选择 CGLIB 实现。

## 支持版本

since 0.0.2

# 入门例子

## 定义服务类

```java
public class UserServiceDefault {

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

## 测试类

```java
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
```

- 日志信息如下

```
开始根据用户id 查询用户信息 123
开始根据用户id 查询用户信息 1234
结束根据用户id 查询用户信息 1234-result
结束根据用户id 查询用户信息 123-result
查询结果123-result
查询结果1234-result
共计耗时: 3181
```