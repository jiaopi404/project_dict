package com.jiaopi404.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Student pojo
 * 1. lombok 提供的各种注解
 *      Data
 *      AllArgsConstructor
 *      NoArgsConstructor
 *      toString
 */
@Data // 默认创建 getter setter，无参的构造函数
@AllArgsConstructor // 加上之后，不会再创建 无参构造函数
@NoArgsConstructor
@ToString // toString 方法
public class Student {
    /**
     * The Name.
     */
    String name;
    /**
     * The Code.
     */
    String code;
    /**
     * The Age.
     */
    Integer age;
}
