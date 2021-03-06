package com.inspur.gcloud.mc.core.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.inspur.gcloud.mc.core.dao.MessageDao;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.core.service.IMessageService;
import com.lc.gcloud.framework.util.GCloudUtil;

@Service("messageService")
public class MessageServiceImpl implements IMessageService {

	@Autowired
    private MessageDao messageDao;
	
	 @Resource(name = "transactionTemplate")
     private TransactionTemplate transactionTemplate;
	 
	/**
     * 保存新增和修改后的信封信息
     * 
     * @param message
     * 
     * @return message
     * 
     */
	 @Transactional
    public String insertMessage(Message message) {
        // 保存消息信息
    	String id = GCloudUtil.getInstance().getNextSeqId(32);
    	message.setId(id);
    	if(messageDao.insert(message) == 1){
    		 return id;
    	}else{
    		return null;
    	}
	 }
	 
	    /**
	     * 通过MessageId查找Message对象
	     * 
	     * @param id
	     * @return Message
	     */
	public Message findMessageById(String id) {
		return messageDao.get(id);
	}
}
