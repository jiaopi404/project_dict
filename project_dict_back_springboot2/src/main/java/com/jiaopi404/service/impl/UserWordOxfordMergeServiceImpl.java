package com.jiaopi404.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaopi404.config.exception.ValidationException;
import com.jiaopi404.mapper.UserWordOxfordMergeMapper;
import com.jiaopi404.mapper.WordOxfordMapper;
import com.jiaopi404.pojo.UserWordOxfordMerge;
import com.jiaopi404.pojo.WordOxford;
import com.jiaopi404.pojo.bo.AddUserWordOxfordMerge;
import com.jiaopi404.service.UserWordOxfordMergeService;
import com.jiaopi404.utils.UUIDGetter;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * @Author:jiaopi404
 * @Description:UserWordOxfordMerge业务层接口实现类
 * @Date 2021/1/4 14:30
 *****/
@Service
public class UserWordOxfordMergeServiceImpl implements UserWordOxfordMergeService {

    @Autowired
    private UserWordOxfordMergeMapper userWordOxfordMergeMapper;

    @Autowired
    private WordOxfordMapper wordOxfordMapper;

    @Override
    @Transactional
    public Integer addCommon(AddUserWordOxfordMerge addUserWordOxfordMerge) {
        // 1. 查询看有没有已经添加过
        Example example = new Example(UserWordOxfordMerge.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ifDelete", 0);
        criteria.andEqualTo("wordOxfordId", addUserWordOxfordMerge.getWordId());
        criteria.andEqualTo("userId", addUserWordOxfordMerge.getUserId());
        List<UserWordOxfordMerge> userWordOxfordMerges = userWordOxfordMergeMapper.selectByExample(example);
        if (userWordOxfordMerges.size() != 0) {
            // 2. 添加过，更新 times lastTime 不处理
            UserWordOxfordMerge userWordOxfordMerge = userWordOxfordMerges.get(0);
            userWordOxfordMerge.setLastTime(new Date());
            return userWordOxfordMergeMapper.updateByPrimaryKey(userWordOxfordMerge);
        } else {
            // 3. 未添加过的，进行创建
            UserWordOxfordMerge userWordOxfordMerge = new UserWordOxfordMerge();
            WordOxford wordOxford = wordOxfordMapper.selectByPrimaryKey(addUserWordOxfordMerge.getWordId());
            // 空指针检测
            if (wordOxford == null) {
                Map<String, String> errMap = new HashMap<>();
                errMap.put("wordId", "找不到对应的字典");
                throw new ValidationException(errMap, "参数错误");
            }
            userWordOxfordMerge.setUserId(addUserWordOxfordMerge.getUserId());
            userWordOxfordMerge.setWordOxfordId(addUserWordOxfordMerge.getWordId());
            userWordOxfordMerge.setWord(wordOxford.getWord());
            userWordOxfordMerge.setIfNewWord(1);
            userWordOxfordMerge.setId(UUIDGetter.getAsString());
            return userWordOxfordMergeMapper.insert(userWordOxfordMerge);
        }
    }

    @Override
    public Integer removeById(String id) {
        UserWordOxfordMerge userWordOxfordMerge = new UserWordOxfordMerge();
        userWordOxfordMerge.setId(id);
        userWordOxfordMerge.setIfDelete(1);
        return userWordOxfordMergeMapper.updateByPrimaryKeySelective(userWordOxfordMerge);
    }

    @Override
    public PageInfo<UserWordOxfordMerge> getPageList(String userId, Integer pageNum, Integer pageSize, String query) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UserWordOxfordMerge.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ifDelete", 0);
        criteria.andEqualTo("userId", userId);
        criteria.andLike("word", query + "%");
        return new PageInfo<>(userWordOxfordMergeMapper.selectByExample(example));
    }
}
