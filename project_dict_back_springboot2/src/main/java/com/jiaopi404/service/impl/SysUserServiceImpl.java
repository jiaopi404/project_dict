package com.jiaopi404.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaopi404.mapper.SysUserMapper;
import com.jiaopi404.pojo.SysUser;
import com.jiaopi404.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:SysUser业务层接口实现类
 * @Date 2021/1/4 14:30
 *****/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * SysUser条件+分页查询
     * @param sysUser 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<SysUser> findPage(SysUser sysUser, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(sysUser);
        //执行搜索
        return new PageInfo<SysUser>(sysUserMapper.selectByExample(example));
    }

    /**
     * SysUser分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<SysUser> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<SysUser>(sysUserMapper.selectAll());
    }

    /**
     * SysUser条件查询
     * @param sysUser
     * @return
     */
    @Override
    public List<SysUser> findList(SysUser sysUser){
        //构建查询条件
        Example example = createExample(sysUser);
        //根据构建的条件查询数据
        return sysUserMapper.selectByExample(example);
    }


    /**
     * SysUser构建查询对象
     * @param sysUser
     * @return
     */
    public Example createExample(SysUser sysUser){
        Example example=new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        if(sysUser!=null){
            // id
            if(!StringUtils.isEmpty(sysUser.getId())){
                    criteria.andEqualTo("id",sysUser.getId());
            }
            // 用户名
            if(!StringUtils.isEmpty(sysUser.getUserName())){
                    criteria.andEqualTo("userName",sysUser.getUserName());
            }
            // wechat open id
            if(!StringUtils.isEmpty(sysUser.getOpenId())){
                    criteria.andEqualTo("openId",sysUser.getOpenId());
            }
            // wechat union_id
            if(!StringUtils.isEmpty(sysUser.getUnionId())){
                    criteria.andEqualTo("unionId",sysUser.getUnionId());
            }
            // create time
            if(!StringUtils.isEmpty(sysUser.getCreateTime())){
                    criteria.andEqualTo("createTime",sysUser.getCreateTime());
            }
            // update time
            if(!StringUtils.isEmpty(sysUser.getUpdateTime())){
                    criteria.andEqualTo("updateTime",sysUser.getUpdateTime());
            }
            // 是否删除了
            if(!StringUtils.isEmpty(sysUser.getIsDeleted())){
                    criteria.andEqualTo("isDeleted",sysUser.getIsDeleted());
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
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改SysUser
     * @param sysUser
     */
    @Override
    public Integer update(SysUser sysUser){
        return sysUserMapper.updateByPrimaryKey(sysUser);
    }

    /**
     * 增加SysUser
     * @param sysUser
     */
    @Override
    public Integer add(SysUser sysUser){
        return sysUserMapper.insert(sysUser);
    }

    /**
     * 根据ID查询SysUser
     * @param id
     * @return
     */
    @Override
    public SysUser findById(String id){
        return sysUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询SysUser全部数据
     * @return
     */
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectAll();
    }
}
