package com.jiaopi404.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaopi404.mapper.WordOxfordMapper;
import com.jiaopi404.pojo.WordOxford;
import com.jiaopi404.service.WordOxfordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:WordOxford业务层接口实现类
 * @Date 2021/1/4 14:30
 *****/
@Service
public class WordOxfordServiceImpl implements WordOxfordService {

    @Autowired
    private WordOxfordMapper wordOxfordMapper;


    /**
     * WordOxford条件+分页查询
     * @param wordOxford 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<WordOxford> findPage(WordOxford wordOxford, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(wordOxford);
        //执行搜索
        return new PageInfo<WordOxford>(wordOxfordMapper.selectByExample(example));
    }

    /**
     * WordOxford分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<WordOxford> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<WordOxford>(wordOxfordMapper.selectAll());
    }

    /**
     * WordOxford条件查询
     * @param wordOxford
     * @return
     */
    @Override
    public List<WordOxford> findList(WordOxford wordOxford){
        //构建查询条件
        Example example = createExample(wordOxford);
        //根据构建的条件查询数据
        return wordOxfordMapper.selectByExample(example);
    }


    /**
     * WordOxford构建查询对象
     * @param wordOxford
     * @return
     */
    public Example createExample(WordOxford wordOxford){
        Example example=new Example(WordOxford.class);
        Example.Criteria criteria = example.createCriteria();
        if(wordOxford!=null){
            // 主键
            if(!StringUtils.isEmpty(wordOxford.getId())){
                    criteria.andEqualTo("id",wordOxford.getId());
            }
            // 单词
            if(!StringUtils.isEmpty(wordOxford.getWord())){
                    criteria.andEqualTo("word",wordOxford.getWord());
            }
            // 解释
            if(!StringUtils.isEmpty(wordOxford.getExp())){
                    criteria.andEqualTo("exp",wordOxford.getExp());
            }
            // 创建时间
            if(!StringUtils.isEmpty(wordOxford.getCreateTime())){
                    criteria.andEqualTo("createTime",wordOxford.getCreateTime());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public Integer delete(String id){
        return wordOxfordMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改WordOxford
     * @param wordOxford
     */
    @Override
    public Integer update(WordOxford wordOxford){
        return wordOxfordMapper.updateByPrimaryKey(wordOxford);
    }

    /**
     * 增加WordOxford
     * @param wordOxford
     */
    @Override
    public Integer add(WordOxford wordOxford){
        return wordOxfordMapper.insert(wordOxford);
    }

    /**
     * 根据ID查询WordOxford
     * @param id
     * @return
     */
    @Override
    public WordOxford findById(String id){
        return wordOxfordMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询WordOxford全部数据
     * @return
     */
    @Override
    public List<WordOxford> findAll() {
        return wordOxfordMapper.selectAll();
    }

    @Override
    public void addList(List<WordOxford> wordOxfordList) {
        wordOxfordMapper.insertList(wordOxfordList);
    }


}
