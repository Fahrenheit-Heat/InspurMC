package com.inspur.gcloud.mc.core.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * <p>附件类</P>
 *
 * @author ZXh
 */
@Table(name="MC_ATTACH")
public class Attach implements Serializable{
	// 主键
    @Id
    private String id;
    
    // 关联消息ID
    @Column(name="MESSAGE_ID")
    private String messageId;
    
    // 附件名称
    @Column(name = "ATTACH_NAME")
    private String attachName;
    
    // 附件类型
    @Column(name = "ATTACH_TYPE")
    private String attachType;
    
    // 存储类型：0：网盘；1：本地
    @Column(name = "MEMORY_TYPE")
    private String memoryType;
    
    // 附件地址
    @Column(name = "ATTACH_ADDRESS")
    private String attachAddress;
    
    // 创建日期
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    // 修改日期
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    
    // 备注
    @Column(name = "REMARK")
    private String remark;

	/**
	 * 属性id的get方法
	 * @return  Attach id 
	 */
	public String getId() {
		return id;
	}

	/**
	 * 属性id的set方法
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * 属性attachName的get方法
	 * @return  Attach attachName 
	 */
	public String getAttachName() {
		return attachName;
	}

	/**
	 * 属性attachName的set方法
	 * @param attachName String
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	/**
	 * 属性attachType的get方法
	 * @return  Attach attachType 
	 */
	public String getAttachType() {
		return attachType;
	}

	/**
	 * 属性attachType的set方法
	 * @param attachType String
	 */
	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	/**
	 * 属性memoryType的get方法
	 * @return  Attach memoryType 
	 */
	public String getMemoryType() {
		return memoryType;
	}

	/**
	 * 属性memoryType的set方法
	 * @param memoryType String
	 */
	public void setMemoryType(String memoryType) {
		this.memoryType = memoryType;
	}

	/**
	 * 属性attachAddress的get方法
	 * @return  Attach attachAddress 
	 */
	public String getAttachAddress() {
		return attachAddress;
	}

	/**
	 * 属性attachAddress的set方法
	 * @param attachAddress String
	 */
	public void setAttachAddress(String attachAddress) {
		this.attachAddress = attachAddress;
	}

	/**
	 * 属性createTime的get方法
	 * @return  Attach createTime 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 属性createTime的set方法
	 * @param createTime Date
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性updateTime的get方法
	 * @return  Attach updateTime 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 属性updateTime的set方法
	 * @param updateTime Date
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性remark的get方法
	 * @return  Attach remark 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 属性remark的set方法
	 * @param remark String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    

}
