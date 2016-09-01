package com.inspur.gcloud.mc.settings.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title: 参数类型实体类</p>
 * <p>Description: 用于设置获取参数类型对象</p>
 * @author h.song
 * @date 2016年6月22日 下午9:52:31
 * @vision 1.0
 */
@Table(name="MC_PARAM_TYPE")
public class ParamType implements Serializable{
	
	/**
	 * 主键ID
	 */
	@Id
	private String Id;
	
	/**
	 * 消息类型编码
	 */
	@Column(name="MESSAGE_TYPE_CODE")
	private String messageTypeCode;
	
	/**
	 * 参数编码
	 */
	@Column(name="PARAM_CODE")
	private String paramCode;
	
	/**
	 * 参数名称
	 */
	@Column(name="PARAM_NAME")
	private String paramName;
	
	/**
	 * 索引
	 */
	@Column(name="SEQ")
	private int seq;
	
	/**
	 * 备注
	 */
	@Column(name="REMARK")
	private String remark;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getMessageTypeCode() {
		return messageTypeCode;
	}

	public void setMessageTypeCode(String messageTypeCode) {
		this.messageTypeCode = messageTypeCode;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
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
