package com.inspur.gcloud.ext.sender;

import java.util.List;

import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.common.data.ShortMessage;

public interface IShortMessageSender {
	
	/**
	 * 批量发送短信实现方法
	 * @param messageList 短信列表
	 * @return {发送结果对象 ResultMap}
	 */
	public ResultMap sendMessageImpl(List<ShortMessage> messageList);
	
	/**
	 * 发送短信实现方法
	 * @param shortMessage 短信对象
	 * @return {发送结果对象 ResultMap}
	 */
	public ResultMap sendMessageImpl(ShortMessage shortMessage);

}
