package com.jiaopi404.mapper;

import com.jiaopi404.my.mapper.MyMapper;
import com.jiaopi404.pojo.SysUser;
import org.springframework.stereotype.Repository;

/****
 * @Author:jiaopi404
 * @Description:SysUser的Dao
 * @Date 2021/1/4 14:30
 *****/
@Repository
//public interface SysUserMapper extends Mapper<SysUser> {
public interface SysUserMapper extends MyMapper<SysUser> {
}
