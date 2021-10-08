package com.jiaopi404.my.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的 MyMapper
 *
 * @param <T> the type parameter
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
