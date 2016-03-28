package com.inspur.gcloud.mc.core.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * <p>信息类</P>
 *
 * @author ZXh
 */
@Table(name = "MC_MESSAGE")
public class Message implements Serializable{
	// 主键
	@Id
    private String id;
    
    // 消息级别：0为最低级别，9为最高级别
    @Column(name = "MESSAGE_LEVEL")
    private String messageLevel;
    
    // 消息主题
    @Column(name = "MESSAGE_TOPIC")
    private String messageTopic;
	
    // 消息内容
    @Column(name = "MESSAGE_CONTENT")
    private String messageContent;
    
    // 备注
    @Column(name = "REMARK")
    private String Remark;
    
    @Transient
    private Attach attach;
    

	/**
	 * 属性id的get方法
	 * @return id String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 属性id的set方法
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getMessageLevel() {
		return messageLevel;
	}

	public void setMessageLevel(String messageLevel) {
		this.messageLevel = messageLevel;
	}

	/**
	 * 属性messageTopic的get方法
	 * @return messageTopic String
	 */
	public String getMessageTopic() {
		return messageTopic;
	}

	/**
	 * 属性messageTopic的set方法
	 * @param messageTopic String
	 */
	public void setMessageTopic(String messageTopic) {
		this.messageTopic = messageTopic;
	}

	/**
	 * 属性messageContent的get方法
	 * @return messageContent String
	 */
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * 属性messageContent的set方法
	 * @param messageContent String
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	/**
	 * 属性attach的get方法
	 * @return attach Attach
	 */
	public Attach getAttach() {
		return attach;
	}

	/**
	 * 属性attach的set方法
	 * @param attach Attach
	 */
	public void setAttach(Attach attach) {
		this.attach = attach;
	}
    
}
