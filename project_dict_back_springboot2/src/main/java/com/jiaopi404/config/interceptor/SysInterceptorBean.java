package com.jiaopi404.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jiaopi404.controller.interceptor.UserInfoInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用户信息的 拦截器
 *
 * <p>使用 拦截器的步骤：</p>
 * <p>1. 创建拦截器类 implements HandlerInterceptor，实现拦截器方法，其中：
 * <p>1.1 preHandle 进入 controller 之前</p>
 * <p>1.2 postHandler 出 controller 视图渲染之前</p>
 * <p>1.3 afterCompletion 视图渲染之后</p>
 * <p>2. 为了让扫描器扫描到，创建配置类 @Configuration，实现 WebMvcConfigurer 接口</p>
 * <p>3. 首先 创建拦截器的 Bean</p>
 * <p>4. 其次，实现方法 addInterceptors(InterceptorRegistry registry)</p>
 * <p>5. registry.addInterceptor(userInfoInterceptor()) 方法进行添加</p>
 * <p>6. 链式调用 excludePathPatterns 排除某个路由或者 addPathPatterns 添加某个路由</p>
 */
@Configuration
public class SysInterceptorBean implements WebMvcConfigurer {

    /**
     * User info interceptor user info interceptor.
     *
     * @return the user info interceptor
     */
    @Bean
    public UserInfoInterceptor userInfoInterceptor () {
        return new UserInfoInterceptor();
    }

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations and resource handler requests.
     * Interceptors can be registered to apply to all requests or be limited
     * to a subset of URL patterns.
     *
     * @param registry 拦截器仓库
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 除...之外
//        registry.addInterceptor(userInfoInterceptor()).excludePathPatterns("/student/del");
        // 添加特定路径
        registry.addInterceptor(userInfoInterceptor()).addPathPatterns("/student/del");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
