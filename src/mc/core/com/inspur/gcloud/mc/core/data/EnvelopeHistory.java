package com.inspur.gcloud.mc.core.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <p>历史信封类</P>
 *
 * @author ZXh
 */
@Table(name = "MC_ENVELOPE_HISTORY")
public class EnvelopeHistory {

	// 主键
    @Id
    private String id;
    
    // 消息内容ID
    @Column(name = "MESSAGE_ID")
    private String messageId;
    
    // 消息主题
    @Column(name = "MESSAGE_TOPIC")
    private String messageTopic;
    
    // 消息类型：M：站内消息；W：站内邮件；S：短信；E：电子邮件
    @Column(name = "MESSAGE_TYPE")
    private String messageType;
    
    // 转发或回复时消息的原消息ID，原始消息的关联消息ID为本身ID
    @Column(name = "RELATED_MESSAGE_ID")
    private String relatedMessageId;
    
    // 关联路径,转发或回复该消息的所有消息ID
    @Column(name = "RELATED_PATH")
    private String relatedPath;
    
    // 发件人ID
    @Column(name = "SENDER_ID")
    private String senderId;
    
    // 接收人ID
    @Column(name = "RECEIVER_ID")
    private String receiverId;
    
    // 收件状态：0：未读；1：已读；2：删除;3：彻底删除；NULL：未接收；
    @Column(name = "RECEIVE_STATE")
    private String receiveState;
    
    // 发送状态：0：未发送；1：已发送；2：删除；3：彻底删除
    @Column(name = "SEND_STATE")
    private String sendState;
    
    // 是否定时发送：0：否；1：是；
    @Column(name = "IS_SCHEDULE")
    private String isSchedule;
    
    // 是否需要回执：0：否；1：是；
    @Column(name = "IS_READ_RECEIPT")
    private String isReadReceipt;
    
    // 是否已回执：0：否；1：是；
    @Column(name = "HAS_RECEIPT")
    private String hasReceipt;
    
    // 发送类型：0：原邮件；1：回复；2：转发
    @Column(name = "SEND_TYPE")
    private String sendType;
    
    // 收件类型：一对一；ALL：系统消息；
    @Column(name = "RECEIVER_TYPE")
    private String receiverType;
    
    // 发送级别：0：主送；1：抄送；
    @Column(name = "SEND_LEVEL")
    private String sendLevel;
    
    // 发送时间
    @Column(name = "SEND_TIME")
    private Date sendTime;
    
    // 阅读时间
    @Column(name = "READ_TIME")
    private Date readTime;
    
    // 创建时间
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    // 修改时间
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    
    // 备注
    @Column(name = "REMARK")
    private String remark;

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

	/**
	 * 属性messageId的get方法
	 * @return messageId String
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * 属性messageId的set方法
	 * @param messageId String
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
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
	 * 属性messageType的get方法
	 * @return messageType String
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * 属性messageType的set方法
	 * @param messageType String
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * 属性relatedMessageId的get方法
	 * @return relatedMessageId String
	 */
	public String getRelatedMessageId() {
		return relatedMessageId;
	}

	/**
	 * 属性relatedMessageId的set方法
	 * @param relatedMessageId String
	 */
	public void setRelatedMessageId(String relatedMessageId) {
		this.relatedMessageId = relatedMessageId;
	}

	/**
	 * 属性relatedPath的get方法
	 * @return relatedPath String
	 */
	public String getRelatedPath() {
		return relatedPath;
	}

	/**
	 * 属性relatedPath的set方法
	 * @param relatedPath String
	 */
	public void setRelatedPath(String relatedPath) {
		this.relatedPath = relatedPath;
	}

	/**
	 * 属性senderId的get方法
	 * @return senderId String
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * 属性senderId的set方法
	 * @param senderId String
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * 属性receiveState的get方法
	 * @return receiveState String
	 */
	public String getReceiveState() {
		return receiveState;
	}

	/**
	 * 属性receiveState的set方法
	 * @param receiveState String
	 */
	public void setReceiveState(String receiveState) {
		this.receiveState = receiveState;
	}

	/**
	 * 属性sendState的get方法
	 * @return sendState String
	 */
	public String getSendState() {
		return sendState;
	}

	/**
	 * 属性sendState的set方法
	 * @param sendState String
	 */
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	/**
	 * 属性isSchedule的get方法
	 * @return isSchedule String
	 */
	public String getIsSchedule() {
		return isSchedule;
	}

	/**
	 * 属性isSchedule的set方法
	 * @param isSchedule String
	 */
	public void setIsSchedule(String isSchedule) {
		this.isSchedule = isSchedule;
	}

	/**
	 * 属性isReadReceipt的get方法
	 * @return isReadReceipt String
	 */
	public String getIsReadReceipt() {
		return isReadReceipt;
	}

	/**
	 * 属性isReadReceipt的set方法
	 * @param isReadReceipt String
	 */
	public void setIsReadReceipt(String isReadReceipt) {
		this.isReadReceipt = isReadReceipt;
	}

	/**
	 * 属性hasReceipt的get方法
	 * @return hasReceipt String
	 */
	public String getHasReceipt() {
		return hasReceipt;
	}

	/**
	 * 属性hasReceipt的set方法
	 * @param hasReceipt String
	 */
	public void setHasReceipt(String hasReceipt) {
		this.hasReceipt = hasReceipt;
	}

	/**
	 * 属性sendType的get方法
	 * @return sendType String
	 */
	public String getSendType() {
		return sendType;
	}

	/**
	 * 属性sendType的set方法
	 * @param sendType String
	 */
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	/**
	 * 属性receiverType的get方法
	 * @return receiverType String
	 */
	public String getReceiverType() {
		return receiverType;
	}

	/**
	 * 属性receiverType的set方法
	 * @param receiverType String
	 */
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	/**
	 * 属性sendLevel的get方法
	 * @return sendLevel String
	 */
	public String getSendLevel() {
		return sendLevel;
	}

	/**
	 * 属性sendLevel的set方法
	 * @param sendLevel String
	 */
	public void setSendLevel(String sendLevel) {
		this.sendLevel = sendLevel;
	}

	/**
	 * 属性sendTime的get方法
	 * @return sendTime Date
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 属性sendTime的set方法
	 * @param sendTime Date
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 属性readTime的get方法
	 * @return readTime Date
	 */
	public Date getReadTime() {
		return readTime;
	}

	/**
	 * 属性readTime的set方法
	 * @param readTime String
	 */
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	/**
	 * 属性createTime的get方法
	 * @return createTime Date
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 属性createTime的set方法
	 * @param createTime String
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性updateTime的get方法
	 * @return updateTime Date
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 属性updateTime的set方法
	 * @param updateTime String
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性remark的get方法
	 * @return remark String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 属性remark的set方法
	 * @param remark String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
