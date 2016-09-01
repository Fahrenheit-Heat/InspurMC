package com.inspur.gcloud.mc.common.data;

/**
 * <p>短信数据对象</p>
 * 
 * @author songH
 * @version 1.0
 * @since 2016年5月7日 
 */
public class ShortMessage {
	
	/**
	 * 信封表主键ID
	 */
	private String envelopeId;
	
	/**
	 * 消息表主键ID
	 */
	private String messageId;
	
	/**
	 * 接收人手机号码
	 */
	private String phoneNumber;
	
	/**
	 * 发送人ID
	 */
	private String senderId;
	
	/**
	 * 发送人姓名（或手机号）
	 */
	private String senderName;
	
	/**
	 * 重发次数
	 */
	private int resendCount;
	
	/**
	 * 消息级别
	 */
	private String messageLevel;
	
	/**
	 * 短信内容
	 */
	private String messageContent;

	/**
	 * 获取信封表主键ID
	 * @return 信封表主键ID
	 */
	public String getEnvelopeId() {
		return envelopeId;
	}

	/**
	 * 设置信封表主键ID
	 * @param envelopeId 信封表主键ID
	 */
	public void setEnvelopeId(String envelopeId) {
		this.envelopeId = envelopeId;
	}

	/**
	 * 获取消息表主键ID
	 * @return messageId 消息表主键ID
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * 设置消息表主键ID
	 * @param messageId 消息表主键ID
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * 获取手机号码
	 * @return 手机号码
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 设置手机号码
	 * @param phoneNumber 手机号码
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 获取发送人ID
	 * @return 发送人ID
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * 设置发送人ID
	 * @param senderId 发送人ID
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * 获取发送人姓名
	 * @return 发送人姓名
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * 设置发送人姓名
	 * @param senderName 获取发送人姓名
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * 获取重发次数
	 * @return 重发次数
	 */
	public int getResendCount() {
		return resendCount;
	}

	/**
	 * 设置重发次数
	 * @param resendCount 设置重发次数
	 */
	public void setResendCount(int resendCount) {
		this.resendCount = resendCount;
	}

	/**
	 * 获取短信级别
	 * @return 短信级别
	 */
	public String getMessageLevel() {
		return messageLevel;
	}

	/**
	 * 设置短信级别
	 * @param messageLevel 短信级别（0~9）
	 */
	public void setMessageLevel(String messageLevel) {
		this.messageLevel = messageLevel;
	}

	/**
	 * 获取短信内容
	 * @return 短信内容
	 */
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * 设置短信内容
	 * @param messageContent 短信内容
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
}
