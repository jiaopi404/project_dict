package com.jiaopi404.utils;

import com.jiaopi404.pojo.bo.EnglishWord;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 读取字典的工具
 */
public class DictionaryReader {

    /**
     * Read as english word list list.
     *
     * @param path the path
     * @return the list
     * @throws IOException the io exception
     */
    public static List<EnglishWord> readAsEnglishWordList (String path) throws IOException {
//        // 方法 1
//        // 包装流
//        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
//        // 读取文件内容
//        byte[] b = new byte[bis.available()];
//        bis.read(b);
//        // Array.toString(b) 得到的是 字节
//        return new String(b); // 得到中文
        // 以GBK格式,读取文件
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "GBK");
        BufferedReader br = new BufferedReader(isr);
        String str = null;

        // 创建StringBuffer字符串缓存区
//        StringBuilder sb = new StringBuilder();
        ArrayList<EnglishWord> wordList = new ArrayList<>();


        // 通过readLine()方法遍历读取文件
        while ((str = br.readLine()) != null) {
//            System.out.println("某一行：" + str);
            try {
                EnglishWord word = DictionaryReader.readLineAsEnglishWord(str);
                wordList.add(word);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println("这个出错了: " + str);
            }
            // 使用readLine()方法无法进行换行,需要手动在原本输出的字符串后面加"\n"或"\r"
//            str += "\n";
//            sb.append(str);
        }
        return wordList;
    }

    /**
     * Read line as english word english word.
     *
     * @param str the str
     * @return the english word
     * @throws ArrayIndexOutOfBoundsException the array index out of bounds exception
     */
    public static EnglishWord readLineAsEnglishWord (String str) throws ArrayIndexOutOfBoundsException {
        String[] split = str.split(" +");
        EnglishWord word = new EnglishWord();
        word.setWord(split[0]);
        word.setExp(split[1]);
        return word;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            List<EnglishWord> wordList = DictionaryReader.readAsEnglishWordList("E:\\dev\\project_dict\\project_dict_back_springboot2\\src\\main\\resources\\static\\dictionary.txt");
//            System.out.println(str.substring(0, 100));
            System.out.println(wordList.get(0).toString());
            System.out.println(wordList.get(1).toString());
            System.out.println(wordList.get(2).toString());
            System.out.println(wordList.get(3).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
