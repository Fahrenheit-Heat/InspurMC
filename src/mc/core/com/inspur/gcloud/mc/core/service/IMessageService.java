
package com.inspur.gcloud.mc.core.service;

import com.inspur.gcloud.mc.core.data.Message;

/**
 * 消息接口类
 * 
 * Service层 接口类，用于业务逻辑处理，事务控制等
 * 
 * @author ZXh
 *
 */
public interface IMessageService {

	/**
     * 保存新增和修改后的信封信息
     * 
     * @param envelope
     * 
     * @return Envelope
     * 
     */
    public String saveMessage(Message message);
    
    public String updateMessage(Message message);

    /**
     * 
     * @param id
     * @return Message
     */
	public Message findOne(String id);
	
}
