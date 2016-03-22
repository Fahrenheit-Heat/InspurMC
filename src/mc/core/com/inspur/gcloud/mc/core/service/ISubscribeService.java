package com.inspur.gcloud.mc.core.service;

import java.util.List;
import java.util.Map;

import com.inspur.gcloud.mc.core.data.Subscribe;

/**
 * 订阅接口类
 * 
 * Service层 接口类，用于业务逻辑处理，事务控制等
 * 
 * @author Howard
 *
 */
public interface ISubscribeService {
	
	/**
	 * 查询消息订阅
	 * @param map
	 * @return
	 */
	public List<Subscribe> findAll(Map map);
	
	/**
     * 根据 查找信息
     * @param map key分别为： 
     *             
     * @return List， 内容为 Subscribe对象列表
	 */
	public List<Subscribe> getByParams(Map<String,String> map);
	
	
	public Subscribe save(Subscribe subscribe);
	
	
}
