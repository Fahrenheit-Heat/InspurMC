package com.inspur.gcloud.mc.engine.dispatcher.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.McDataObjectConstants;
import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.common.util.McErrorOptionConstants;
import com.inspur.gcloud.mc.common.util.McLoggerUtil;
import com.inspur.gcloud.mc.engine.dispatcher.service.IMessageDispatcherService;
import com.inspur.gcloud.mc.engine.sender.service.IInstationMsgSenderService;
import com.inspur.gcloud.mc.engine.sender.service.IMessageSenderService;

/**
 * <p>Title: 消息分发器实现类</p>
 * <p>Description: 用于将消息对象进行解析，分发至各个消息发送器</p>
 * @author h.song
 * @date 2016年3月19日 下午5:43:15
 * @vision 1.0
 */
@Service("dispatcherService")
public class MessageDispatcherService implements IMessageDispatcherService{
	
	@Autowired
	private IInstationMsgSenderService instationMsgSenderService;
	@Autowired
	private IMessageSenderService messageSenderService;
	
	/**
	 * 消息服务消息分发器
	 * @param messageObject 消息对象
	 * @return {resultMap}
	 */
	@Override
	public ResultMap messageDispatcher(MessageObject messageObject){
		
		ResultMap sendResultMap = new ResultMap();
		
		// 获取发送消息类型
		String messageType = messageObject.getMessageType();
		if(StringUtils.isNotEmpty(messageType)){
			if(McDataObjectConstants.MESSAGE_TYPE_INSTATIONMSG.equals(messageType)){
				// 站内消息
				sendResultMap = instationMsgSenderService.sendMessage(messageObject);
			}else if(McDataObjectConstants.MESSAGE_TYPE_INSTATIONMAIL.equals(messageType)){
				// 站内邮件
				
			}else if(McDataObjectConstants.MESSAGE_TYPE_OUTSTATIONMAIL.equals(messageType)){
				// 站外邮件
				
			}else if(McDataObjectConstants.MESSAGE_TYPE_MESSAGE.equals(messageType)){
				// 短信
				sendResultMap = messageSenderService.sendMessage(messageObject);
			}else{
				// 未匹配类型
				sendResultMap.setSuccess(false);
				sendResultMap.addErrorType(McErrorOptionConstants.MESSAGE_NO_TYPE);
				sendResultMap.addErrorCode(McErrorOptionConstants.MESSAGE_NO_TYPE_CODE);
				sendResultMap.setMsg("错误：消息类型无法匹配，无法进行发送！");
			}
		} else {
			sendResultMap.setSuccess(false);
			sendResultMap.addErrorType(McErrorOptionConstants.ERROR_TYPE_DISPATCHER);
			sendResultMap.addErrorCode(McErrorOptionConstants.ERROR_CODE_DISPATCHER);
			sendResultMap.setMsg("错误：消息类型为空，无法进行发送！");
		}
		
		McLoggerUtil.getInstance().systemLog("", sendResultMap.getErrorCode(), sendResultMap.getMsg());
		
		return sendResultMap;
	}

}
