package com.github.houbb.async.test.config;


import com.github.houbb.async.spring.annotation.EnableAsync;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
@Configurable
@ComponentScan(basePackages = "com.github.houbb.async.test.service")
@EnableAsync
public class SpringConfig {
}
