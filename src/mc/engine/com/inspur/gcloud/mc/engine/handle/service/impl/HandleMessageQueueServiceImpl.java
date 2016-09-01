package com.inspur.gcloud.mc.engine.handle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inspur.gcloud.mc.common.McDataObjectConstants;
import com.inspur.gcloud.mc.common.data.ShortMessage;
import com.inspur.gcloud.mc.common.messagepool.MessagePool;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.core.service.IShortMsgService;
import com.inspur.gcloud.mc.engine.builder.service.IMessageBuilderService;
import com.inspur.gcloud.mc.engine.handle.service.IHandleMessageQueueService;

/**
 * <p>Title: 消息队列处理实现类</p>
 * <p>Description: 用于处理短信成功队列和失败队列</p>
 * @author h.song
 * @date 2016年6月20日 下午2:24:18
 * @vision 1.0
 */
@Service("handleMessageQueueService")
public class HandleMessageQueueServiceImpl implements IHandleMessageQueueService{
	
	private static final boolean MESSAGE_IS_RESEND = true;
	
	private static final int MESSAGE_RESEND_COUNT = 3;
	
	private MessagePool messagePool = MessagePool.getInstance();
	
	@Autowired
	private IShortMsgService shortMsgService;
	@Autowired
	private IMessageBuilderService messageBuilderService;
	
	@Override
	public void addErrorQueue(List<String> errorList) {
		if(MESSAGE_IS_RESEND){
			// 进行重发处理
			for(int i = 0; i < errorList.size(); i++){
				// 获取信封表主键ID
				String envelopeId = errorList.get(i); 
				// 通过主键ID获取信封对象
				Envelope envelope = shortMsgService.findEnvelopeById(envelopeId);
				// 获取重发次数 +1
				int resendCount = envelope.getResendCount() + 1;
				// 设置信封对象重发次数
				envelope.setResendCount(resendCount);
				// 更新重发次数
				shortMsgService.updateEnvelope(envelope);
				if(envelope != null){
					// 判断对象不为空，获取消息主键ID
					String messageId = envelope.getMessageId();
					// 通过主键ID获取消息对象
					Message message = shortMsgService.findMessageById(messageId);
					// 构建短信对象
					ShortMessage shortMessage = messageBuilderService.builderShortMessage(message, envelope);
					// 将短信对象放入失败队列
					messagePool.setSMessage2FailQueue(shortMessage);
				}
			}
		}else{
			// 不进行重发处理
			shortMsgService.batchUpdateEnvelopeSendState(errorList, McDataObjectConstants.SEND_STATE_FAIL);
		}
	}

	@Override
	public void addSuccessQueue(List<String> successList) {
		for(int i = 0; i < successList.size(); i++){
			messagePool.setSMessage2SuccessQueue(successList.get(i));
		}
	}

	@Override
	public void handleErrorQueue(){
		// 获取失败队列
		BlockingQueue<ShortMessage> errorQueue = messagePool.geFaillQueue();
		if(!errorQueue.isEmpty()){
			for(int i = 0; i < errorQueue.size(); i++){
				ShortMessage shortMessage = messagePool.getSMessageFromFailQueue();
				int resendCount = shortMessage.getResendCount();
				if(resendCount <= MESSAGE_RESEND_COUNT){
					// 小于重发次数，进行重发处理
					messagePool.setSMessage2MessageQueueHead(shortMessage);
				}else{
					// 大于重发次数，不再进行处理，更新状态为发送失败
					String errorId = shortMessage.getEnvelopeId();
					List<String> errorList = new ArrayList<String>();
					errorList.add(errorId);
					shortMsgService.batchUpdateEnvelopeSendState(errorList, McDataObjectConstants.SEND_STATE_FAIL);
				}
			}
		}
	}

	@Override
	public void handleSuccessQueue() {
		List<String> successList = new ArrayList<String>();
		BlockingQueue<String> successQueue = messagePool.getSuccessQueue();
		for(int i = 0; i < successQueue.size(); i++){
			String successId = messagePool.getSMessageFromSuccessQueue();
			if(StringUtils.isEmpty(successId)){
				break;
			}
			successList.add(successId);
		}
		shortMsgService.batchUpdateEnvelopeSendState(successList, McDataObjectConstants.SEND_STATE_SUCCESS);
	}

}
