package com.inspur.gcloud.mc.engine.sender.service;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;

public interface IMessageSenderService {
	/**
	 * 发送短信
	 * @param messageObject
	 * @return
	 */
	public ResultMap sendMessage(MessageObject messageObject);
}
