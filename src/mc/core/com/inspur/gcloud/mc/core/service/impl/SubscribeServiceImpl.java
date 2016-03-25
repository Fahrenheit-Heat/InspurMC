package com.inspur.gcloud.mc.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.loushang.framework.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.inspur.gcloud.mc.core.dao.SubscribeDao;
import com.inspur.gcloud.mc.core.data.Subscribe;
import com.inspur.gcloud.mc.core.service.ISubscribeService;
/**
 * 订阅服务实现类
 * 
 * Service层 实现类，用于业务逻辑处理，事务控制等
 * 
 * @author 赵振华
 *
 */
@Service
public class SubscribeServiceImpl implements ISubscribeService {
	
	@Autowired
	private SubscribeDao subscribeDao;
	/**
	 * 模板事务
	 */
	@Resource(name = "transactionTemplate")
	private TransactionTemplate transactionTemplate;
	
	/**
	 * 查询消息订阅
	 * 
	 */
	public List<Subscribe> findAll(Map map) {
		return subscribeDao.findAll(map);
	}

	/**
     * 根据 查找信息
     * @param map key分别为： 
     *             
     * @return List， 内容为 Subscribe对象列表
	 */
	public List<Subscribe> getByParams(Map<String, String> parameters) {
		return subscribeDao.getByParams(parameters);
	}

	/**
	 * 保存操作
	 */
	@Transactional
	public Subscribe save(Subscribe subscribe) {
		if(subscribe.getId() != null && !subscribe.getId().equals("")){
			subscribeDao.update(subscribe);
		}else{
			subscribe.setId(UUIDGenerator.getUUID());
			subscribeDao.insert(subscribe);
		}
		return subscribe;
	}

	/**
	 * 根据id获取订阅信息
	 */
	public Subscribe findOne(String id) {
		return subscribeDao.get(id);
	}
	
	/**
	 * 批量删除
	 */
	@Override
	public void delete(final String[] ids) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				subscribeDao.batchDelete(ids);
				
			}
		});
	}

}
