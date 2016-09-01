package com.inspur.gcloud.mc.engine.handle.service;

import java.util.List;

/**
 * <p>Title: 消息队列处理接口</p>
 * <p>Description: 用于处理短信成功队列和失败队列</p>
 * @author h.song
 * @date 2016年6月20日 下午2:28:28
 * @vision 1.0
 */
public interface IHandleMessageQueueService {
	
	/**
	 * 将失败消息添加入失败队列方法
	 * @param errorList 短信发送失败返回的信封表主键ID列表
	 */
	public void addErrorQueue(List<String> errorList);
	
	/**
	 * 将成功消息添加入成功队列方法
	 * @param successList 短信发送成功返回的信封表主键ID列表
	 */
	public void addSuccessQueue(List<String> successList);
	
	/**
	 * 处理失败队列的方法
	 * @param errorList 失败队列
	 */
	public void handleErrorQueue();
	
	/**
	 * 处理成功队列的方法
	 * @param successList 成功队列
	 */
	public void handleSuccessQueue();

}
