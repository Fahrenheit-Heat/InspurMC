package com.inspur.gcloud.mc.engine.builder.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.McHandleTextConstants;
import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ShortMessage;
import com.inspur.gcloud.mc.common.util.HandleMessageContentUtil;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.engine.builder.service.IMessageBuilderService;

/**
 * <p>Title: 消息对象构建服务实现类</p>
 * <p>Description: 用于将Message对象和Envelope对象构建为需要的消息视图对象</p>
 * @author h.song
 * @date 2016年6月17日 上午9:45:10
 * @vision 1.0
 */
@Service("builderService")
public class MessageBuilderServiceImpl implements IMessageBuilderService {
	
	@Override
	public MessageView builderMessage(Message message, List<Envelope> envelopeList) {
		
		// 定义页面消息对象
		MessageView messageView = new MessageView();
		// 定义接收人ID
		String receiverId = null;
		// 定义接收人名称
		String receiverName = null;
		// 定义信封表ID
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
		
		// 组装页面消息对象
		messageView.setMessageTopic(McHandleTextConstants.HANDLE_NO_TOPIC.equals(message.getMessageTopic()) ? "" : message.getMessageTopic());
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
		messageView.setReceiverType(envelopeList.get(0).getReceiverType());
		messageView.setMessageLevel(message.getMessageLevel());
		messageView.setMessageContent(message.getMessageContent());

		// 返回页面消息对象
		return messageView;
	}

	@Override
	public MessageView builderReplyMessage(Message message, Envelope envelope) {
		
		// 定义页面消息对象
		MessageView messageView = new MessageView();
		// 赋值消息主键ID
		messageView.setMessageId(message.getId());
		
		// 构建消息主题
		String messageTopic = message.getMessageTopic();
		messageView.setMessageTopic(McHandleTextConstants.HANDLE_REPLAY_PRE + messageTopic);
		
		// 构建消息内容
		String senderName = envelope.getSenderName();
		// 获取发送人ID
		String senderId = envelope.getSenderId();
		// 获取消息内容
		String messageContent = message.getMessageContent();
		// 获取当前时间
		Date createTime = envelope.getCreateTime();
		// 时间格式化
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String sendTime = sdf.format(createTime);
		// 构建消息内容
		messageContent = HandleMessageContentUtil.getInstance().addCSSMessageContext(senderName, sendTime, messageTopic, messageContent);
		// 设置消息内容
		messageView.setMessageContent(messageContent);
		
		// 设置关联消息ID
		messageView.setRelatedMessageId(message.getId());
		// 设置发送类型
		messageView.setSendType("1");
		// 设置接收人类型
		messageView.setReceiverType(envelope.getReceiverType());
		
		// 构建接收人
		messageView.setReceiverName(senderName);
		messageView.setReceiverId(senderId);
		
		// 返回页面消息对象
		return messageView;
	}

	@Override
	public MessageView builderForwardMessage(Message message, Envelope envelope) {
		MessageView messageView = new MessageView();
		// 构建信封信息
		messageView.setMessageId(message.getId());
		
		// 设置关联消息ID
		messageView.setRelatedMessageId(message.getId());
		
		// 构建消息主题
		String messageTopic = message.getMessageTopic();
		messageView.setMessageTopic(McHandleTextConstants.HANDLE_FORWARD_PRE + messageTopic);
		
		// 构建消息内容
		String senderName = envelope.getSenderName();
		String messageContent = message.getMessageContent();
		
		// 设置发送人名称
		messageView.setSenderName(senderName);
		// 获取当前日期
		Date createTime = envelope.getCreateTime();
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String sendTime = sdf.format(createTime);
		// 构建消息内容
		messageContent = HandleMessageContentUtil.getInstance().addCSSMessageContext(senderName, sendTime, messageTopic, messageContent);
		// 设置消息内容
		messageView.setMessageContent(messageContent);
		
		// 返回页面消息对象
		return messageView;
	}
	
	@Override
	public MessageView builderViewMessage(Message message, List<Envelope> envelopeList) {
		MessageView messageView = new MessageView();
		
		// 定义信封主键ID
		String envelopeId = null;
		// 定义接收人名称
		String receiverName = null;
		// 定义接收人ID
		String receiverId = null;
		
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
		// 构建信封信息
		messageView.setEnvelopeId(envelopeId);
		messageView.setMessageId(message.getId());
		
		// 构建消息主题
		messageView.setMessageTopic(message.getMessageTopic());
		
		// 构建消息内容
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

		// 返回页面消息对象
		return messageView;
	}
	
	@Override
	public ShortMessage builderShortMessage(Message message, Envelope envelope){
		
		//定义短信对象
		ShortMessage shortMessage = new ShortMessage();
		
		// 设置短信对象属性
		shortMessage.setEnvelopeId(envelope.getId());
		shortMessage.setPhoneNumber(envelope.getReceiverId());
		shortMessage.setSenderId(envelope.getSenderId());
		shortMessage.setSenderName(envelope.getSenderName());
		shortMessage.setResendCount(envelope.getResendCount());
		shortMessage.setMessageId(message.getId());
		shortMessage.setMessageContent(message.getMessageContent());
		
		// 返回短信对象
		return shortMessage;
	}
}
