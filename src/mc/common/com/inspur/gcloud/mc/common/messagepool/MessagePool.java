package com.inspur.gcloud.mc.common.messagepool;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import com.inspur.gcloud.mc.common.data.ShortMessage;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author h.song
 * @date 2016年6月16日 下午2:22:22
 * @vision 1.0
 */
public final class MessagePool {
	
	// 定义消息队列
	public final LinkedBlockingDeque<ShortMessage> messageQueue = new LinkedBlockingDeque<ShortMessage>();
	
	// 定义成功队列
	public final BlockingQueue<String> successQueue = new LinkedBlockingQueue<String>();
	
	// 定义失败队列
	public final BlockingQueue<ShortMessage> failQueue = new LinkedBlockingQueue<ShortMessage>();
	
	private static MessagePool instance = new MessagePool();
	
	private MessagePool(){}
	
	public static MessagePool getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * @param shortMessage
	 * @return
	 */
	public boolean setSMessage2MessageQueue(ShortMessage shortMessage){
		return messageQueue.offer(shortMessage);
	}
	
	/**
	 * 
	 * @param shortMessage
	 * @return
	 */
	public boolean setSMessage2MessageQueueHead(ShortMessage shortMessage){
		return messageQueue.offerFirst(shortMessage);
	}
	
	/**
	 * 
	 * @return
	 */
	public ShortMessage getSMessageFromMessageQueue(){
		return messageQueue.poll();
	}
	
	public boolean setSMessage2FailQueue(ShortMessage shortMessage){
		return failQueue.offer(shortMessage);
	}
	
	public ShortMessage getSMessageFromFailQueue(){
		return failQueue.poll();
	}
	
	public String getSMessageFromSuccessQueue(){
		return successQueue.poll();
	}

	public boolean setSMessage2SuccessQueue(String id){
		return successQueue.offer(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public LinkedBlockingDeque<ShortMessage> getMessageQueue() {
		return messageQueue;
	}

	/**
	 * 
	 * @return
	 */
	public BlockingQueue<String> getSuccessQueue() {
		return successQueue;
	}

	/**
	 * 
	 * @return
	 */
	public BlockingQueue<ShortMessage> geFaillQueue() {
		return failQueue;
	}
	
}
