package com.jiaopi404.mapper;

import com.jiaopi404.my.mapper.MyMapper;
import com.jiaopi404.pojo.User;
import org.springframework.stereotype.Repository;

/****
 * @Author:jiaopi404
 * @Description:User的Dao
 * @Date 2021/1/4 14:30
 *****/
@Repository
//public interface UserMapper extends Mapper<User> {
public interface UserMapper extends MyMapper<User> {
}
