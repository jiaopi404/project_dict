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
 * @Description:UserWordOxfordMerge构建
 * @Date 2021/1/4 14:30
 *****/
@ApiModel(description = "UserWordOxfordMerge",value = "UserWordOxfordMerge")
@Data
@Table(name="tb_user_word_oxford_merge")
public class UserWordOxfordMerge implements Serializable{

	@ApiModelProperty(value = "关联表主键",required = false)
	@Id
    @Column(name = "id")
	private String id; // 关联表主键

	@ApiModelProperty(value = "用户 id",required = false)
    @Column(name = "user_id")
	private String userId; // 用户 id

	@ApiModelProperty(value = "牛津词典 逻辑外键",required = false)
    @Column(name = "word_oxford_id")
	private String wordOxfordId; // 牛津词典 逻辑外键

	@ApiModelProperty(value = "单词 内容。冗余",required = false)
    @Column(name = "word")
	private String word; // 单词 内容。冗余

	@ApiModelProperty(value = "背诵的 次数",required = false)
    @Column(name = "times")
	private Integer times; // 背诵的 次数

	@ApiModelProperty(value = "是否标记为删除了 1 是 0 否",required = false)
    @Column(name = "if_delete")
	private Integer ifDelete = 0; // 是否标记为删除了 1 是 0 否

	@ApiModelProperty(value = "是否生词，设置为是，则在生词本； 1 是 0 否",required = false)
    @Column(name = "if_new_word")
	private Integer ifNewWord = 0; // 是否生词，设置为是，则在生词本； 1 是 0 否

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime = new Date(); // 创建时间

	@ApiModelProperty(value = "上一次背诵的时间",required = false)
    @Column(name = "last_time")
	private Date lastTime = new Date(); // 上一次背诵的时间



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
//	public String getUserId() {
//		return userId;
//	}
//
//	//set方法
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	//get方法
//	public String getWordOxfordId() {
//		return wordOxfordId;
//	}
//
//	//set方法
//	public void setWordOxfordId(String wordOxfordId) {
//		this.wordOxfordId = wordOxfordId;
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
//	public Integer getTimes() {
//		return times;
//	}
//
//	//set方法
//	public void setTimes(Integer times) {
//		this.times = times;
//	}
//
//	//get方法
//	public Integer getIfDelete() {
//		return ifDelete;
//	}
//
//	//set方法
//	public void setIfDelete(Integer ifDelete) {
//		this.ifDelete = ifDelete;
//	}
//
//	//get方法
//	public Integer getIfNewWord() {
//		return ifNewWord;
//	}
//
//	//set方法
//	public void setIfNewWord(Integer ifNewWord) {
//		this.ifNewWord = ifNewWord;
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
//	//get方法
//	public Date getLastTime() {
//		return lastTime;
//	}
//
//	//set方法
//	public void setLastTime(Date lastTime) {
//		this.lastTime = lastTime;
//	}
//


}
