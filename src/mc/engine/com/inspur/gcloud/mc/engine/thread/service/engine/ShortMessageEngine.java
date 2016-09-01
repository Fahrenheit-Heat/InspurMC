package com.inspur.gcloud.mc.engine.thread.service.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inspur.gcloud.ext.sender.IShortMessageSender;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.common.data.ShortMessage;
import com.inspur.gcloud.mc.common.messagepool.MessagePool;
import com.inspur.gcloud.mc.common.util.McLoggerUtil;
import com.inspur.gcloud.mc.engine.handle.service.IHandleMessageQueueService;
import com.inspur.gcloud.mc.engine.thread.service.creater.MessageSenderCreater;
import com.inspur.gcloud.sc.api.GCloudSCUtil;

/**
 * <p>Title: 短信服务类</p>
 * <p>Description: 用于短信服务具体实现：从短信队列获取发送消息、初始化消息发送器、发送成功和失败消息处理</p>
 * @author h.song
 * @date 2016年6月17日 上午9:53:38
 * @vision 1.0
 */
@Component
@Scope("prototype")
public class ShortMessageEngine extends Thread {

	// 通过参数中心获取系统参数，消息分组处理数量，若无法获取，则默认为100
	private static final int MESSAGE_GROUP_COUNT = GCloudSCUtil.getInstance()
			.getParameter("Mc.messageGroupCount") != null
					? Integer.parseInt(GCloudSCUtil.getInstance().getParameter("Mc.messageGroupCount")) : 100;

	private static Log log = LogFactory.getLog(ShortMessageEngine.class);

	@Autowired
	private IHandleMessageQueueService handleMessageQueue;
	@Autowired
	private MessageSenderCreater messageSenderCreater;

	// 创建消息池对象
	private MessagePool messagePool = MessagePool.getInstance();

	private McLoggerUtil mcLoggerUtil = McLoggerUtil.getInstance();

	/**
	 * 线程启动方法
	 */
	@Override
	public void run() {
		if (log.isDebugEnabled()) {
			log.debug("++++++++++++++++++++++++短信服务已启动++++++++++++++++++++++++");
		}

		mcLoggerUtil.systemLog("", "1", new Date() + "短信服务已启动");

		// 获取配置的发送器实现类路径
		String classPath = GCloudSCUtil.getInstance().getParameter("Mc.messageSenderImplPath");

		// 通过类路径获取短信发送器
		IShortMessageSender shortMessageSender = (IShortMessageSender) messageSenderCreater
				.createSMessageSender(classPath);

		while (true) {
			
			try {
				// 获取缓存中的短信队列
				LinkedBlockingDeque<ShortMessage> messageQueue = messagePool.getMessageQueue();
				// 判断队列是否存在短信
				if (!messageQueue.isEmpty()) {
					if (log.isDebugEnabled()) {
						log.debug("当前短信队列中存在" + messageQueue.size() + "条短信。");
					}

					mcLoggerUtil.systemLog("", "1", "当前短信队列中存在" + messageQueue.size() + "条短信。");

					// 定义存放消息队列获取的短信对象列表
					List<ShortMessage> messageList = new ArrayList<ShortMessage>();
					while (messageList.size() < MESSAGE_GROUP_COUNT) {
						// 从消息队列中获取短信
						ShortMessage shortMessage = messagePool.getSMessageFromMessageQueue();
						if (shortMessage == null) {
							break;
						}
						// 加入短信列表
						messageList.add(shortMessage);
					}
					if (log.isDebugEnabled()) {
						log.debug("已从当前短信队列种取出" + messageList.size() + "条短信，开始推送至短信平台。");
					}

					mcLoggerUtil.systemLog("", "1", "已从当前短信队列种取出" + messageList.size() + "条短信，开始推送至短信平台。");

					// 发送短信至短信平台
					ResultMap resultMap = shortMessageSender.sendMessageImpl(messageList);
					// 处理返回结果
					List<String> successList = resultMap.getSuccessList();
					List<String> errorList = resultMap.getErrorList();
					if (successList.size() > 0) {
						// 处理成功消息放入成功队列
						handleMessageQueue.addSuccessQueue(successList);
					}
					if (errorList.size() > 0) {
						// 处理失败消息放入失败队列
						handleMessageQueue.addErrorQueue(errorList);
					}

				}
			} catch (Exception e) {
				log.error("短信服务运行时错误：" + e);
				mcLoggerUtil.systemLog("", "1", "短信服务运行时错误：" + e);
			}
		}
	}

}
