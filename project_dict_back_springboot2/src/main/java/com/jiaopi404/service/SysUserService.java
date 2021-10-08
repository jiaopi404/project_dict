package com.jiaopi404.service;
import com.jiaopi404.pojo.SysUser;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:jiaopi404
 * @Description:SysUser业务层接口
 * @Date 2021/1/4 14:30
 *****/
public interface SysUserService {

    /***
     * SysUser多条件分页查询
     * @param sysUser
     * @param page
     * @param size
     * @return
     */
    PageInfo<SysUser> findPage(SysUser sysUser, int page, int size);

    /***
     * SysUser分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<SysUser> findPage(int page, int size);

    /***
     * SysUser多条件搜索方法
     * @param sysUser
     * @return
     */
    List<SysUser> findList(SysUser sysUser);

    /***
     * 删除SysUser
     * @param id
     */
    Integer delete(String id);

    /***
     * 修改SysUser数据
     * @param sysUser
     */
    Integer update(SysUser sysUser);

    /***
     * 新增SysUser
     * @param sysUser
     */
    Integer add(SysUser sysUser);

    /**
     * 根据ID查询SysUser
     * @param id
     * @return
     */
     SysUser findById(String id);

    /***
     * 查询所有SysUser
     * @return
     */
    List<SysUser> findAll();
}
