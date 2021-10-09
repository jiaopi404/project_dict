package com.jiaopi404.controller;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.pojo.WordOxford;
import com.jiaopi404.service.WordOxfordService;
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
@Api(value = "WordOxfordController")
@RestController
@RequestMapping("/wordOxford")
@CrossOrigin
public class WordOxfordController {

    @Autowired
    private WordOxfordService wordOxfordService;

    /***
     * WordOxford分页条件搜索实现
     * @param wordOxford
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "WordOxford条件分页查询",notes = "分页条件查询WordOxford方法详情",tags = {"WordOxfordController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public ResultV0<PageInfo<WordOxford>> findPage(@RequestBody(required = false) @ApiParam(name = "WordOxford对象",value = "传入JSON数据",required = false) WordOxford wordOxford, @PathVariable  int page, @PathVariable  int size){
        //调用WordOxfordService实现分页条件查询WordOxford
        PageInfo<WordOxford> pageInfo = wordOxfordService.findPage(wordOxford, page, size);
        return ResultV0.OK(pageInfo, "查询成功");
    }

    /***
     * WordOxford分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "WordOxford分页查询",notes = "分页查询WordOxford方法详情",tags = {"WordOxfordController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public ResultV0<PageInfo<WordOxford>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用WordOxfordService实现分页查询WordOxford
        PageInfo<WordOxford> pageInfo = wordOxfordService.findPage(page, size);
        return ResultV0.OK(pageInfo, "查询成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param wordOxford
     * @return
     */
    @ApiOperation(value = "WordOxford条件查询",notes = "条件查询WordOxford方法详情",tags = {"WordOxfordController"})
    @PostMapping(value = "/search" )
    public ResultV0<List<WordOxford>> findList(@RequestBody(required = false) @ApiParam(name = "WordOxford对象",value = "传入JSON数据",required = false) WordOxford wordOxford){
        //调用WordOxfordService实现条件查询WordOxford
        List<WordOxford> list = wordOxfordService.findList(wordOxford);
//        return new Result<List<WordOxford>>(true,StatusCode.OK,"查询成功",list);
        return ResultV0.OK(list, "查询成功");
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "WordOxford根据ID删除",notes = "根据ID删除WordOxford方法详情",tags = {"WordOxfordController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public ResultV0<Object> delete(@PathVariable String id){
        //调用WordOxfordService实现根据主键删除
        wordOxfordService.delete(id);
        return ResultV0.OK("删除成功");
    }

    /***
     * 修改WordOxford数据
     * @param wordOxford
     * @param id
     * @return
     */
    @ApiOperation(value = "WordOxford根据ID修改",notes = "根据ID修改WordOxford方法详情",tags = {"WordOxfordController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public ResultV0<Object> update(@RequestBody @ApiParam(name = "WordOxford对象",value = "传入JSON数据",required = false) WordOxford wordOxford,@PathVariable String id){
        //设置主键值
        wordOxford.setId(id);
        //调用WordOxfordService实现修改WordOxford
        wordOxfordService.update(wordOxford);
        return ResultV0.OK("修改成功");
    }

    /***
     * 新增WordOxford数据
     * @param wordOxford
     * @return
     */
    @ApiOperation(value = "WordOxford添加",notes = "添加WordOxford方法详情",tags = {"WordOxfordController"})
    @PostMapping
    public ResultV0<Object> add(@RequestBody  @ApiParam(name = "WordOxford对象",value = "传入JSON数据",required = true) WordOxford wordOxford){
        //调用WordOxfordService实现添加WordOxford
        wordOxfordService.add(wordOxford);
        return ResultV0.OK("添加成功");
    }

    /***
     * 根据ID查询WordOxford数据
     * @param id
     * @return
     */
    @ApiOperation(value = "WordOxford根据ID查询",notes = "根据ID查询WordOxford方法详情",tags = {"WordOxfordController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public ResultV0<WordOxford> findById(@PathVariable String id){
        //调用WordOxfordService实现根据主键查询WordOxford
        WordOxford wordOxford = wordOxfordService.findById(id);
        return ResultV0.OK(wordOxford, "查询成功");
    }

    /***
     * 查询WordOxford全部数据
     * @return
     */
    @ApiOperation(value = "查询所有WordOxford",notes = "查询所WordOxford有方法详情",tags = {"WordOxfordController"})
    @GetMapping
    public ResultV0<List<WordOxford>> findAll(){
        //调用WordOxfordService实现查询所有WordOxford
        List<WordOxford> list = wordOxfordService.findAll();
        return ResultV0.OK(list, "查询成功");
    }
}
