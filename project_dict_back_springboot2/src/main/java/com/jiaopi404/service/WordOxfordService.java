package com.jiaopi404.service;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.pojo.WordOxford;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:WordOxford业务层接口
 * @Date 2021 /1/4 14:30
 */
public interface WordOxfordService {
    /**
     * 添加一个列表
     *
     * @param wordOxfordList the word oxford list
     */
    void addList (List<WordOxford> wordOxfordList);

    /**
     * query 查询
     * @param query
     * @return
     */
    PageInfo<WordOxford> basicQuery (String query);
}
