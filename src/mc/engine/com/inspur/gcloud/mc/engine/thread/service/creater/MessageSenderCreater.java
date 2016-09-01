package com.inspur.gcloud.mc.engine.thread.service.creater;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.util.McLoggerUtil;
import com.inspur.gcloud.mc.common.util.SpringContextUtil;

/**
 * <p>Title: 消息发送器构造类</p>
 * <p>Description: 用于动态构建消息发送器</p>
 * @author h.song
 * @date 2016年6月30日 上午11:08:36
 * @vision 1.0
 */
@Service("messageSenderCreater")
public class MessageSenderCreater {
	
	private static Log log = LogFactory.getLog(MessageSenderCreater.class);
	
	@Autowired
	private SpringContextUtil springContextUtil;
	
	/**
	 * 反射创建消息发送器实现类
	 * @return
	 */
	@SuppressWarnings("static-access")
	public Object createSMessageSender(String classPath){
		
		// 定义发送器类
		Class<?> senderClass = null;
		
		try {
			// 反射
			senderClass = Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			log.error("加载实现类错误：" + e.toString());
			McLoggerUtil.getInstance().systemLog("", "1", "加载实现类错误：" + e);
		}
		
		return springContextUtil.getBeanByClass(senderClass);
	}

}
