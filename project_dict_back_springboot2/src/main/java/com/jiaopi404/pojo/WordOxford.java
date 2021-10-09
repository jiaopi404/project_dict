package com.jiaopi404.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:jiaopi404
 * @Description:WordOxford构建
 * @Date 2021/1/4 14:30
 *****/
@ApiModel(description = "WordOxford",value = "WordOxford")
@Data
@Table(name="tb_word_oxford")
public class WordOxford implements Serializable{

	@ApiModelProperty(value = "主键",required = false)
	@Id
    @Column(name = "id")
	private String id; // 主键

	@ApiModelProperty(value = "单词",required = false)
    @Column(name = "word")
	private String word; // 单词

	@ApiModelProperty(value = "解释",required = false)
    @Column(name = "exp")
	private String exp; // 解释

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime = new Date(); // 创建时间



//
//	//get方法
//	public String getId() {
//		return id;
//	}
//
//	//set方法
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	//get方法
//	public String getWord() {
//		return word;
//	}
//
//	//set方法
//	public void setWord(String word) {
//		this.word = word;
//	}
//
//	//get方法
//	public String getExp() {
//		return exp;
//	}
//
//	//set方法
//	public void setExp(String exp) {
//		this.exp = exp;
//	}
//
//	//get方法
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	//set方法
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//


}
