package com.inspur.gcloud.mc.engine.builder.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.engine.builder.service.IMessageBuilderService;

@Service("builderService")
public class MessageBuilderServiceImpl implements IMessageBuilderService {

	@Override
	public MessageView builderMessage(Message message, List<Envelope> envelopeList) {
		
		MessageView messageView = new MessageView();
		String receiverId = null;
		String receiverName = null;
		String envelopeId = null;
		
		// 拼装收件人,收件ID,信封ID
		for(int i = 0; i < envelopeList.size(); i++){
			if(i == 0){
				envelopeId = envelopeList.get(i).getId();
				receiverId = envelopeList.get(i).getReceiverId();
				receiverName = envelopeList.get(i).getReceiverName();
			}else{
				envelopeId += ( "," + envelopeList.get(i).getId());
				receiverId += ( "," + envelopeList.get(i).getReceiverId());
				receiverName += ( "," + envelopeList.get(i).getReceiverName());
			}
		}
		
		messageView.setMessageTopic(message.getMessageTopic());
		messageView.setEnvelopeId(envelopeId);
		messageView.setMessageId(message.getId());
		messageView.setMessageType(envelopeList.get(0).getMessageType());
		messageView.setSenderId(envelopeList.get(0).getSenderId());
		messageView.setSenderName(envelopeList.get(0).getSenderName());
		messageView.setReceiverId(receiverId);
		messageView.setReceiverName(receiverName);
		messageView.setIsSchedule(envelopeList.get(0).getIsSchedule());
		messageView.setIsReadReceipt(envelopeList.get(0).getIsReadReceipt());
		messageView.setSendType(envelopeList.get(0).getSendType());
		messageView.setSendState(envelopeList.get(0).getSendState());
		messageView.setMessageLevel(message.getMessageLevel());
		messageView.setMessageContent(message.getMessageContent());

		return messageView;
	}

	@Override
	public MessageView builderReplyMessage(Message message, Envelope envelope) {
		
		MessageView messageView = new MessageView();
		messageView.setMessageId(message.getId());
		
		// 构建消息主题
		String messageTopic = message.getMessageTopic();
		messageView.setMessageTopic("回复：" + messageTopic);
		
		// 构建消息内容
		String senderName = envelope.getSenderName();
		String senderId = envelope.getSenderId();
		String messageContent = message.getMessageContent();
		Date createTime = envelope.getCreateTime();
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String sendTime = sdf.format(createTime);
		messageContent = "<br/><br/><hr>发件人："+senderName+"<br/>发送时间："+sendTime+"<br/>主题："+messageTopic+"<br/>" + messageContent;
		messageView.setMessageContent(messageContent);
		
		// 构建接收人
		messageView.setReceiverName(senderName);
		messageView.setReceiverId(senderId);
		
		return messageView;
	}

	@Override
	public MessageView builderForwardMessage(Message message, Envelope envelope) {
		MessageView messageView = new MessageView();
		messageView.setMessageId(message.getId());
		
		// 构建消息主题
		String messageTopic = message.getMessageTopic();
		messageView.setMessageTopic("转发：" + messageTopic);
		
		// 构建消息内容
		String senderName = envelope.getSenderName();
		String messageContent = message.getMessageContent();
		Date createTime = envelope.getCreateTime();
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String sendTime = sdf.format(createTime);
		messageContent = "<br/><br/><hr>发件人："+senderName+"<br/>发送时间："+sendTime+"<br/>主题："+messageTopic+"<br/>" + messageContent;
		messageView.setMessageContent(messageContent);
		
		return messageView;
	}
	@Override
	public MessageView builderViewMessage(Message message, Envelope envelope) {
		MessageView messageView = new MessageView();
		// 构建信封信息
		messageView.setEnvelopeId(envelope.getId());
		messageView.setMessageId(message.getId());
		
		// 构建消息主题
		messageView.setMessageTopic(message.getMessageTopic());
		
		// 构建消息内容
		messageView.setMessageType(envelope.getMessageType());
		messageView.setSenderId(envelope.getSenderId());
		messageView.setSenderName(envelope.getSenderName());
		messageView.setReceiverId(envelope.getReceiverId());
		messageView.setReceiverName(envelope.getReceiverName());
		messageView.setIsSchedule(envelope.getIsSchedule());
		messageView.setIsReadReceipt(envelope.getIsReadReceipt());
		messageView.setSendType(envelope.getSendType());
		messageView.setSendState(envelope.getSendState());
		messageView.setMessageLevel(message.getMessageLevel());
		messageView.setMessageContent(message.getMessageContent());

		return messageView;
	}

}
