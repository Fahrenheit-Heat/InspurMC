/**
 * 
 */
package com.inspur.gcloud.mc.core.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>回执类</p>
 * 
 * @author ZXh
 *
 */
@Table(name = "MC_RECEIPT")
public class Receipt {
	
	// 主键
	@Id
	private String id;
	
	// 发件人ID
	@Column(name = "SENDER_ID")
	private String sendId;
	
	// 收件人ID
	@Column(name = "RECEIVER_ID")
	private String receiverId;
	
	// 消息ID
	@Column(name = "MESSAGE_ID")
	private String messageId;
	
	// 回执信息
	@Column(name = "RECEIPT_INFORMATION")
	private String receiptInformation;

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
	 * 属性sendId的get方法
	 * @return sendId String
	 */
	public String getSendId() {
		return sendId;
	}

	/**
	 * 属性sendId的set方法
	 * @param sendId String
	 */
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	/**
	 * 属性receiverId的get方法
	 * @return receiverId String
	 */
	public String getReceiverId() {
		return receiverId;
	}

	/**
	 * 属性receiverId的set方法
	 * @param receiverId String
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
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
	 * 属性receiptInformation的get方法
	 * @return receiptInformation String
	 */
	public String getReceiptInformation() {
		return receiptInformation;
	}

	/**
	 * 属性receiptInformation的set方法
	 * @param receiptInformation String
	 */
	public void setReceiptInformation(String receiptInformation) {
		this.receiptInformation = receiptInformation;
	}
	
	
}
