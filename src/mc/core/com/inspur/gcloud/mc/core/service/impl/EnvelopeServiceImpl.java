package com.inspur.gcloud.mc.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.core.dao.EnvelopeDao;
import com.inspur.gcloud.mc.core.dao.MessageDao;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.core.service.IEnvelopeService;
import com.lc.gcloud.framework.util.GCloudUtil;

@Service("envelopeService")
public class EnvelopeServiceImpl implements IEnvelopeService {
	
	@Autowired
	private EnvelopeDao envelopeDao;
	
	@Autowired
	private MessageDao messageDao;
	
	private MessageObject messageObject;

	@Override
	public List<Envelope> findList(Map parameters) {
		return envelopeDao.findList(parameters);
	}

	@Override
	public List<Envelope> getByParams(Map<String, String> parameters) {
		return envelopeDao.getByParams(parameters);
	}

	@Override
	public Envelope findEnvelopeById(String id) {
		return envelopeDao.get(id);
	}
	
	@Override
	public Message findMessage(String id) {
		return messageDao.get(id);
	}

	@Override
	public int saveEnvelope(Envelope envelope, String messageId) {
		int count = 0;
		if (envelope.getId() != null && !envelope.getId().equals("")) {
            // 更新信封信息
        	count = envelopeDao.update(envelope);
        	//更新消息信息
        	envelope.getMessage().setId(messageId);
        	messageDao.update(envelope.getMessage());
        } else {
            // 保存信封信息
        	envelope.setId(GCloudUtil.getInstance().getNextSeqId(32));
        	envelope.setMessageId(messageId);
        	count = envelopeDao.insert(envelope);
        	// 保存消息信息
        	envelope.getMessage().setId(messageId);
        	messageDao.insert(envelope.getMessage());
        }
        return count;
	}

	@Override
	public void delete(Map map) {

		String ids = (String) map.get("ids");
		String messageIds = (String) map.get("messageIdArray");
		String[] messageIdArray = messageIds.split(",");
		String[] idArray = ids.split(",");
		envelopeDao.batchDelete(idArray);
		messageDao.batchDelete(messageIdArray);
	}
	//插入新信封
	@Override
	public int batchSaveEnvelope(List<Envelope> envelopeList, String messageId) {
		List<Envelope> newEnvelopeList = new ArrayList<Envelope>();
		for(int i = 0; i < envelopeList.size(); i++){
			Envelope envelope = envelopeList.get(i);
			envelope.setId(GCloudUtil.getInstance().getNextSeqId(32));
			envelope.setMessageId(messageId);
			newEnvelopeList.add(envelope);
		}
		return envelopeDao.batchInsert(newEnvelopeList);
	}
	//更新原有信封
	public int batchUpdateEnvelope(List<Envelope> envelopeList, String messageId){
		List<Envelope> newEnvelopeList = new ArrayList<Envelope>();
		for(int i = 0; i < envelopeList.size(); i++){
			Envelope envelope = envelopeList.get(i);
			envelope.setMessageId(messageId);
			newEnvelopeList.add(envelope);
		}
		return envelopeDao.batchUpdate(newEnvelopeList);
	}

	@Override
	public String findMessageId(String id) {
		return envelopeDao.getMessageId(id);
	}

	@Override
	public MessageObject makeUpMessageObject(Envelope envelope,String messageId) {
		messageObject.setEnvelopeList(envelopeDao.getAll());//!!!
		messageObject.setMessage(messageDao.getMessageById(messageId));
		return messageObject;
	}



}
