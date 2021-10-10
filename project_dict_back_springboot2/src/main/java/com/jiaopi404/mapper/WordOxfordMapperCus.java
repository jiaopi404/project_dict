package com.jiaopi404.mapper;

import com.jiaopi404.my.mapper.MyMapper;
import com.jiaopi404.pojo.WordOxford;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/****
 * @Author:jiaopi404
 * @Description:WordOxford的Dao
 * @Date 2021 /1/4 14:30
 */
@Repository
public interface WordOxfordMapperCus {

    void insertWordList(@Param(value="list") List<WordOxford> list);

//    /**
//     * Gets all word oxford.
//     *
//     * @return the all word oxford
//     */
////    List<WordOxford> getAllWordOxford();
////    @Select("select * from tb_word_oxford")
//    List<WordOxford> getAllWordOxford();
//
//    /**
//     * Gets test table by name like.
//     *
//     * @param name the name
//     * @return the test table by name like
//     */
//    List<WordOxford> getTestTableByNameLike(String name);
}
