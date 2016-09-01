package com.inspur.gcloud.ext.sender.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inspur.gcloud.ext.sender.IShortMessageSender;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.common.data.ShortMessage;

@Service("shortMessageSender")
public class ShortMessageSenderImpl implements IShortMessageSender {

	@Override
	public ResultMap sendMessageImpl(List<ShortMessage> messageList) {
		
		ResultMap resultMap = new ResultMap();
		List<String> successList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		
		for(int i = 0; i < messageList.size(); i++){
			String envelopeId = messageList.get(i).getEnvelopeId();
			errorList.add(envelopeId);
		}
		
		resultMap.setSuccessList(successList);
		resultMap.setErrorList(errorList);
		return resultMap;
	}

	@Override
	public ResultMap sendMessageImpl(ShortMessage shortMessage) {
		// TODO Auto-generated method stub
		return null;
	}

}
