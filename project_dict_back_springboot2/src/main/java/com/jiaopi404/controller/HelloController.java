package com.jiaopi404.controller;

import com.jiaopi404.pojo.Student;
import com.jiaopi404.utils.ResultV0;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Hello controller.
 * 1. lombok 提供的日志功能，需要 @Slf4j 注解
 * 2. 获取 application.yml 中的自定义配置 @value 注解
 * 3. DI, IOC 自动注入 @Configuration 中的 Bean，如以下的 stu
 * 4. 注入自定义属性资源配置，如以下的 sysConfig
 */
@RestController // 加上之后 返回 JSON, 区别于 @Controller
@RequestMapping("/")
@Slf4j // lombok 提供的日志功能，可以配置日志文件
public class HelloController {
    // TODO: 接口 checkPort
    @GetMapping("/checkPort")
    public ResultV0 checkPort () {
        return ResultV0.OK(null, "服务正常");
    }
}
