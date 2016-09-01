package com.inspur.gcloud.mc.common.data;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;

/**
 * <p>Title: 页面接受消息对象</p>
 * <p>Description: 用于接收页面视图提交表单以及外部系统接入使用消息对象</p>
 * @author h.song
 * @date 2016年6月22日 上午11:01:19
 * @vision 1.0
 */
public class MessageView {
	
	/**
	 * 信封表Id串
	 */
	private String envelopeId;
	
	/**
	 * 消息表Id串
	 */
	private String messageId;
	
	/**
	 * *消息主题--对外接口必填
	 * <p>站内消息：不为空</p>
	 * <p>短信：为空</p>
	 */
	private String messageTopic;
	
	/**
	 * *消息类型--对外接口必填 code：
	 * <code>m：站内消息</code>
	 * <code>w：站内邮件</code>
	 * <code>e：站外邮件</code>
	 * <code>s：短信</code>
	 */
	private String messageType;
	
	/**
	 * *发送人Id串--对外接口必填，当发送人为多个时，数据格式为：id1,id2,...,idn
	 */
	private String senderId;
	
	/**
	 * *发送人姓名串--对外接口必填，当发送人为多个时，数据格式为：name1,name2,...,namen
	 */
	private String senderName;
	
	/**
	 * *接收人Id串--对外接口必填，当接收人为多个时，数据格式为：id1,id2,...,idn
	 * <p>发送普通消息时，必填</p>
	 * <p>发送系统消息时，必为空</p>
	 */
	private String receiverId;
	
	/**
	 * *接收人姓名串--对外接口必填，当发送人为多个时，数据格式为：name1,name2,...,namen
	 * <p>发送普通消息时，必填</p>
	 * <p>发送系统消息时，必为"全体人员"</p>
	 */
	private String receiverName;
	
	/**
	 * 是否定时发送 code：
	 * <code>0：否</code>
	 * <code>1：是</code>
	 */
	private String isSchedule;
	
	/**
	 * 是否已读回执 code：
	 * <code>0：否</code>
	 * <code>1：是</code>
	 */
	private String isReadReceipt;
	
	/**
	 * 发送类型 code：
	 * <code>0：原件</code>
	 * <code>1：回复</code>
	 * <code>2：转发</code>
	 * <p>default：0</p>
	 */
	private String sendType;
	
	/**
	 * 发送状态 code：
	 * <code>0：未发送</code>
	 * <code>1：已发送</code>
	 * <code>2：删除</code>
	 * <code>3：彻底删除</code>
	 * <code>4：发送中</code>
	 * <code>5：发送失败</code>
	 * <p>站内消息 default：1</p>
	 * <p>短信 default：4</p>
	 */
	private String sendState;
	
	/**
	 * 接收人类型 code：
	 * <code>0：1对1</code>
	 * <code>1：组织机构代码</code>
	 * <code>3：系统消息</code>
	 * <p>default：1</p>
	 */
	private String receiverType;
	
	/**
	 * 接受状态 code：
	 * <code>0：未读</code>
	 * <code>1：已读</code>
	 * <code>2：删除</code>
	 * <code>3：彻底删除</code>
	 * <code>NULL：未接收</code>
	 * <p>default：1</p>
	 */
	private String receiveState;
	
	/**
	 * 消息级别
	 */
	private String messageLevel;
	
	/**
	 * *消息内容--对外接口必填
	 */
	private String messageContent;
	
	/**
	 * 转发或回复路径
	 */
	private String relatedPath;
	
	/**
	 * 转发或回复上一条消息ID
	 */
	private String relatedMessageId;

	/**
	 * 获取消息主题方法
	 * @return String messageTopic
	 */
	public String getMessageTopic() {
		return messageTopic;
	}

	/**
	 * 设置消息主题方法
	 * @param messageTopic 消息主题
	 */
	public void setMessageTopic(String messageTopic) {
		this.messageTopic = messageTopic;
	}

	/**
	 * 获取消息类型方法
	 * @return String messageType
	 * 					<code>m：站内消息</code>
	 * 					<code>w：站内邮件</code>
	 * 					<code>s：短信</code>
	 * 					<code>e：电子邮件</code>
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * 设置消息类型方法
	 * @param messageType 消息类型
	 * 					<code>m：站内消息</code>
	 * 					<code>w：站内邮件</code>
	 * 					<code>s：短信</code>
	 * 					<code>e：电子邮件</code>
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * 获取发送人ID方法
	 * @return String senderId
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * 设置发送人ID方法
	 * @param sendId 发送人ID
	 */
	public void setSenderId(String sendId) {
		this.senderId = sendId;
	}

	/**
	 * 获取接收人ID串方法，多个接收人ID以“,”分割
	 * @return String receiverId
	 */
	public String getReceiverId() {
		return receiverId;
	}

	/**
	 * 设置接收人ID串方法
	 * @param receiverId 接收人ID
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * 获取接收人ID列表方法
	 * @return List<String> receiverIdList
	 */
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
	
	/**
	 * 获取接收人姓名串方法，多个接收人ID以“,”分割
	 * @return String receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * 设置接收人姓名串方法
	 * @param receiverName 接收人姓名
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	/**
	 * 获取接收人姓名列表方法
	 * @return List<String> receiverNameList
	 */
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

	/**
	 * 获取是否定时发送方法
	 * @return String isSchedule
	 * 					<code>0：否</code>
	 * 					<code>1：是</code>
	 */
	public String getIsSchedule() {
		return isSchedule;
	}

	/**
	 * 设置是否定时发送方法
	 * @param isSchedule 是否定时发送标志
	 * 					<code>0：否</code>
	 * 					<code>1：是</code>
	 */
	public void setIsSchedule(String isSchedule) {
		this.isSchedule = isSchedule;
	}

	/**
	 * 获取是否需要回执方法
	 * @return String isReadReceipt
	 * 					<code>0：否</code>
	 * 					<code>1：是</code>
	 */
	public String getIsReadReceipt() {
		return isReadReceipt;
	}

	/**
	 * 设置是否需要回执方法
	 * @param isReadReceipt 是否需要回执标志
	 * 					<code>0：否</code>
	 * 					<code>1：是</code>
	 */
	public void setIsReadReceipt(String isReadReceipt) {
		this.isReadReceipt = isReadReceipt;
	}

	/**
	 * 获取发送类型方法
	 * @return String sendType
	 * 					<code>0：未发送</code>
	 * 					<code>1：已发送</code>
	 *  				<code>2：删除</code>
	 *  				<code>3：彻底删除</code>
	 *  				<code>4：发送中</code>
	 *  				<code>5：发送失败</code>
	 */
	public String getSendType() {
		return sendType;
	}

	/**
	 * 设置发送类型方法
	 * @param sendType 发送类型
	 * 					<code>0：未发送</code>
	 * 					<code>1：已发送</code>
	 *  				<code>2：删除</code>
	 *  				<code>3：彻底删除</code>
	 *  				<code>4：发送中</code>
	 *  				<code>5：发送失败</code>
	 */
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	/**
	 * 获取接收人类型方法
	 * @return String receiverType
	 * 					<code>0：普通人员</code>
	 * 					<code>1：BSP组织机构代码</code>
	 * 					<code>2：全体人员</code>
	 */
	public String getReceiverType() {
		return receiverType;
	}

	/**
	 * 设置接收人类型方法
	 * @param receiverType 接收人类型
	 * 					<code>0：普通人员</code>
	 * 					<code>1：BSP组织机构代码</code>
	 * 					<code>2：全体人员</code>
	 */
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	/**
	 * 获取接收状态方法
	 * @return String receiveState
	 * 					<code>0：未读</code>
	 * 					<code>1：已读</code>
	 * 					<code>2：删除</code>
	 * 					<code>3：彻底删除</code> 
	 * 					<code>NULL：未接收</code> 
	 */
	public String getReceiveState() {
		return receiveState;
	}

	/**
	 * 设置接收状态方法
	 * @param receiveState 接收状态
	 * 					<code>0：未读</code>
	 * 					<code>1：已读</code>
	 * 					<code>2：删除</code>
	 * 					<code>3：彻底删除</code> 
	 * 					<code>NULL：未接收</code> 
	 */
	public void setReceiveState(String receiveState) {
		this.receiveState = receiveState;
	}

	/**
	 * 获取消息级别方法
	 * @return String messageLevel，0~9
	 */
	public String getMessageLevel() {
		return messageLevel;
	}

	/**
	 * 设置消息级别方法
	 * @param messageLevel 消息级别 0~9
	 */
	public void setMessageLevel(String messageLevel) {
		this.messageLevel = messageLevel;
	}

	/**
	 * 获取消息内容方法
	 * @return String messageContent
	 */
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * 设置消息内容方法
	 * @param messageContent 消息内容
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	/**
	 * 获取发送人名方法
	 * @return String senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * 设置发送人名方法
	 * @param senderName 发送人名
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * 获取信封对象列表方法
	 * @return List<String> envelopeIdList
	 */
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
	
	/**
	 * 获取信封表主键ID方法
	 * @return String envelopeId
	 */
	public String getEnvelopeId(){
		return envelopeId;
	}

	/**
	 * 设置信封表主键ID方法
	 * @param envelopeId 信封表主键ID
	 */
	public void setEnvelopeId(String envelopeId) {
		this.envelopeId = envelopeId;
	}

	/**
	 * 获取消息表主键ID方法
	 * @return String messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * 设置消息表主键ID方法
	 * @param messageId 消息表主键ID
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * 获取发送状态方法
	 * @return String sendState
	 *	 				<code>0：未发送</code>
	 * 					<code>1：已发送</code>
	 * 					<code>2：删除</code>
	 * 					<code>3：彻底删除</code>
	 * 					<code>4：发送中</code>
	 * 					<code>5：发送失败</code> 
	 */
	public String getSendState() {
		return sendState;
	}

	/**
	 * 设置发送状态方法
	 * @param sendState 发送状态
	 * 					<code>0：未发送</code>
	 * 					<code>1：已发送</code>
	 * 					<code>2：删除</code>
	 * 					<code>3：彻底删除</code>
	 * 					<code>4：发送中</code>
	 * 					<code>5：发送失败</code>
	 */
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	/**
	 * 获取消息关联路径方法
	 * @return String relatedPath
	 */
	public String getRelatedPath() {
		return relatedPath;
	}

	/**
	 * 设置消息关联路径方法
	 * @param relatedPath 消息关联路径
 	 */
	public void setRelatedPath(String relatedPath) {
		this.relatedPath = relatedPath;
	}

	/**
	 * 获取转发或回复时消息的上条消息ID
	 * @return String relatedMessageId
	 */
	public String getRelatedMessageId() {
		return relatedMessageId;
	}

	/**
	 * 设置转发或回复时消息的上条消息ID
	 * @param relatedMessageId 转发或回复时消息的上条消息ID
	 */
	public void setRelatedMessageId(String relatedMessageId) {
		this.relatedMessageId = relatedMessageId;
	}
	
}
