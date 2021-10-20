package com.jiaopi404.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaopi404.mapper.WordOxfordMapper;
import com.jiaopi404.mapper.WordOxfordMapperCus;
import com.jiaopi404.pojo.WordOxford;
import com.jiaopi404.service.WordOxfordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:WordOxford业务层接口实现类
 * @Date 2021 /1/4 14:30
 */
@Service
public class WordOxfordServiceImpl implements WordOxfordService {
    @Autowired
    private WordOxfordMapperCus wordOxfordMapperCus;

    @Autowired
    private WordOxfordMapper wordOxfordMapper;

    @Transactional
    @Override
    public void addList(List<WordOxford> wordOxfordList) {
        wordOxfordMapperCus.insertWordList(wordOxfordList);
    }

    @Override
    public PageInfo<WordOxford> basicQuery(String query) {
        // PageInfo
        PageHelper.startPage(1, 10);
        Example example = new Example(WordOxford.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("word", query + "%");
        criteria.orLike("exp", "%" + query + "%");
        example.orderBy("word");
        return new PageInfo<>(wordOxfordMapper.selectByExample(example));
    }
}
