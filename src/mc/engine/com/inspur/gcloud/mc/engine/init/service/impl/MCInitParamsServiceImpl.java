package com.inspur.gcloud.mc.engine.init.service.impl;

import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.common.paramspool.ParamsPool;
import com.inspur.gcloud.mc.engine.init.service.IMCInitParamsService;

/**
 * <p>Title: 消息服务参平台数初始化服务</p>
 * <p>Description: 用于初始化平台参数集合</p>
 * @author h.song
 * @date 2016年6月17日 上午10:18:40
 * @vision 1.0
 */
@Service("mcInitParamsService")
public class MCInitParamsServiceImpl implements IMCInitParamsService {
	
	// 定义参数池
	private ParamsPool paramsPool = ParamsPool.getInstance();

	/**
	 * 从数据库获取参数并放入参数池中初始化
	 */
	@Override
	public void initParams() {
		
		paramsPool.setValue("MESSAGE_SENDER_IMPL_PATH", "com.inspur.gcloud.ext.sender.impl.ShortMessageSenderImpl");
		
	}

}
