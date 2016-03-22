package com.inspur.gcloud.mc.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.gcloud.mc.core.dao.EnvelopeDao;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.service.IEnvelopeService;

@Service("envelopeService")
public class EnvelopeServiceImpl implements IEnvelopeService {
	
	@Autowired
	private EnvelopeDao envelopeDao;

	@SuppressWarnings("rawtypes")
	@Override
	public List<Envelope> findList(Map parameters) {
		return envelopeDao.findList(parameters);
	}

	@Override
	public List<Envelope> getByParams(Map<String, String> parameters) {
		return envelopeDao.getByParams(parameters);
	}

	@Override
	public Envelope findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Envelope save(Envelope envelope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Map map) {
		// TODO Auto-generated method stub

	}

}
