package com.inspur.gcloud.mc.engine.sender;

import java.util.List;

import com.inspur.gcloud.mc.common.data.MessageObject;

/**
 * <p>发送器接口类</p>
 * <p>作用：统一发送器中含有的方法</p>
 * 
 * @author Songh
 * @version 1.0
 * @since 2016年3月19日
 *
 */
public interface ISender {
	
	/**
	 * 单条发送方法
	 * @param messageObject
	 * @return
	 */
	public String send(MessageObject messageObject);
	
	/**
	 * 群发送方法
	 * @param messageObjectList
	 * @return
	 */
	public String groupSend(List<MessageObject> messageObjectList);

}
