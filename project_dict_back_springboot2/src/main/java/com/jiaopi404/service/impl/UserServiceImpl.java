package com.jiaopi404.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaopi404.mapper.UserMapper;
import com.jiaopi404.pojo.User;
import com.jiaopi404.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:User业务层接口实现类
 * @Date 2021/1/4 14:30
 *****/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * User条件+分页查询
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<User> findPage(User user, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(user);
        //执行搜索
        return new PageInfo<User>(userMapper.selectByExample(example));
    }

    /**
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<User> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<User>(userMapper.selectAll());
    }

    /**
     * User条件查询
     * @param user
     * @return
     */
    @Override
    public List<User> findList(User user){
        //构建查询条件
        Example example = createExample(user);
        //根据构建的条件查询数据
        return userMapper.selectByExample(example);
    }


    /**
     * User构建查询对象
     * @param user
     * @return
     */
    public Example createExample(User user){
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(user!=null){
            // id
            if(!StringUtils.isEmpty(user.getId())){
                    criteria.andEqualTo("id",user.getId());
            }
            // 用户名
            if(!StringUtils.isEmpty(user.getUserName())){
                    criteria.andEqualTo("userName",user.getUserName());
            }
            // wechat open id
            if(!StringUtils.isEmpty(user.getOpenId())){
                    criteria.andEqualTo("openId",user.getOpenId());
            }
            // wechat union_id
            if(!StringUtils.isEmpty(user.getUnionId())){
                    criteria.andEqualTo("unionId",user.getUnionId());
            }
            // create time
            if(!StringUtils.isEmpty(user.getCreateTime())){
                    criteria.andEqualTo("createTime",user.getCreateTime());
            }
            // update time
            if(!StringUtils.isEmpty(user.getUpdateTime())){
                    criteria.andEqualTo("updateTime",user.getUpdateTime());
            }
            // 是否删除了
            if(!StringUtils.isEmpty(user.getIsDeleted())){
                    criteria.andEqualTo("isDeleted",user.getIsDeleted());
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
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改User
     * @param user
     */
    @Override
    public Integer update(User user){
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 增加User
     * @param user
     */
    @Override
    public Integer add(User user){
        return userMapper.insert(user);
    }

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
    @Override
    public User findById(String id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询User全部数据
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
