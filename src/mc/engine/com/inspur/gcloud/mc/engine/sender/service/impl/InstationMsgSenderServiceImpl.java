package com.inspur.gcloud.mc.engine.sender.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.core.service.IEnvelopeService;
import com.inspur.gcloud.mc.core.service.IMessageService;
import com.inspur.gcloud.mc.engine.sender.service.IInstationMsgSenderService;

@Service("instationMsgSenderService")
public class InstationMsgSenderServiceImpl implements IInstationMsgSenderService {
	
	@Autowired
	private IMessageService messageSevice;
	@Autowired
	private IEnvelopeService envelopeService;

	@Override
	public ResultMap sendMessage(MessageObject messageObject) {
		
		if(messageObject != null){
			if(messageObject.getEnvlopeCount() > 1){
				String messageId = messageSevice.saveMessage(messageObject.getMessage());
				int envelopeCount = envelopeService.saveEnvelope(messageObject.getEnvelopeList().get(0), messageId);
			}else{
				String messageId = messageSevice.saveMessage(messageObject.getMessage());
				int envelopeCount = envelopeService.batchSaveEnvelope(messageObject.getEnvelopeList(), messageId);
			}
			
		}else{
			
		}
		return null;
	}

}
