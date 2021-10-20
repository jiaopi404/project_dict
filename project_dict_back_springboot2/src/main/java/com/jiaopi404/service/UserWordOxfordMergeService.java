package com.jiaopi404.service;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.pojo.UserWordOxfordMerge;
import com.jiaopi404.pojo.bo.AddUserWordOxfordMerge;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:UserWordOxfordMerge业务层接口
 * @Date 2021/1/4 14:30
 *****/
public interface UserWordOxfordMergeService {

    /**
     * 添加到生词本
     * @param addUserWordOxfordMerge
     * @return
     */
    Integer addCommon (AddUserWordOxfordMerge addUserWordOxfordMerge);

    /**
     * 从生词本移除
     * @param id
     * @return
     */
    Integer removeById (String id);

    /**
     * 查询生词本列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    PageInfo<UserWordOxfordMerge> getPageList (String userId, Integer pageNum, Integer pageSize, String query);
}
