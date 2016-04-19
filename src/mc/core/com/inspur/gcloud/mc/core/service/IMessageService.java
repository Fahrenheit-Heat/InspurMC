
package com.inspur.gcloud.mc.core.service;

import com.inspur.gcloud.mc.core.data.Message;

/**
 * 消息接口类
 * 
 * Service层 接口类，用于业务逻辑处理，事务控制等
 * 
 * @author ZhaoZhenHua
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
    public String insertMessage(Message message);

    /**
     * 通过MessageId查找Message对象
     * 
     * @param id
     * @return Message
     */
	public Message findMessageById(String id);
	
}
