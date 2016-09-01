package com.inspur.gcloud.mc.core.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * <p>信封类</P>
 *
 * @author ZJy
 */
@Table(name = "MC_ENVELOPE")
public class Envelope implements Serializable{
	
	// 主键
    @Id
    private String id;
    
    // 消息内容ID
    @Column(name = "MESSAGE_ID")
    private String messageId;
    
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
    
    // 发送状态：0：未发送；1：已发送；2：删除；3：彻底删除；4：发送中；5：发送失败；
    @Column(name = "SEND_STATE")
    private String sendState;
    
    // 重发次数
    @Column(name = "RESEND_COUNT")
    private int resendCount;
    
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
    
    //发送人名称
    @Column(name = "SENDER_NAME")
    private String senderName;
    
    //接受人名称
    @Column(name = "RECEIVER_NAME")
    private String receiverName;
    
    public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Transient
    private Message message;
    
    @Transient
    private Attach attach;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getRelatedMessageId() {
		return relatedMessageId;
	}

	public void setRelatedMessageId(String relatedMessageId) {
		this.relatedMessageId = relatedMessageId;
	}

	public String getRelatedPath() {
		return relatedPath;
	}

	public void setRelatedPath(String relatedPath) {
		this.relatedPath = relatedPath;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiveState() {
		return receiveState;
	}

	public void setReceiveState(String receiveState) {
		this.receiveState = receiveState;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	
	public int getResendCount() {
		return resendCount;
	}

	public void setResendCount(int resendCount) {
		this.resendCount = resendCount;
	}

	public String getIsSchedule() {
		return isSchedule;
	}

	public void setIsSchedule(String isSchedule) {
		this.isSchedule = isSchedule;
	}

	public String getIsReadReceipt() {
		return isReadReceipt;
	}

	public void setIsReadReceipt(String isReadReceipt) {
		this.isReadReceipt = isReadReceipt;
	}

	public String getHasReceipt() {
		return hasReceipt;
	}

	public void setHasReceipt(String hasReceipt) {
		this.hasReceipt = hasReceipt;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getSendLevel() {
		return sendLevel;
	}

	public void setSendLevel(String sendLevel) {
		this.sendLevel = sendLevel;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 属性message的get方法
	 * @return message Message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * 属性message的set方法
	 * @param message Message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
	
	/**
	 * 属性attach的get方法
	 * @return attach Attach
	 */
	public Attach getAttach() {
		return attach;
	}

	/**
	 * 属性message的set方法
	 * @param message Message
	 */
	public void setAttach(Attach attach) {
		this.attach = attach;
	}
    
}
