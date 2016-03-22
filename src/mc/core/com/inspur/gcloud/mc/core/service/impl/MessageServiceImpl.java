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
    public Message saveMessage(Message message) {
    	
        if (message.getId() != null && !message.getId().equals("")) {
            // 更新消息信息
        	messageDao.update(message);
        } else {
            // 保存消息信息
        	message.setId(GCloudUtil.getInstance().getNextSeqId(32));
        	//String id = message.getId();
        	messageDao.insert(message);
        }
        
        return message;
    }

}
