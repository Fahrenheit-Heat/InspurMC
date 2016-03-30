package com.inspur.gcloud.mc.engine.parser.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.McConstants;
import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.core.data.Attach;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.engine.parser.service.IMessageParserService;
import com.lc.gcloud.framework.util.GCloudUtil;

/**
 * <p>消息对象解析器</p>
 * 
 * @author Songh
 * @version 1.0
 * @since 2016年3月23日
 */
@Service("parserService")
public class MessageParserService implements IMessageParserService {
	
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
			MessageObject messageObject = new MessageObject();
			if(receiverIdList.size() > 0){
				for(int i = 0; i < receiverIdList.size(); i++){
					Envelope envelope = new Envelope();
					if(messageView.getEnvelopeId() != null && !messageView.getEnvelopeId().equals("")){
						envelope.setId(messageView.getEnvelopeId());
					}
					envelope.setMessageType(messageView.getMessageType());
					envelope.setRelatedMessageId("");
					envelope.setRelatedPath("");
					envelope.setSenderId(messageView.getSenderId());
					envelope.setSenderName(messageView.getSenderName());
					envelope.setReceiverId(receiverIdList.get(i));
					envelope.setReceiverName(messageView.getReceiverName());
					envelope.setReceiverType("");
					envelope.setSendState(messageView.getSendState());
					envelope.setIsSchedule("");
					envelope.setIsReadReceipt("");
					envelope.setHasReceipt("");
					envelope.setSendType("");
					envelope.setReceiverType("");
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
				parserResultMap.addErrorType(McConstants.ERROR_TYPE_PARSER);
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
			parserResultMap.addErrorType(McConstants.ERROR_TYPE_PARSER);
			parserResultMap.setMsg("错误：无法获取视图消息对象，无法进行解析！");
		}
		return parserResultMap;
	}

}
