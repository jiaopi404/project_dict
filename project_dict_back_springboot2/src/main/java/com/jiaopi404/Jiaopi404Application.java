package com.jiaopi404;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.jiaopi404.mapper")
public class Jiaopi404Application {

    public static void main(String[] args) {
        SpringApplication.run(Jiaopi404Application.class, args);
        System.out.println("=============================== [启动成功] =================================");
    }

}
