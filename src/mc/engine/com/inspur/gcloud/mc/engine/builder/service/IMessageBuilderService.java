package com.inspur.gcloud.mc.engine.builder.service;

import java.util.List;

import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;

public interface IMessageBuilderService {
	
	public MessageView builderMessage(Message message, List<Envelope> envelopeList);
	
	public MessageView builderViewMessage(Message message, Envelope envelope);
	
	public MessageView builderReplyMessage(Message message, Envelope envelope);
	
	public MessageView builderForwardMessage(Message message, Envelope envelope);

}
