package com.jiaopi404.service;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.pojo.User;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:User业务层接口
 * @Date 2021 /1/4 14:30
 */
public interface UserService {

    /***
     * User多条件分页查询
     * @param user the user
     * @param page the page
     * @param size the size
     * @return page info
     */
    PageInfo<User> findPage(User user, int page, int size);

    /***
     * User分页查询
     * @param page the page
     * @param size the size
     * @return page info
     */
    PageInfo<User> findPage(int page, int size);

    /***
     * User多条件搜索方法
     * @param user the user
     * @return list
     */
    List<User> findList(User user);

    /***
     * 删除User
     * @param id the id
     * @return the integer
     */
    Integer delete(String id);

    /***
     * 修改User数据
     * @param user the user
     * @return the integer
     */
    Integer update(User user);

    /***
     * 新增User
     * @param user the user
     * @return the integer
     */
    Integer add(User user);

    /**
     * 根据ID查询User
     *
     * @param id the id
     * @return user
     */
    User findById(String id);

    /***
     * 查询所有User
     * @return list
     */
    List<User> findAll();
}
