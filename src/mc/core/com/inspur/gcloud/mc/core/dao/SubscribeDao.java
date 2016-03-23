package com.inspur.gcloud.mc.core.dao;

import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.mapper.EntityMapper;

import com.inspur.gcloud.mc.core.data.Subscribe;
/**
 * Dao层 接口类，用于持久化数据处理
 * 
 * 订阅接口类
 * 
 * @author Howard
 *
 */
public interface SubscribeDao extends EntityMapper<Subscribe>{
	
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
	List<Subscribe> findAll(Map map);
	
	/**
	 * 根据订阅模块、提醒方式、是否开启订阅查找信息
	 * @param map
	 * @return
	 */
	public List<Subscribe> getByParams(Map map);
}
