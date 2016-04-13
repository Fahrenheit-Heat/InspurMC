package com.inspur.gcloud.mc.common.data;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;

/**
 * <p>页面接受消息对象</p>
 * <p>用途：用于接收页面视图提交表单</p>
 *
 * @author Songh
 * @version 1.0
 * @since 2016年3月23日
 */
public class MessageView {
	
	//信封Id串
	private String envelopeId;
	
	//消息Id
	private String messageId;
	
	// 消息主题
	private String messageTopic;
	
	// 消息类型
	private String messageType;
	
	// 发送人Id
	private String senderId;
	
	//发送人姓名串
	private String senderName;
	
	// 接收人Id串
	private String receiverId;
	
	// 接收人姓名串
	private String receiverName;
	
	// 是否定时发送
	private String isSchedule;
	
	// 是否已读回执
	private String isReadReceipt;
	
	// 发送类型
	private String sendType;
	
	// 发送状态
	private String sendState;
	
	// 接收人类型
	private String receiverType;
	
	// 接受状态
	private String receiveState;
	
	// 消息级别
	private String messageLevel;
	
	// 消息内容
	private String messageContent;

	public String getMessageTopic() {
		return messageTopic;
	}

	public void setMessageTopic(String messageTopic) {
		this.messageTopic = messageTopic;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String sendId) {
		this.senderId = sendId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	public List<String> getReceiverIdList() {
		List<String> receiverIdList = new ArrayList<String>();
		if(!StringUtils.isNullOrEmpty(receiverId) && receiverId.indexOf(",") > -1){
			String[] rId = receiverId.split(",");
			for(int i = 0; i < rId.length; i++){
				receiverIdList.add(rId[i]);
			}
		}else{
			receiverIdList.add(receiverId);
		}
		return receiverIdList;
	}
	
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public List<String> getReceiverNameList() {
		List<String> receiverNameList = new ArrayList<String>();
		if(!StringUtils.isNullOrEmpty(receiverName) && receiverName.indexOf(",") > -1){
			String[] rName = receiverName.split(",");
			for(int i = 0; i < rName.length; i++){
				receiverNameList.add(rName[i]);
			}
		}else{
			receiverNameList.add(receiverName);
		}
		return receiverNameList;
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

	public String getReceiveState() {
		return receiveState;
	}

	public void setReceiveState(String receiveState) {
		this.receiveState = receiveState;
	}

	public String getMessageLevel() {
		return messageLevel;
	}

	public void setMessageLevel(String messageLevel) {
		this.messageLevel = messageLevel;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public List<String> getEnvelopeIdList() {
		
		List<String> envelopeIdList = new ArrayList<String>();
		if(!StringUtils.isNullOrEmpty(envelopeId) && envelopeId.indexOf(",") > -1) {
			String[] eId = envelopeId.split(",");
			for(int i = 0; i < eId.length; i++) {
				envelopeIdList.add(eId[i]);
			}
		} else {
			envelopeIdList.add(envelopeId);
		}
		return envelopeIdList;
	}
	
	public String getEnvelopeId(){
		return envelopeId;
	}

	public void setEnvelopeId(String envelopeId) {
		this.envelopeId = envelopeId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	
}
