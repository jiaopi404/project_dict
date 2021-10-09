package com.jiaopi404.controller;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.pojo.UserWordOxfordMerge;
import com.jiaopi404.service.UserWordOxfordMergeService;
import com.jiaopi404.utils.ResultV0;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:
 * @Date 2021/1/4 14:30
 *****/
@Api(value = "UserWordOxfordMergeController")
@RestController
@RequestMapping("/userWordOxfordMerge")
@CrossOrigin
public class UserWordOxfordMergeController {

    @Autowired
    private UserWordOxfordMergeService userWordOxfordMergeService;

    /***
     * UserWordOxfordMerge分页条件搜索实现
     * @param userWordOxfordMerge
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "UserWordOxfordMerge条件分页查询",notes = "分页条件查询UserWordOxfordMerge方法详情",tags = {"UserWordOxfordMergeController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public ResultV0<PageInfo<UserWordOxfordMerge>> findPage(@RequestBody(required = false) @ApiParam(name = "UserWordOxfordMerge对象",value = "传入JSON数据",required = false) UserWordOxfordMerge userWordOxfordMerge, @PathVariable  int page, @PathVariable  int size){
        //调用UserWordOxfordMergeService实现分页条件查询UserWordOxfordMerge
        PageInfo<UserWordOxfordMerge> pageInfo = userWordOxfordMergeService.findPage(userWordOxfordMerge, page, size);
        return ResultV0.OK(pageInfo, "查询成功");
    }

    /***
     * UserWordOxfordMerge分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "UserWordOxfordMerge分页查询",notes = "分页查询UserWordOxfordMerge方法详情",tags = {"UserWordOxfordMergeController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public ResultV0<PageInfo<UserWordOxfordMerge>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用UserWordOxfordMergeService实现分页查询UserWordOxfordMerge
        PageInfo<UserWordOxfordMerge> pageInfo = userWordOxfordMergeService.findPage(page, size);
        return ResultV0.OK(pageInfo, "查询成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param userWordOxfordMerge
     * @return
     */
    @ApiOperation(value = "UserWordOxfordMerge条件查询",notes = "条件查询UserWordOxfordMerge方法详情",tags = {"UserWordOxfordMergeController"})
    @PostMapping(value = "/search" )
    public ResultV0<List<UserWordOxfordMerge>> findList(@RequestBody(required = false) @ApiParam(name = "UserWordOxfordMerge对象",value = "传入JSON数据",required = false) UserWordOxfordMerge userWordOxfordMerge){
        //调用UserWordOxfordMergeService实现条件查询UserWordOxfordMerge
        List<UserWordOxfordMerge> list = userWordOxfordMergeService.findList(userWordOxfordMerge);
//        return new Result<List<UserWordOxfordMerge>>(true,StatusCode.OK,"查询成功",list);
        return ResultV0.OK(list, "查询成功");
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "UserWordOxfordMerge根据ID删除",notes = "根据ID删除UserWordOxfordMerge方法详情",tags = {"UserWordOxfordMergeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public ResultV0<Object> delete(@PathVariable String id){
        //调用UserWordOxfordMergeService实现根据主键删除
        userWordOxfordMergeService.delete(id);
        return ResultV0.OK("删除成功");
    }

    /***
     * 修改UserWordOxfordMerge数据
     * @param userWordOxfordMerge
     * @param id
     * @return
     */
    @ApiOperation(value = "UserWordOxfordMerge根据ID修改",notes = "根据ID修改UserWordOxfordMerge方法详情",tags = {"UserWordOxfordMergeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public ResultV0<Object> update(@RequestBody @ApiParam(name = "UserWordOxfordMerge对象",value = "传入JSON数据",required = false) UserWordOxfordMerge userWordOxfordMerge,@PathVariable String id){
        //设置主键值
        userWordOxfordMerge.setId(id);
        //调用UserWordOxfordMergeService实现修改UserWordOxfordMerge
        userWordOxfordMergeService.update(userWordOxfordMerge);
        return ResultV0.OK("修改成功");
    }

    /***
     * 新增UserWordOxfordMerge数据
     * @param userWordOxfordMerge
     * @return
     */
    @ApiOperation(value = "UserWordOxfordMerge添加",notes = "添加UserWordOxfordMerge方法详情",tags = {"UserWordOxfordMergeController"})
    @PostMapping
    public ResultV0<Object> add(@RequestBody  @ApiParam(name = "UserWordOxfordMerge对象",value = "传入JSON数据",required = true) UserWordOxfordMerge userWordOxfordMerge){
        //调用UserWordOxfordMergeService实现添加UserWordOxfordMerge
        userWordOxfordMergeService.add(userWordOxfordMerge);
        return ResultV0.OK("添加成功");
    }

    /***
     * 根据ID查询UserWordOxfordMerge数据
     * @param id
     * @return
     */
    @ApiOperation(value = "UserWordOxfordMerge根据ID查询",notes = "根据ID查询UserWordOxfordMerge方法详情",tags = {"UserWordOxfordMergeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public ResultV0<UserWordOxfordMerge> findById(@PathVariable String id){
        //调用UserWordOxfordMergeService实现根据主键查询UserWordOxfordMerge
        UserWordOxfordMerge userWordOxfordMerge = userWordOxfordMergeService.findById(id);
        return ResultV0.OK(userWordOxfordMerge, "查询成功");
    }

    /***
     * 查询UserWordOxfordMerge全部数据
     * @return
     */
    @ApiOperation(value = "查询所有UserWordOxfordMerge",notes = "查询所UserWordOxfordMerge有方法详情",tags = {"UserWordOxfordMergeController"})
    @GetMapping
    public ResultV0<List<UserWordOxfordMerge>> findAll(){
        //调用UserWordOxfordMergeService实现查询所有UserWordOxfordMerge
        List<UserWordOxfordMerge> list = userWordOxfordMergeService.findAll();
        return ResultV0.OK(list, "查询成功");
    }
}
