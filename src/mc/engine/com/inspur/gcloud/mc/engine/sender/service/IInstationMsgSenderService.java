package com.inspur.gcloud.mc.engine.sender.service;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;

public interface IInstationMsgSenderService {
	
	/**
	 * 发送消息
	 * @param messageObject
	 * @return
	 */
	public ResultMap sendMessage(MessageObject messageObject);

}
