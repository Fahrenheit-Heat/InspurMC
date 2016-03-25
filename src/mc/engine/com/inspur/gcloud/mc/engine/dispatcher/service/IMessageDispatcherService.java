package com.inspur.gcloud.mc.engine.dispatcher.service;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;

public interface IMessageDispatcherService {
	
	/**
	 * 
	 * @param messageObject
	 * @return
	 */
	public ResultMap messageDispatcher(MessageObject messageObject);

}
