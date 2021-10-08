package com.jiaopi404.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringBootConfig 类，创建配置类
 *
 * 1. Configuration: 标识为配置类，会被容器扫描到；
 * 2. 其他的
 *      Bean
 *      Service
 *      Controller
 *      Component
 *      Repository
 */
@Configuration // 标识此类为 配置类，会被容器扫描到
// SpringBoot 会扫描 启动类当前包，和子包，所有加了 注解的
/**
 * @Bean
 * @Service
 * @Controller
 * @Component
 * @Repository
 * 以上都可使用，根据场景 或 业务，进行选择；
 */
public class SpringBootConfig {
}
