package com.jiaopi404.controller;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.pojo.SysUser;
import com.jiaopi404.service.SysUserService;
import com.jiaopi404.utils.ResultV0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:
 * @Date 2021/1/4 14:30
 *****/

@RestController
@RequestMapping("/sysUser")
@CrossOrigin
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /***
     * 查询SysUser全部数据
     * @return
     */
    @GetMapping
    public ResultV0 findAll(){
        //调用SysUserService实现查询所有SysUser
        List<SysUser> list = sysUserService.findAll();
        return ResultV0.OK(list);
    }
}
