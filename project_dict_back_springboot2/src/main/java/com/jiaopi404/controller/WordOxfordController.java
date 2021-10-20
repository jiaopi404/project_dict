package com.jiaopi404.controller;

import com.jiaopi404.pojo.WordOxford;
import com.jiaopi404.service.WordOxfordService;
import com.jiaopi404.utils.ResultV0;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:
 * @Date 2021 /1/4 14:30
 */
@Api(value = "WordOxfordController")
@RestController
@RequestMapping("/wordOxford")
@CrossOrigin
public class WordOxfordController {

    @Autowired
    private WordOxfordService wordOxfordService;

    @GetMapping("/baseQuery")
    public ResultV0<List<WordOxford>> baseQuery(String query) {
        List<WordOxford> wordOxfords = new ArrayList<>();
        //调用UserService实现分页条件查询User
        if (StringUtil.isEmpty(query)) {
            return ResultV0.OK(wordOxfords, "查询成功");
        }
        wordOxfords = wordOxfordService.basicQuery(query);
        return ResultV0.OK(wordOxfords, "查询成功");
    }
}
