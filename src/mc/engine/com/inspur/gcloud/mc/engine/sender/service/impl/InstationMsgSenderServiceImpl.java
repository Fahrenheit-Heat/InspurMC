package com.inspur.gcloud.mc.engine.sender.service.impl;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.core.data.Message;
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
	@Transient
	public ResultMap sendMessage(MessageObject messageObject) {
		ResultMap resultMap = new ResultMap();
		if(messageObject != null){
			if(messageObject.getEnvlopeCount() == 1){
				String messageId = messageSevice.insertMessage(messageObject.getMessage());
				envelopeService.saveEnvelope(messageObject.getEnvelopeList().get(0), messageId);
			}else{
				Message message = messageObject.getMessage();
				messageSevice.insertMessage(message);
				envelopeService.batchInsertEnvelope(messageObject.getEnvelopeList(), message);
			}
			resultMap.setSuccess(true);
			resultMap.setMsg("发送器：发送成功！");
		}else{
			resultMap.setSuccess(false);
			resultMap.setMsg("发送器：发送失败！");
		}
		return resultMap;
	}

}
