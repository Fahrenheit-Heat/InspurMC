/**
 * 
 */
package com.inspur.gcloud.mc.core.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>订阅信息类</p>
 * 
 * @author ZXh
 *
 */
@Table(name = "MC_SUBSCRIBE")
public class Subscribe {

	// 主键
	@Id
	private String id;
	
	// 组织代码
	@Column(name = "ORGAN_ID")
	private String organId;
	
	// 订阅模块
	@Column(name = "SUBSCRIBE_MODULE")
	private String subscribeModule;
	
	// 提醒方式：m：站内消息；w：站内邮件；s：短信；    e：电子邮件；
	@Column(name = "WARN_TYPE")
	private String warnType;
	
	// 是否开启订阅：0：否；1：是
	@Column(name ="SUBSCRIBE_OPEN")
	private String subscribeOpen;
	
	// 备注
	@Column(name = "REMARK")
	private String remark;

	/**
	 * 属性id的get方法
	 * @return id String
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

	/**
	 * 属性organId的get方法
	 * @return organId String
	 */
	public String getOrganId() {
		return organId;
	}

	/**
	 * 属性organId的set方法
	 * @param organId String
	 */
	public void setOrganId(String organId) {
		this.organId = organId;
	}

	/**
	 * 属性subscribeModule的get方法
	 * @return subscribeModule String
	 */
	public String getSubscribeModule() {
		return subscribeModule;
	}

	/**
	 * 属性subscribeModule的set方法
	 * @param subscribeModule String
	 */
	public void setSubscribeModule(String subscribeModule) {
		this.subscribeModule = subscribeModule;
	}

	/**
	 * 属性warnType的get方法
	 * @return warnType String
	 */
	public String getWarnType() {
		return warnType;
	}

	/**
	 * 属性warnType的set方法
	 * @param warnType String
	 */
	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}

	/**
	 * 属性subscribeOpen的get方法
	 * @return subscribeOpen String
	 */
	public String getSubscribeOpen() {
		return subscribeOpen;
	}

	/**
	 * 属性subscribeOpen的set方法
	 * @param subscribeOpen String
	 */
	public void setSubscribeOpen(String subscribeOpen) {
		this.subscribeOpen = subscribeOpen;
	}

	/**
	 * 属性remark的get方法
	 * @return remark String
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
