package com.inspur.gcloud.mc.engine.thread.service.engine;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inspur.gcloud.mc.common.util.McLoggerUtil;
import com.inspur.gcloud.mc.engine.handle.service.IHandleMessageQueueService;

/**
 * <p>Title: 处理成功消息队列实现类</p>
 * <p>Description: 用于处理成功消息队列消息</p>
 * @author h.song
 * @date 2016年6月30日 上午11:37:59
 * @vision 1.0
 */
@Component  
@Scope("prototype")  
public class ShortMessageHandleSuccessQueueEngine extends Thread {
	
	private static Log log = LogFactory.getLog(ShortMessageHandleSuccessQueueEngine.class);
	
	@Autowired
	private IHandleMessageQueueService handleMessageQueue;
	
	private McLoggerUtil mcLoggerUtil = McLoggerUtil.getInstance();
	
	@Override
	public void run() {
		if(log.isDebugEnabled()){
			log.debug("++++++++++++++++++++++++短信队列处理成功短信服务已启动++++++++++++++++++++++++");
		}
		
		mcLoggerUtil.systemLog("", "1", new Date() + "短信队列处理成功短信服务已启动");
		
		while(true){
			try{
				handleMessageQueue.handleSuccessQueue();
			} catch(Exception e) {
				log.error("处理短信失败队列出错：" + e);
				mcLoggerUtil.systemLog("", "1", "处理短信失败队列出错：" + e);
			}
		}
	}

}
