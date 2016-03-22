package com.inspur.gcloud.mc.common.data;

import java.util.List;

import javax.persistence.Transient;

import com.inspur.gcloud.mc.core.data.Attach;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;

/**
 * 消息服务平台Message对象
 * 
 * @author Songh
 * @version 1.0
 * @since 2016年3月19日 
 */
/**
 * @author sh955
 *
 */
public class MessageObject {
	
	/**
	 * 信封表对象
	 */
	@Transient
	private List<Envelope> envelopeList;
	
	/**
	 * 消息表对象
	 */
	@Transient
	private Message message;
	
	/**
	 * 附件表对象
	 */
	@Transient
	private List<Attach> attachList;

	public List<Envelope> getEnvelopeList() {
		return envelopeList;
	}

	public void setEnvelopeList(List<Envelope> envelopeList) {
		this.envelopeList = envelopeList;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Attach> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<Attach> attachList) {
		this.attachList = attachList;
	}
	
	public int getEnvlopeCount(){
		if(envelopeList == null || envelopeList.isEmpty()){
			return 0;
		}else{
			return envelopeList.size();
		}
	}
	
	public int getAttachCount(){
		if(attachList == null || attachList.isEmpty()){
			return 0;
		}else{
			return attachList.size();
		}
	}
	
	public String getMessageType(){
		if(envelopeList == null || envelopeList.isEmpty()){
			return "-1";
		}else{
			return  envelopeList.get(0).getMessageType();
		}
	}
	
}
