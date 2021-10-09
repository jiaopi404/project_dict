package com.jiaopi404.service;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.pojo.UserWordOxfordMerge;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:UserWordOxfordMerge业务层接口
 * @Date 2021/1/4 14:30
 *****/
public interface UserWordOxfordMergeService {

    /***
     * UserWordOxfordMerge多条件分页查询
     * @param userWordOxfordMerge
     * @param page
     * @param size
     * @return
     */
    PageInfo<UserWordOxfordMerge> findPage(UserWordOxfordMerge userWordOxfordMerge, int page, int size);

    /***
     * UserWordOxfordMerge分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<UserWordOxfordMerge> findPage(int page, int size);

    /***
     * UserWordOxfordMerge多条件搜索方法
     * @param userWordOxfordMerge
     * @return
     */
    List<UserWordOxfordMerge> findList(UserWordOxfordMerge userWordOxfordMerge);

    /***
     * 删除UserWordOxfordMerge
     * @param id
     */
    Integer delete(String id);

    /***
     * 修改UserWordOxfordMerge数据
     * @param userWordOxfordMerge
     */
    Integer update(UserWordOxfordMerge userWordOxfordMerge);

    /***
     * 新增UserWordOxfordMerge
     * @param userWordOxfordMerge
     */
    Integer add(UserWordOxfordMerge userWordOxfordMerge);

    /**
     * 根据ID查询UserWordOxfordMerge
     * @param id
     * @return
     */
     UserWordOxfordMerge findById(String id);

    /***
     * 查询所有UserWordOxfordMerge
     * @return
     */
    List<UserWordOxfordMerge> findAll();
}
