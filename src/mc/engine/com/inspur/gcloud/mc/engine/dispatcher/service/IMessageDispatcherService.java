package com.inspur.gcloud.mc.engine.dispatcher.service;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;

/**
 * <p>Title: 消息分发器接口</p>
 * <p>Description: 用于将消息对象进行解析，分发至各个消息发送器</p>
 * @author h.song
 * @date 2016年6月20日 下午5:42:49
 * @vision 1.0
 */
public interface IMessageDispatcherService {
	
	/**
	 * 消息分发方法
	 * @param messageObject 消息抽象对象
	 * @return 平台统一返回结果对象 ResultMap
	 */
	public ResultMap messageDispatcher(MessageObject messageObject);

}
