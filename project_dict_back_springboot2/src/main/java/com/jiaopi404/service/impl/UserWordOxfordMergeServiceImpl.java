package com.jiaopi404.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaopi404.mapper.UserWordOxfordMergeMapper;
import com.jiaopi404.pojo.UserWordOxfordMerge;
import com.jiaopi404.service.UserWordOxfordMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:UserWordOxfordMerge业务层接口实现类
 * @Date 2021/1/4 14:30
 *****/
@Service
public class UserWordOxfordMergeServiceImpl implements UserWordOxfordMergeService {

    @Autowired
    private UserWordOxfordMergeMapper userWordOxfordMergeMapper;


    /**
     * UserWordOxfordMerge条件+分页查询
     * @param userWordOxfordMerge 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<UserWordOxfordMerge> findPage(UserWordOxfordMerge userWordOxfordMerge, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(userWordOxfordMerge);
        //执行搜索
        return new PageInfo<UserWordOxfordMerge>(userWordOxfordMergeMapper.selectByExample(example));
    }

    /**
     * UserWordOxfordMerge分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<UserWordOxfordMerge> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<UserWordOxfordMerge>(userWordOxfordMergeMapper.selectAll());
    }

    /**
     * UserWordOxfordMerge条件查询
     * @param userWordOxfordMerge
     * @return
     */
    @Override
    public List<UserWordOxfordMerge> findList(UserWordOxfordMerge userWordOxfordMerge){
        //构建查询条件
        Example example = createExample(userWordOxfordMerge);
        //根据构建的条件查询数据
        return userWordOxfordMergeMapper.selectByExample(example);
    }


    /**
     * UserWordOxfordMerge构建查询对象
     * @param userWordOxfordMerge
     * @return
     */
    public Example createExample(UserWordOxfordMerge userWordOxfordMerge){
        Example example=new Example(UserWordOxfordMerge.class);
        Example.Criteria criteria = example.createCriteria();
        if(userWordOxfordMerge!=null){
            // 关联表主键
            if(!StringUtils.isEmpty(userWordOxfordMerge.getId())){
                    criteria.andEqualTo("id",userWordOxfordMerge.getId());
            }
            // 用户 id
            if(!StringUtils.isEmpty(userWordOxfordMerge.getUserId())){
                    criteria.andEqualTo("userId",userWordOxfordMerge.getUserId());
            }
            // 牛津词典 逻辑外键
            if(!StringUtils.isEmpty(userWordOxfordMerge.getWordOxfordId())){
                    criteria.andEqualTo("wordOxfordId",userWordOxfordMerge.getWordOxfordId());
            }
            // 单词 内容。冗余
            if(!StringUtils.isEmpty(userWordOxfordMerge.getWord())){
                    criteria.andEqualTo("word",userWordOxfordMerge.getWord());
            }
            // 背诵的 次数
            if(!StringUtils.isEmpty(userWordOxfordMerge.getTimes())){
                    criteria.andEqualTo("times",userWordOxfordMerge.getTimes());
            }
            // 是否标记为删除了 1 是 0 否
            if(!StringUtils.isEmpty(userWordOxfordMerge.getIfDelete())){
                    criteria.andEqualTo("ifDelete",userWordOxfordMerge.getIfDelete());
            }
            // 是否生词，设置为是，则在生词本； 1 是 0 否
            if(!StringUtils.isEmpty(userWordOxfordMerge.getIfNewWord())){
                    criteria.andEqualTo("ifNewWord",userWordOxfordMerge.getIfNewWord());
            }
            // 创建时间
            if(!StringUtils.isEmpty(userWordOxfordMerge.getCreateTime())){
                    criteria.andEqualTo("createTime",userWordOxfordMerge.getCreateTime());
            }
            // 上一次背诵的时间
            if(!StringUtils.isEmpty(userWordOxfordMerge.getLastTime())){
                    criteria.andEqualTo("lastTime",userWordOxfordMerge.getLastTime());
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
        return userWordOxfordMergeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改UserWordOxfordMerge
     * @param userWordOxfordMerge
     */
    @Override
    public Integer update(UserWordOxfordMerge userWordOxfordMerge){
        return userWordOxfordMergeMapper.updateByPrimaryKey(userWordOxfordMerge);
    }

    /**
     * 增加UserWordOxfordMerge
     * @param userWordOxfordMerge
     */
    @Override
    public Integer add(UserWordOxfordMerge userWordOxfordMerge){
        return userWordOxfordMergeMapper.insert(userWordOxfordMerge);
    }

    /**
     * 根据ID查询UserWordOxfordMerge
     * @param id
     * @return
     */
    @Override
    public UserWordOxfordMerge findById(String id){
        return userWordOxfordMergeMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询UserWordOxfordMerge全部数据
     * @return
     */
    @Override
    public List<UserWordOxfordMerge> findAll() {
        return userWordOxfordMergeMapper.selectAll();
    }
}
