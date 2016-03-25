package com.inspur.gcloud.mc.engine.dispatcher.service.impl;

import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.McConstants;
import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.engine.dispatcher.service.IMessageDispatcherService;
import com.inspur.gcloud.mc.engine.sender.service.IInstationMsgSenderService;

/**
 * <p>消息分发器</p>
 * <p>业务场景：将消息对象进行解析，分发至各个消息发送器</p>
 * 
 * @author Songh
 * @version 1.0
 * @since 2016年3月19日
 *
 */
@Service("dispatcherService")
public class MessageDispatcherService implements IMessageDispatcherService{
	
	@Autowired
	private IInstationMsgSenderService iInstationMsgSenderService;
	
	/**
	 * 消息服务消息分发器
	 * @param messageObject 消息对象
	 * @return {resultMap}
	 */
	public ResultMap messageDispatcher(MessageObject messageObject){
		
		ResultMap sendResultMap = new ResultMap();
		
		// 获取发送消息类型
		String messageType = messageObject.getMessageType();
		if(StringUtils.isNotEmpty(messageType)){
			if(McConstants.MESSAGE_TYPE_INSTATIONMSG.equals(messageType)){
				// 站内消息
				sendResultMap = iInstationMsgSenderService.sendMessage(messageObject);
			}else if(McConstants.MESSAGE_TYPE_INSTATIONMAIL.equals(messageType)){
				// 站内邮件
				
			}else if(McConstants.MESSAGE_TYPE_OUTSTATIONMAIL.equals(messageType)){
				// 站外邮件
				
			}else if(McConstants.MESSAGE_TYPE_MESSAGE.equals(messageType)){
				// 短信
				
			}else{
				// 未匹配类型
				sendResultMap.setSuccess(false);
				sendResultMap.addErrorType(McConstants.MESSAGE_NO_TYPE);
				sendResultMap.setMsg("错误：消息类型无法匹配，无法进行发送！");
			}
		}else{
			sendResultMap.setSuccess(false);
			sendResultMap.addErrorType(McConstants.ERROR_TYPE_DISPATCHER);
			sendResultMap.setMsg("错误：消息类型为空，无法进行发送！");
		}
		return sendResultMap;
	}

}
