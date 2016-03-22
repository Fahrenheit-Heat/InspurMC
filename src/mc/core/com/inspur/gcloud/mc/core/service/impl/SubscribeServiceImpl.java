package com.inspur.gcloud.mc.core.service.impl;

import java.util.List;
import java.util.Map;

import org.loushang.framework.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.core.dao.SubscribeDao;
import com.inspur.gcloud.mc.core.data.Subscribe;
import com.inspur.gcloud.mc.core.service.ISubscribeService;
/**
 * 订阅服务实现类
 * 
 * @author Howard
 *
 */
@Service
public class SubscribeServiceImpl implements ISubscribeService {
	
	@Autowired
	private SubscribeDao subscribeDao;
	
	/**
	 * 查询消息订阅
	 * 
	 */
	public List<Subscribe> findAll(Map map) {
		return subscribeDao.findAll(map);
	}

	/**
	 * 
	 */
	public List<Subscribe> getByParams(Map<String, String> parameters) {
		return subscribeDao.getByParams(parameters);
	}

	
	public Subscribe save(Subscribe subscribe) {
		subscribe.setId(UUIDGenerator.getUUID());
		subscribeDao.insert(subscribe);
		return subscribe;
	}

}
