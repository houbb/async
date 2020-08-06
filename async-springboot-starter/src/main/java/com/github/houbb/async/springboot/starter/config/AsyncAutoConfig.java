package com.github.houbb.async.springboot.starter.config;

import com.github.houbb.async.spring.annotation.EnableAsync;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * 异步配置
 * @author binbin.hou
 * @since 0.0.4
 */
@Configuration
@ConditionalOnClass(EnableAsync.class)
@EnableAsync
public class AsyncAutoConfig {
}
