package com.inspur.gcloud.mc.engine.builder.service;

import java.util.List;

import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ShortMessage;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;

/**
 * <p>Title: 消息构建器接口</p>
 * <p>Description: 用于消息构建，解析各种消息对象</p>
 * @author h.song
 * @date 2016年6月17日 上午9:47:07
 * @vision 1.0
 */
public interface IMessageBuilderService {
	
	/**
	 * 构建站内消息对象方法
	 * @param message 消息对象
	 * @param envelopeList 信封对象列表
	 * @return 页面消息对象 MessageView
	 */
	public MessageView builderMessage(Message message, List<Envelope> envelopeList);
	
	/**
	 * 构建页面站内消息对象
	 * @param message 消息对象
	 * @param envelopeList 信封对象列表
	 * @return 页面消息对象 MessageView
	 */
	public MessageView builderViewMessage(Message message, List<Envelope> envelopeList);
	
	/**
	 * 构建站内消息回复消息对象
	 * @param message 消息对象
	 * @param envelope 信封对象
	 * @return 页面消息对象 MessageView
	 */
	public MessageView builderReplyMessage(Message message, Envelope envelope);
	
	/**
	 * 构建站内消息转发消息对象
	 * @param message 消息对象
	 * @param envelope 信封对象
	 * @return 页面消息对象 MessageView
	 */
	public MessageView builderForwardMessage(Message message, Envelope envelope);
	
	/**
	 * 构建短信对象
	 * @param message 消息对象
	 * @param envelope 信封对象
	 * @return 页面消息对象 MessageView
	 */
	public ShortMessage builderShortMessage(Message message, Envelope envelope);

}
