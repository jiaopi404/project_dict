package com.jiaopi404.sub;

import com.jiaopi404.pojo.WordOxford;
import com.jiaopi404.pojo.bo.EnglishWord;
import com.jiaopi404.service.WordOxfordService;
import com.jiaopi404.utils.DictionaryReader;
import com.jiaopi404.utils.UUIDGetter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GenerateWordTests {

    @Autowired
    private WordOxfordService wordOxfordService;

    @Test
    void contextLoads() {
    }

    @Test
    void generateWordTests () {
        try {
            List<EnglishWord> wordList = DictionaryReader.readAsEnglishWordList("E:\\dev\\project_dict\\project_dict_back_springboot2\\src\\main\\resources\\static\\dictionary.txt");
            List<WordOxford> wordOxfordList = new ArrayList<>(); // 结果集
            // 填充结果集
            for (EnglishWord word : wordList) {
                WordOxford wordOxford = new WordOxford();
                BeanUtils.copyProperties(word, wordOxford);
                wordOxford.setId(UUIDGetter.getAsString());
                wordOxfordList.add(wordOxford);
            }
            // 调用存库服务
            wordOxfordService.addList(wordOxfordList);
            System.out.println("添加成功？？");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
