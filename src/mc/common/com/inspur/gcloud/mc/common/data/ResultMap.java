package com.inspur.gcloud.mc.common.data;

import java.util.HashMap;

/**
 * <p>消息中心返回结果类</p>
 * <p>用途：统一的返回结果对象</p>
 * 
 * @author Songh
 * @version 1.0
 * @since 2016年3月19日
 *
 */
public class ResultMap extends HashMap<String, Object> {

	private static final long serialVersionUID = -1668874384010563627L;
	
	// 错误码常量
	public static final String KEY_ERROR_TYPE = "errorType";
	
	// 发送状态
	private String sendState;
	
	// 发送状态代码
	private String sendStateCode;
	
	// 是否成功
	private boolean isSuccess;
	
	// 返回信息
	private String msg;
	
	public ResultMap(){
		super();
		isSuccess = false;
		sendState = "";
		sendStateCode = "";
		msg = "";
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	public String getSendStateCode() {
		return sendStateCode;
	}

	public void setSendStateCode(String sendStateCode) {
		this.sendStateCode = sendStateCode;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 增加错误码
	 * @param errorType 错误码
	 */
	public void addErrorType(String errorType){
		this.put(KEY_ERROR_TYPE, errorType);
	}
	
	/**
	 * 获取错误码
	 * @return 错误码
	 */
	public String getErrorType(){
		return getKeyValue(KEY_ERROR_TYPE);
	}
	
	public String getKeyValue(String key){
		Object result = this.get(key);
		return result == null ? "" : result.toString();
	}
	
}
