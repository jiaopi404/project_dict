package com.jiaopi404.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaopi404.mapper.WordOxfordMapper;
import com.jiaopi404.mapper.WordOxfordMapperCus;
import com.jiaopi404.pojo.WordOxford;
import com.jiaopi404.pojo.bo.EnglishWord;
import com.jiaopi404.service.WordOxfordService;
import com.jiaopi404.utils.DictionaryReader;
import com.jiaopi404.utils.UUIDGetter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.ArrayList;
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
    public List<WordOxford> basicQuery(String query) {
        Example example = new Example(WordOxford.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("word", query + "%");
        criteria.orLike("exp", query + "%");
        example.orderBy("word");
        return wordOxfordMapper.selectByExample(example);
    }
}
