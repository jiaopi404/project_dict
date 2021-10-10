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
 * @Date 2021 /1/4 14:30
 */
@Api(value = "WordOxfordController")
@RestController
@RequestMapping("/wordOxford")
@CrossOrigin
public class WordOxfordController {

    @Autowired
    private WordOxfordService wordOxfordService;
}
