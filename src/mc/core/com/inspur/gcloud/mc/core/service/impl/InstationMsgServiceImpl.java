package com.inspur.gcloud.mc.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspur.gcloud.mc.core.dao.EnvelopeDao;
import com.inspur.gcloud.mc.core.dao.MessageDao;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.core.service.IInstationMsgService;
import com.lc.gcloud.framework.util.GCloudUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author h.song
 * @date 2016年6月21日 下午3:18:15
 * @vision 1.0
 */
@Service("instationMsgService")
public class InstationMsgServiceImpl implements IInstationMsgService {
	
	@Autowired
	private EnvelopeDao envelopeDao;
	@Autowired
	private MessageDao messageDao;

	@Override
	public List<Map<String, Object>> findInstationMsgList(Map<String, String> parameterMap) {
		return envelopeDao.findInstaticonMsgList(parameterMap);
	}
	
	@Override
	public List<Map<String, Object>> getByParams(Map<String, String> parameters) {
		return envelopeDao.getInstationMsgByParams(parameters);
	}
	
	@Override
	public Envelope findEnvelopeByMessageIdAndLoginId(Map<String, String> map) {
		return envelopeDao.findEnvelopeByMessageIdAndLoginId(map);
	}
	
	@Override
	public int updateEnvelope(Envelope envelope) {
		return envelopeDao.update(envelope);
	}
	
	@Override
	public List<Envelope> findEnvelopeListByMessageId(String messageId) {
		return envelopeDao.findEnvelopeListByMessageId(messageId);
	}
	
	@Override
	public List<Envelope> findEnvelopeListByMessageIdAndLoginId(Map<String, String> map) {
		return envelopeDao.findEnvelopeListByMessageIdAndLoginId(map);
	}
	
	@Transactional
	@Override
	public void physicalDelete(List<Envelope> envelopeList, String messageId) {
		
		// 删除信封
		String[] envelopeIds = new String[envelopeList.size()];
		for (int i = 0; i < envelopeList.size(); i++) {
			envelopeIds[i] = envelopeList.get(i).getId();
		}
		
		// 删除消息
		messageDao.delete(messageId);
		// 批量删除信封 
		envelopeDao.batchDelete(envelopeIds);
	}
	
	@Transactional
	@Override
	public int saveEnvelope(Envelope envelope, String messageId) {
		int count = 0;
		if (envelope.getId() != null && !envelope.getId().equals("")) {
            // 更新信封信息
			envelope.setMessageId(messageId);
        	count = envelopeDao.update(envelope);
        } else {
            // 保存信封信息
        	envelope.setId(GCloudUtil.getInstance().getNextSeqId(32));
        	envelope.setMessageId(messageId);
        	count = envelopeDao.insert(envelope);
        }
        return count;
	}
	
	@Transactional
	@Override
	public Boolean batchInsertEnvelope(List<Envelope> envelopeList, Message message) {
		
		List<Envelope> newEnvelopeList = new ArrayList<Envelope>();
		message.setId(GCloudUtil.getInstance().getNextSeqId(32));
		for(int i = 0; i < envelopeList.size(); i++){
			Envelope envelope = envelopeList.get(i);
			envelope.setId(GCloudUtil.getInstance().getNextSeqId(32));
			envelope.setMessageId(message.getId());
			envelope.setMessage(message);
			newEnvelopeList.add(envelope);
		}

		// 插入消息
		messageDao.insert(message);
		// 批量插入信封
		envelopeDao.batchInsert(newEnvelopeList);
		return true;

	}
	
	@Override
	public Message findMessageById(String id) {
		return messageDao.get(id);
	}
	
	@Transactional
	@Override
	public void delete(Map<String, Object> map) {

		@SuppressWarnings("unchecked")
		List<Envelope> envelopeList = (List<Envelope>) map.get("envelopeList");
		for(int i = 0; i < envelopeList.size(); i++) {
			Map<String, String> changeMap = new HashMap<String, String>();
			changeMap.put("id", envelopeList.get(i).getId());
			changeMap.put("boxType", (String) map.get("boxType"));
			envelopeDao.changeState(changeMap);
		}
	}

	@Override
	public String insertMessage(Message message) {
		// 保存消息信息
    	String id = GCloudUtil.getInstance().getNextSeqId(32);
    	message.setId(id);
    	if(messageDao.insert(message) == 1){
    		 return id;
    	}else{
    		return null;
    	}
	}


}
