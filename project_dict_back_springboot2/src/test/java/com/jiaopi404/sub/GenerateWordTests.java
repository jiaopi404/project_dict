package com.jiaopi404.sub;

import com.jiaopi404.pojo.bo.EnglishWord;
import com.jiaopi404.service.WordOxfordService;
import com.jiaopi404.utils.DictionaryReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
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
            for (EnglishWord word : wordList) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
