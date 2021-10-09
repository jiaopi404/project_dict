package com.jiaopi404.service;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.pojo.WordOxford;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:WordOxford业务层接口
 * @Date 2021/1/4 14:30
 *****/
public interface WordOxfordService {

    /***
     * WordOxford多条件分页查询
     * @param wordOxford
     * @param page
     * @param size
     * @return
     */
    PageInfo<WordOxford> findPage(WordOxford wordOxford, int page, int size);

    /***
     * WordOxford分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<WordOxford> findPage(int page, int size);

    /***
     * WordOxford多条件搜索方法
     * @param wordOxford
     * @return
     */
    List<WordOxford> findList(WordOxford wordOxford);

    /***
     * 删除WordOxford
     * @param id
     */
    Integer delete(String id);

    /***
     * 修改WordOxford数据
     * @param wordOxford
     */
    Integer update(WordOxford wordOxford);

    /***
     * 新增WordOxford
     * @param wordOxford
     */
    Integer add(WordOxford wordOxford);

    /**
     * 根据ID查询WordOxford
     * @param id
     * @return
     */
     WordOxford findById(String id);

    /***
     * 查询所有WordOxford
     * @return
     */
    List<WordOxford> findAll();

    void addList (List<WordOxford> wordOxfordList);
}
