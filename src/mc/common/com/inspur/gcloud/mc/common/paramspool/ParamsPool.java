package com.inspur.gcloud.mc.common.paramspool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * <p>Title: 参数池</p>
 * <p>Description: 存放全部有效参数</p>
 * @author h.song
 * @date 2016年6月16日 下午2:44:39
 * @vision 1.0
 */
public class ParamsPool {
	
	private static ParamsPool instance = new ParamsPool();
	
	private ParamsPool(){}
	
	public static ParamsPool getInstance(){
		return instance;
	}
	
	private final Map<String, String> paramsMap = new ConcurrentHashMap<String, String>();
	
	/**
	 * 通过参数编码获取参数值方法
	 * @param parameterCode 参数编码
	 * @return String parameterValue 参数值
	 */
	public String getValue(String parameterCode){
		return paramsMap.get(parameterCode);
	}
	
	/**
	 * 设置参数值
	 * @param parameterCode 参数编码
	 * @param parameterValue 参数值
	 */
	public void setValue(String parameterCode, String parameterValue){
		paramsMap.put(parameterCode, parameterValue);
	}

}
