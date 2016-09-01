package com.inspur.gcloud.mc.engine.sender.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.common.data.ShortMessage;
import com.inspur.gcloud.mc.common.messagepool.MessagePool;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.core.service.IShortMsgService;
import com.inspur.gcloud.mc.engine.builder.service.IMessageBuilderService;
import com.inspur.gcloud.mc.engine.sender.service.IMessageSenderService;
import com.lc.gcloud.framework.util.GCloudUtil;

/**
 * <p>Title: 消息发送器服务实现类</p>
 * <p>Description: </p>
 * @author h.song
 * @date 2016年6月17日 上午9:47:22
 * @vision 1.0
 */
@Service("messageSenderService")
public class MessageSenderServiceImpl implements IMessageSenderService {
	
	private static Log log = LogFactory.getLog(MessageSenderServiceImpl.class);

	@Autowired
	private IShortMsgService shortMsgService;
	@Autowired
	private IMessageBuilderService messageBuilderService;
	
	/**
	 * 发送短信方法
	 */
	public ResultMap sendMessage(MessageObject messageObject) {
		// 定义统一返回结果对象
		ResultMap resultMap = new ResultMap();
		// 存储数据库，并获取已存入数据库的消息对象
		MessageObject successMessageObject = this.sendMessageByDB(messageObject);
		// 放入消息队列
		boolean flag = this.sendMessageByQueue(successMessageObject);
		if(flag){
			resultMap.setSuccess(flag);
		}else{
			resultMap.setSuccess(flag);
		}
		return resultMap;
		
	}
	
	/**
	 * 消息发送保存数据库方法
	 * @param messageObject 消息对象
	 * @return MessageObject 成功保存消息对象
	 */
	@Transactional
	public MessageObject sendMessageByDB(MessageObject messageObject){
		
		if(messageObject != null){
			// 判断消息对象不为空，开始将消息存入数据库
			if(messageObject.getEnvlopeCount() <= 1){
				// 单条消息发送
				String messageId = shortMsgService.insertMessage(messageObject.getMessage());
				shortMsgService.saveEnvelope(messageObject.getEnvelopeList().get(0), messageId);
			} else {
				// 多条消息发送
				String messageId = shortMsgService.insertMessage(messageObject.getMessage());
				List<Envelope> envelopeList = messageObject.getEnvelopeList();
				messageObject.getMessage().setId(GCloudUtil.getInstance().getNextSeqId(32));
				// 循环保存信封表数据
				for(int i = 0; i < envelopeList.size(); i++){
					try{
						shortMsgService.saveEnvelope(envelopeList.get(i), messageId);
					} catch(Exception e) {
						if(log.isDebugEnabled()){
							log.debug("信封表保存数据出错："+e.getMessage());
						}
						// 将未保存入数据库的信封移除
						envelopeList.remove(i);
					}
				}
				// 组装成功存入数据库消息对象
				messageObject.setEnvelopeList(envelopeList);
			}
			return messageObject;
		}else{
			// 消息对象为空，返回null
			return null;
		}
	}
	
	/**
	 * 发送消息至消息队列方法
	 * @param messageObject 已保存短信消息对象
	 * @return boolean flag {true：成功、false：失败}
	 */
	public boolean sendMessageByQueue(MessageObject messageObject){
		
		// 定义消息队列
		MessagePool messagePool = MessagePool.getInstance();

		try{
			// 将已保存入数据库的短信放入短信队列中
			for(int i = 0; i < messageObject.getEnvlopeCount(); i++){
				Message message = messageObject.getMessage();
				Envelope envelope = messageObject.getEnvelopeList().get(i);
				// 创建短信对象
				ShortMessage shortMessage = messageBuilderService.builderShortMessage(message, envelope);
				// 加入消息队列
				messagePool.setSMessage2MessageQueue(shortMessage);
			}
			return true;
		}catch(Exception e){
			//打算在这里加逻辑
			if(log.isDebugEnabled()){
				log.debug("短信发送放入发送消息队列出错："+e.getMessage());	
			}
			
			return false;
		}
		
	}

}
