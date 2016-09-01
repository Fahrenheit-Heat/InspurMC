package com.inspur.gcloud.mc.engine.parser.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.McDataObjectConstants;
import com.inspur.gcloud.mc.common.McHandleTextConstants;
import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.common.util.McErrorOptionConstants;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.engine.parser.service.IMessageParserService;

/**
 * <p>消息对象解析器</p>
 * 
 * @author Songh
 * @version 1.0
 * @since 2016年3月23日
 */
@Service("parserService")
public class MessageParserServiceImpl implements IMessageParserService {
	
	@Override
	public ResultMap instationMsgParser(MessageView messageView) {
		// 当前日期时间
		Date date = new Date();
		// 定义返回结果集
		ResultMap parserResultMap = new ResultMap();
		
		if(messageView != null){
			// 视图对象不为空，开始进行解析
			// 解析Message对象
			Message message = new Message();
			// 获取消息主题，若为空初始化为“（无主题）”
			String messageTopic = messageView.getMessageTopic() != "" ? messageView.getMessageTopic() : McHandleTextConstants.HANDLE_NO_TOPIC;
			message.setMessageTopic(messageTopic);
			message.setMessageContent(messageView.getMessageContent());
			message.setMessageLevel("");
			message.setRemark("");
			//涉及到修改时
			if(messageView.getMessageId() != null && !messageView.getMessageId().equals("")){
				message.setId(messageView.getMessageId());
			}
			
			// 解析envelope对象
			List<String> receiverIdList = messageView.getReceiverIdList();
			List<Envelope> envelopeList = new ArrayList<Envelope>();
			List<String> envelopeId = messageView.getEnvelopeIdList();
			MessageObject messageObject = new MessageObject();
			if(receiverIdList.size() > 0){
				for(int i = 0; i < receiverIdList.size(); i++){
					// 创建信封对象
					Envelope envelope = new Envelope();
					// 设置信封ID
					if(messageView.getEnvelopeId() != null && !messageView.getEnvelopeId().equals("")){
						envelope.setId(envelopeId.get(i));
					}
					
					// 处理接收人类型
					String receiverType = messageView.getReceiverType() ==null ? McDataObjectConstants.RECEIVER_TYPE_ORAGINID : messageView.getReceiverType();
					envelope.setReceiverType(receiverType);
					
					// 根据接收人类型处理接收人信息
					if(McDataObjectConstants.RECEIVER_TYPE_SYSTEM.equals(receiverType)){
						// 接收人类型为系统消息，置空接收人信息
						envelope.setReceiverId("");
						envelope.setReceiverName(McHandleTextConstants.HANDLE_ALL_USER);
					}else{
						// 接收人类型不为系统消息，进行组装接收人信息
						envelope.setReceiverId(receiverIdList.get(i));
						envelope.setReceiverName(messageView.getReceiverNameList().get(i));
					}
					
					// 处理发送状态，若未设置发送状态，初始化发送成功状态
					String sendState = messageView.getSendState() == null ? McDataObjectConstants.SEND_STATE_SUCCESS : messageView.getSendState();
					envelope.setSendState(sendState);
					
					// 处理发送类型，若未设置发送类型，初始化发送类型为原件
					String sendType = messageView.getSendType() == null ? McDataObjectConstants.SEND_TYPE_ORIGINAL : messageView.getSendType();
					envelope.setSendType(sendType);
					
					// 处理接受状态，若未设置接受状态，初始化接受状态为未读
					String receiveState = messageView.getReceiveState() == null ? McDataObjectConstants.RECEIVE_STATE_NOREAD : messageView.getReceiveState();
					envelope.setReceiveState(receiveState);
					
					envelope.setMessageType(McDataObjectConstants.MESSAGE_TYPE_INSTATIONMSG);
					envelope.setRelatedMessageId(messageView.getRelatedMessageId());
					envelope.setRelatedPath(messageView.getRelatedPath());
					envelope.setSenderId(messageView.getSenderId());
					envelope.setSenderName(messageView.getSenderName());
					
					envelope.setIsReadReceipt("0");
					
					envelope.setIsSchedule("");
					envelope.setHasReceipt("");
					envelope.setSendLevel("");
					envelope.setSendTime(date);
					envelope.setReadTime(null);
					envelope.setCreateTime(date);
					envelope.setUpdateTime(null);
					envelope.setRemark("");
					
					envelopeList.add(envelope);
				}
			}else{
				// 无法获取接收人信息
				parserResultMap.setSuccess(false);
				parserResultMap.addErrorType(McErrorOptionConstants.ERROR_TYPE_PARSER);
				parserResultMap.setMsg("错误：无法获取接收人信息，无法进行解析！");
			}
			
			// 组装messageObject
			messageObject.setMessage(message);
			messageObject.setEnvelopeList(envelopeList);
			
			// 组装返回结果集
			parserResultMap.setSuccess(true);
			parserResultMap.put("messageObject", messageObject);
			parserResultMap.setMsg("提示：解析完成！");
		}else{
			// 无法获取视图消息对象
			parserResultMap.setSuccess(false);
			parserResultMap.addErrorType(McErrorOptionConstants.ERROR_TYPE_PARSER);
			parserResultMap.setMsg("错误：无法获取视图消息对象，无法进行解析！");
		}
		return parserResultMap;
	}

	@Override
	public ResultMap shortMessageParser(MessageView messageView) {
		// 当前日期时间
		Date date = new Date();
		// 定义返回结果集
		ResultMap parserResultMap = new ResultMap();
		
		if(messageView != null){
			// 视图对象不为空，开始进行解析
			// 解析Message对象
			Message message = new Message();
			message.setMessageTopic(messageView.getMessageTopic());
			message.setMessageContent(messageView.getMessageContent());
			message.setMessageLevel("");
			message.setRemark("");
			//涉及到修改时
			if(messageView.getMessageId() != null && !messageView.getMessageId().equals("")){
				message.setId(messageView.getMessageId());
			}
			
			// 解析envelope对象
			List<String> receiverIdList = messageView.getReceiverIdList();
			List<Envelope> envelopeList = new ArrayList<Envelope>();
			List<String> envelopeId = messageView.getEnvelopeIdList();
			MessageObject messageObject = new MessageObject();
			if(receiverIdList.size() > 0){
				for(int i = 0; i < receiverIdList.size(); i++){
					Envelope envelope = new Envelope();
					if(messageView.getEnvelopeId() != null && !messageView.getEnvelopeId().equals("")){
						envelope.setId(envelopeId.get(i));
					}
					
					// 处理发送状态，若未设置发送状态，初始化发送成功状态
					String sendState = messageView.getSendState() == null ? McDataObjectConstants.SEND_STATE_LOADING : messageView.getSendState();
					envelope.setSendState(sendState);
					
					// 处理发送类型，若未设置发送类型，初始化发送类型为原件
					String sendType = messageView.getSendType() == null ? McDataObjectConstants.SEND_TYPE_ORIGINAL : messageView.getSendType();
					envelope.setSendType(sendType);
					
					// 处理接受状态，若未设置接受状态，初始化接受状态为未读
					String receiveState = messageView.getReceiveState() == null ? McDataObjectConstants.RECEIVE_STATE_NOREAD : messageView.getReceiveState();
					envelope.setReceiveState(receiveState);
					
					envelope.setMessageType(McDataObjectConstants.MESSAGE_TYPE_MESSAGE);
					envelope.setSenderId(messageView.getSenderId());
					envelope.setSenderName(messageView.getSenderName());
					envelope.setReceiverId(receiverIdList.get(i));
					envelope.setReceiverName(messageView.getReceiverNameList().get(i));
					
					envelope.setResendCount(0);
					envelope.setIsReadReceipt("0");
					envelope.setIsSchedule("");
					envelope.setHasReceipt("");
					envelope.setSendLevel("");
					envelope.setSendTime(date);
					envelope.setReadTime(null);
					envelope.setCreateTime(date);
					envelope.setUpdateTime(null);
					envelope.setRemark("");
					
					envelopeList.add(envelope);
				}
			}else{
				// 无法获取接收人信息
				parserResultMap.setSuccess(false);
				parserResultMap.addErrorType(McErrorOptionConstants.ERROR_TYPE_PARSER);
				parserResultMap.setMsg("错误：无法获取接收人信息，无法进行解析！");
			}
			
			// 组装messageObject
			messageObject.setMessage(message);
			messageObject.setEnvelopeList(envelopeList);
			
			// 组装返回结果集
			parserResultMap.setSuccess(true);
			parserResultMap.put("messageObject", messageObject);
			parserResultMap.setMsg("提示：解析完成！");
		}else{
			// 无法获取视图消息对象
			parserResultMap.setSuccess(false);
			parserResultMap.addErrorType(McErrorOptionConstants.ERROR_TYPE_PARSER);
			parserResultMap.setMsg("错误：无法获取视图消息对象，无法进行解析！");
		}
		return parserResultMap;
	}

}
