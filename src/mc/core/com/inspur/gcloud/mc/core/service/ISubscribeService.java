package com.inspur.gcloud.mc.core.service;

import java.util.List;
import java.util.Map;

import com.inspur.gcloud.mc.core.data.Subscribe;

/**
 * 订阅接口类
 * 
 * Service层 接口类，用于业务逻辑处理，事务控制等
 * 
 * @author 赵振华
 *
 */
public interface ISubscribeService {
	
	/**
	 * 查询当前用户的组织代码,订阅模块,提醒方式,是否开启订阅,备注
	 * @param map,key 分别为
	 * 				<code>organId</code>[组织代码]
	 * 				<code>subscribeModule</code>[订阅模块]
	 * 				<code>warnType</code>[提醒方式]
	 * 				<code>subscribeOpen</code>[是否开启订阅]
	 * 				<code>remark</code>[备注]
     *              <code>orderfield<code>[排序列 ]
     *              <code>orderdir<code>[排序方向desc或asc] 
     *              <code>start<code>[起始行]
     *              <code>limit<code>[每页显示条数]
	 * @return List, 内容为Subscribe对象列表
	 */
	public List<Subscribe> findAll(Map map);
	
	/**
     * 根据订阅模块、提醒方式、是否开启订阅查找信息
	 * @param map,key 分别为
	 * 				<code>organId</code>[组织代码]
	 * 				<code>subscribeModule</code>[订阅模块]
	 * 				<code>warnType</code>[提醒方式]
	 * 				<code>subscribeOpen</code>[是否开启订阅]
	 * 				<code>remark</code>[备注]
     *              <code>orderfield<code>[排序列 ]
     *              <code>orderdir<code>[排序方向desc或asc] 
     *              <code>start<code>[起始行]
     *              <code>limit<code>[每页显示条数]
	 * @return List, 内容为Subscribe对象列表
	 */
	public List<Subscribe> getByParams(Map<String,String> map);
	
	/**
	 * 根据订阅id查询订阅
	 * @param id [ID主键]
	 * @return Subscribe对象
	 */
	public Subscribe findOne(String id);
	
	/**
	 * 保存操作
	 * @param subscribe对象
	 * @return subscribe对象
	 */
	public Subscribe save(Subscribe subscribe);

    /**
     * 根据ID批量删除订阅
     * 
     * @param ids [ID主键数组]
     * 
     */
    public void delete(String[] ids);
}
