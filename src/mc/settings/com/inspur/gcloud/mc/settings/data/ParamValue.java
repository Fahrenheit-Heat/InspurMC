package com.inspur.gcloud.mc.settings.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title: 参数值对象实体类</p>
 * <p>Description: 用于设置获取参数类值对象</p>
 * @author h.song
 * @date 2016年6月22日 下午9:53:18
 * @vision 1.0
 */
@Table(name="MC_PARAM_VALUE")
public class ParamValue implements Serializable{
	
	/**
	 * 主键ID
	 */
	@Id
	private String id;
	
	/**
	 * 参数编码
	 */
	@Column(name="PARAM_CODE")
	private String paramCode;
	
	/**
	 * 参数值
	 */
	@Column(name="PARAM_VALUE")
	private String paramValue;
	
	/**
	 * 参数可用性：0：不可用 1：可用
	 */
	@Column(name="PARAM_AVAILABE")
	private String paramAvailable;
	
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
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamAvailable() {
		return paramAvailable;
	}

	public void setParamAvailable(String paramAvailable) {
		this.paramAvailable = paramAvailable;
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
