package com.jiaopi404.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:jiaopi404
 * @Description:SysUser构建
 * @Date 2021/1/4 14:30
 *****/
@Data
@Table(name="sys_user")
public class SysUser implements Serializable{

	@Id
    @Column(name = "id")
	private String id; // id

    @Column(name = "user_name")
	private String userName; // 用户名

    @Column(name = "open_id")
	private String openId; // wechat open id

    @Column(name = "union_id")
	private String unionId; // wechat union_id

    @Column(name = "create_time")
	private Date createTime; // create time

    @Column(name = "update_time")
	private Date updateTime; // update time

    @Column(name = "is_deleted")
	private Integer isDeleted; // 是否删除了



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
//	public String getUserName() {
//		return userName;
//	}
//
//	//set方法
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	//get方法
//	public String getOpenId() {
//		return openId;
//	}
//
//	//set方法
//	public void setOpenId(String openId) {
//		this.openId = openId;
//	}
//
//	//get方法
//	public String getUnionId() {
//		return unionId;
//	}
//
//	//set方法
//	public void setUnionId(String unionId) {
//		this.unionId = unionId;
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
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	//set方法
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//
//	//get方法
//	public Integer getIsDeleted() {
//		return isDeleted;
//	}
//
//	//set方法
//	public void setIsDeleted(Integer isDeleted) {
//		this.isDeleted = isDeleted;
//	}
//


}
