package com.inspur.gcloud.mc.engine.thread.service;

import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.inspur.gcloud.mc.common.util.McLoggerUtil;
import com.inspur.gcloud.mc.engine.thread.service.engine.ShortMessageEngine;
import com.inspur.gcloud.mc.engine.thread.service.engine.ShortMessageHandleErrorQueueEngine;
import com.inspur.gcloud.mc.engine.thread.service.engine.ShortMessageHandleSuccessQueueEngine;

/**
 * <p>Title: 服务引擎</p>
 * <p>Description: 用于spring容器启动时，启动消息服务平台参数初始化及各项服务启动功能</p>
 * @author h.song
 * @date 2016年6月17日 上午9:41:07
 * @vision 1.0
 */
@Service("enginService")
public class ServiceEngine implements InitializingBean,ServletContextAware,DisposableBean,ApplicationContextAware {
	
	private static Log log = LogFactory.getLog(ServiceEngine.class);
	
	// 定义短信服务线程对象
	private ShortMessageEngine shortMessageThread;
	// 定义短信服务处理成功队列
	private ShortMessageHandleSuccessQueueEngine shortMessageHandleSuccessQueueThread;
	// 定义短信服务处理失败队列
	private ShortMessageHandleErrorQueueEngine shortMessageHandleErrorQueueThread;
	// 定义spring容器对象
	private ApplicationContext applicationContext;
	
	private McLoggerUtil mcLoggerUtil = McLoggerUtil.getInstance();
	
	/**
	 * spring容器销毁方法
	 */
	@Override
	public void destroy() throws Exception {
		
		log.debug("========================短信服务正在关闭========================");
		mcLoggerUtil.systemLog("", "1", new Date() + "短信服务正在关闭");
		
		shortMessageThread.yield();
		shortMessageHandleSuccessQueueThread.yield();
		shortMessageHandleErrorQueueThread.yield();
		
	}

	/**
	 * spring容器启动初始化方法
	 */
	@Override
	public void setServletContext(ServletContext arg0) {
		
		/**
		 * 短信队列处理服务启动
		 */
		if(log.isDebugEnabled()){
			log.debug("========================短信队列处理服务正在启动========================");
		}
		
		mcLoggerUtil.systemLog("", "1", new Date() + "短信队列处理服务正在启动");
		
		shortMessageHandleSuccessQueueThread = (ShortMessageHandleSuccessQueueEngine) applicationContext.getBean("shortMessageHandleSuccessQueueEngine");
		shortMessageHandleSuccessQueueThread.start();
		shortMessageHandleErrorQueueThread = (ShortMessageHandleErrorQueueEngine) applicationContext.getBean("shortMessageHandleErrorQueueEngine");
		shortMessageHandleErrorQueueThread.start();
		if(log.isDebugEnabled()){
			log.debug("========================短信队列处理服务启动完毕========================");
		}
		
		mcLoggerUtil.systemLog("", "1", new Date() + "短信队列处理服务启动完毕");
		
		/**
		 * 短信服务启动
		 */
		if(log.isDebugEnabled()){
			log.debug("========================短信服务正在启动========================");
		}
		mcLoggerUtil.systemLog("", "1", new Date() + "短信服务正在启动");
		
		shortMessageThread = (ShortMessageEngine) applicationContext.getBean("shortMessageEngine");
		shortMessageThread.setName("shortMessageThread");
		shortMessageThread.start();
		
	}
	
	/**
	 * 初始化spring容器方法
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

}
