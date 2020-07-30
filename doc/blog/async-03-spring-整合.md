# spring 整合

和 spring 整合可以为我们的使用带来更大的便利性。

## maven 引入

```xml
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>async-spring</artifactId>
    <version>最新版</version>
</dependency>
```

## 启动配置

使用 `@EnableAsync` 注解启动配置。

```java
@Configurable
@ComponentScan(basePackages = "com.github.houbb.async.test.service")
@EnableAsync
public class SpringConfig {
}
```

## 方法声明

使用 `@Async` 注解声明在方法上。

```java
@Service
public class UserServiceDefault {

    @Async
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

## 测试效果

```java
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
```

- 测试日志

```
开始根据用户id 查询用户信息 123
开始根据用户id 查询用户信息 1234
结束根据用户id 查询用户信息 1234-result
结束根据用户id 查询用户信息 123-result
查询结果null
查询结果null
共计耗时: 3029
```