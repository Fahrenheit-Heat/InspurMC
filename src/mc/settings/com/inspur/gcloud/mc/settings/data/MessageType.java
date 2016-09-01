package com.inspur.gcloud.mc.settings.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title: 参数类型实体类</p>
 * <p>Description: 用于设置获取参数类型对象</p>
 * @author h.song
 * @date 2016年6月22日 下午9:51:53
 * @vision 1.0
 */
@Table(name="MC_MESSAGE_TYPE")
public class MessageType implements Serializable{

	/**
	 * 主键ID
	 */
	@Id
	private String id;
	
	/**
	 * 消息类型编码
	 */
	@Column(name="MESSAGE_TYPE_CODE")
	private String messageTypeCode;
	
	/**
	 * 消息类型名称
	 */
	@Column(name="MESSAGE_TYPE_NAME")
	private String messageTypeName;
	
	/**
	 * 索引
	 */
	@Column(name="SQE")
	private int seq;
	
	/**
	 * 备注
	 */
	@Column(name="REMARK")
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageTypeCode() {
		return messageTypeCode;
	}

	public void setMessageTypeCode(String messageTypeCode) {
		this.messageTypeCode = messageTypeCode;
	}

	public String getMessageTypeName() {
		return messageTypeName;
	}

	public void setMessageTypeName(String messageTypeName) {
		this.messageTypeName = messageTypeName;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
