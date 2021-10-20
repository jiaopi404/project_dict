package com.jiaopi404.controller;

import com.github.pagehelper.PageInfo;
import com.jiaopi404.config.exception.GraceException;
import com.jiaopi404.pojo.UserWordOxfordMerge;
import com.jiaopi404.pojo.bo.AddUserWordOxfordMerge;
import com.jiaopi404.service.UserWordOxfordMergeService;
import com.jiaopi404.utils.ResultV0;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Map;

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
     * 新增UserWordOxfordMerge数据
     * @param addUserWordOxfordMerge
     * @return
     */
    @PutMapping("/addCommon")
    public ResultV0<Object> addCommon(@RequestBody AddUserWordOxfordMerge addUserWordOxfordMerge){
        //调用UserWordOxfordMergeService实现添加UserWordOxfordMerge
        userWordOxfordMergeService.addCommon(addUserWordOxfordMerge);
        return ResultV0.OK("添加成功");
    }

    /***
     * 新增UserWordOxfordMerge数据
     * @param id
     * @return
     */
    @DeleteMapping("/removeById/{id}")
    public ResultV0<Object> removeById(@PathVariable String id) {
        //调用UserWordOxfordMergeService实现添加UserWordOxfordMerge
        userWordOxfordMergeService.removeById(id);
        return ResultV0.OK("移除成功");
    }

    /**
     * 获取单词本分页数据
     * @param body
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     * @throws JSONException
     */
    @PostMapping("/getPageList")
    public ResultV0<PageInfo<UserWordOxfordMerge>> getPageList (@RequestBody Map<String, String> body, Integer pageNum, Integer pageSize, String query) throws JSONException {
        String userId = "";
        if (body.containsKey("userId")) {
            userId = body.get("userId");
        } else {
            GraceException.display("无用户");
        }
        PageInfo<UserWordOxfordMerge> pageList = userWordOxfordMergeService.getPageList(userId, pageNum, pageSize, query);
        return ResultV0.OK(pageList);
    }

}
