package com.inspur.gcloud.mc.engine.thread.service.engine;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inspur.gcloud.mc.common.util.McLoggerUtil;
import com.inspur.gcloud.mc.engine.handle.service.IHandleMessageQueueService;
import com.inspur.gcloud.sc.api.GCloudSCUtil;

/**
 * <p>Title: 短信失败队列处理线程</p>
 * <p>Description: 用于短信失败队列处理失败短信</p>
 * @author h.song
 * @date 2016年6月20日 下午10:12:55
 * @vision 1.0
 */
@Component  
@Scope("prototype")  
public class ShortMessageHandleErrorQueueEngine extends Thread {
	
	// 通过参数中心获取系统参数，失败消息队列处理线程休眠时间，默认1秒
	private static final int MESSAGE_ERROR_HANDLE_SLEEP_TIME = GCloudSCUtil.getInstance()
			.getParameter("Mc.errorHandleSleepTime") != null
					? Integer.parseInt(GCloudSCUtil.getInstance().getParameter("Mc.errorHandleSleepTime")) : 1;
	
	private static Log log = LogFactory.getLog(ShortMessageHandleErrorQueueEngine.class);
	@Autowired
	private IHandleMessageQueueService handleMessageQueue;
	
	private McLoggerUtil mcLoggerUtil = McLoggerUtil.getInstance();
	
	@Override
	public void run() {
		if(log.isDebugEnabled()){
			log.debug("++++++++++++++++++++++++短信队列处理失败短信服务已启动++++++++++++++++++++++++");
		}
		
		mcLoggerUtil.systemLog("", "1", new Date() + "短信队列处理失败短信服务已启动");
		
		while(true){
			try{
				handleMessageQueue.handleErrorQueue();
				Thread.sleep(MESSAGE_ERROR_HANDLE_SLEEP_TIME * 10000);
			} catch(Exception e) {
				log.error("处理短信失败队列出错："+e);
				mcLoggerUtil.systemLog("", "1", "处理短信失败队列出错："+e);
			}
		}
	}

}
