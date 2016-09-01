package com.inspur.gcloud.mc.common.data;

import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: 消息中心返回结果类</p>
 * <p>Description: 统一的返回结果对象</p>
 * @author h.song
 * @date 2016年6月22日 下午4:11:46
 * @vision 1.0
 */
public class ResultMap extends HashMap<String, Object> {

	private static final long serialVersionUID = -1668874384010563627L;
	
	/**
	 * 错误类型常量
	 */
	public static final String KEY_ERROR_TYPE = "errorType";
	
	/**
	 * 错误码常量
	 */
	public static final String KEY_ERROR_CODE = "errorCode";
	
	/**
	 * 是否成功
	 */
	private boolean isSuccess;
	
	/**
	 * 返回信息
	 */
	private String msg;
	
	/**
	 * 成功列表
	 */
	private List<String> successList;
	
	/**
	 * 失败列表
	 */
	private List<String> errorList;
	
	public ResultMap(){
		super();
		isSuccess = false;
		msg = "";
	}

	/**
	 * 获取是否成功标志
	 * @return 是否成功 boolean
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * 设置成功标志
	 * @param isSuccess 是否成功
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * 获取返回信息
	 * @return msg 信息 
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置返回信息
	 * @param msg 信息
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 获取成功列表
	 * @return 成功列表 List
	 */
	public List<String> getSuccessList() {
		return successList;
	}

	/**
	 * 设置成功列表
	 * @param successList 成功列表
	 */
	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}

	/**
	 * 获取失败列表
	 * @return 失败列表 List
	 */
	public List<String> getErrorList() {
		return errorList;
	}

	/**
	 * 设置失败列表
	 * @param errorList 失败列表
	 */
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	/**
	 * 增加错误类型
	 * @param errorType 错误类型
	 */
	public void addErrorType(String errorType){
		this.put(KEY_ERROR_TYPE, errorType);
	}
	
	/**
	 * 获取错误类型
	 * @return 错误类型
	 */
	public String getErrorType(){
		return getKeyValue(KEY_ERROR_TYPE);
	}
	
	/**
	 * 增加错误码
	 * @param errorCode 错误码
	 */
	public void addErrorCode(String errorCode){
		this.put(KEY_ERROR_CODE, errorCode);
	}
	
	/**
	 * 获取错误码
	 * @return 错误码
	 */
	public String getErrorCode(){
		return getKeyValue(KEY_ERROR_CODE);
	}
	
	/**
	 * 获取错误码内部方法
	 * @param key
	 * @return
	 */
	private String getKeyValue(String key){
		Object result = this.get(key);
		return result == null ? "" : result.toString();
	}
	
}
