package com.inspur.gcloud.ext.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.gcloud.ext.service.IMessageCenterService;
import com.inspur.gcloud.mc.common.McDataObjectConstants;
import com.inspur.gcloud.mc.common.McSystemConstants;
import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.common.util.McErrorOptionConstants;
import com.inspur.gcloud.mc.common.util.McLoggerUtil;
import com.inspur.gcloud.mc.core.dao.EnvelopeDao;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.engine.dispatcher.service.IMessageDispatcherService;
import com.inspur.gcloud.mc.engine.parser.service.IMessageParserService;
import com.lc.gcloud.framework.util.GCloudUtil;

/**
 * <p>Title: 消息服务接口服务接口实现类</p>
 * <p>Description: 用于实现消息服务对外接口方法实现</p>
 * @author h.song
 * @date 2016年6月22日 上午10:55:49
 * @vision 1.0
 */
@Service("mcServiceHsf")
public class MessageCenterServiceImpl implements IMessageCenterService {
	
	@Autowired
	private EnvelopeDao envelopeDao;
	@Autowired
	private IMessageParserService messageParserService;
	@Autowired
	private IMessageDispatcherService messageDispatcherService;
	
	private McLoggerUtil logger = McLoggerUtil.getInstance();

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Integer> getUnreadMessageCount(String loginId, String[] messageTypes) {
		// 构建查询条件Map集合
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("loginId", loginId);
		parameterMap.put("messageTypes", messageTypes);
		
		// 查询未读消息数目
		List returnMapList = envelopeDao.getUnreadMessageCount(parameterMap);
		
		// 初始化返回Map集合
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		for(int i = 0; i < messageTypes.length; i++){
			returnMap.put(messageTypes[i], 0);
		}
		
		// 根据查询结果赋值返回Map集合
		for(int i = 0; i < returnMapList.size(); i++){
			String key = (String)((Map) returnMapList.get(i)).get("MESSAGE_TYPE");
			int value = 0;
			Map resultMap = (Map) returnMapList.get(i);
			Object resultObj = resultMap.get("CNT");
			if(resultObj instanceof BigDecimal){
				value = ((BigDecimal) resultObj).intValue();
			}else if(resultObj instanceof Long){
				value = ((Long)resultObj).intValue();
			}else{
				value = 0;
			}
			returnMap.put(key, value);
		}

		return returnMap;
	}

	@Override
	public int getTotalUnreadMessageCount(String loginId) {
		return envelopeDao.getTotalUnreadMessageCount(loginId);
	}

	@Override
	public void checkSystemMessage(String loginId, String loginUserName) {
		
		// 获取当前登录用户收件箱中无系统信封
		List<Envelope> envelopeList = envelopeDao.getSystemMessageByLoginId(loginId);
		// 创建存放信封对象列表
		List<Envelope> newEnvelopeList = new ArrayList<Envelope>();
		
		if(envelopeList.size() > 0){
			// 遍历组装信封
			for(int i = 0; i < envelopeList.size(); i++){
				Envelope envelope = envelopeList.get(i);
				String oldId = envelope.getId();
				// 赋值主键ID
				envelope.setId(GCloudUtil.getInstance().getNextSeqId(32));
				// 设置关联消息ID
				envelope.setRelatedPath(oldId);
				envelope.setReceiverId(loginId);
				envelope.setReceiverName(loginUserName);
				envelope.setReceiverType(McDataObjectConstants.RECEIVER_TYPE_ORAGINID);
				newEnvelopeList.add(envelope);
			}
			envelopeDao.batchInsert(newEnvelopeList);
		}
	}

	@Override
	public ResultMap sendMessage(MessageView messageView, String appModule) {
		// 初始化返回对象
		ResultMap resultMap = new ResultMap();
		ResultMap parserResultMap = new ResultMap();
		
		// 通过消息类型获取视图解析器，解析视图消息对象
		String messageType = messageView.getMessageType();
		if(McDataObjectConstants.MESSAGE_TYPE_INSTATIONMSG.equals(messageType)){
			// 站内消息
			parserResultMap = messageParserService.instationMsgParser(messageView);
		}else if(McDataObjectConstants.MESSAGE_TYPE_INSTATIONMAIL.equals(messageType)){
			// 站内邮件
			
		}else if(McDataObjectConstants.MESSAGE_TYPE_OUTSTATIONMAIL.equals(messageType)){
			// 站外邮件
			
		}else if(McDataObjectConstants.MESSAGE_TYPE_MESSAGE.equals(messageType)){
			// 短信
			parserResultMap = messageParserService.shortMessageParser(messageView);
		}else{
			// 获取解析器失败
			resultMap.setSuccess(false);
			resultMap.setMsg("提示：获取解析器失败，未正确匹配类型！");
			resultMap.addErrorType(McErrorOptionConstants.MESSAGE_NO_TYPE);
			resultMap.addErrorCode(McErrorOptionConstants.MESSAGE_NO_TYPE_CODE);
			
			logger.bizLog("", appModule, resultMap.getErrorCode(), resultMap.getMsg());
			
			return resultMap;
		}
    	
		// 消息转发
    	if(parserResultMap.isSuccess()){
    		// 解析成功
    		MessageObject messageObject = (MessageObject)parserResultMap.get("messageObject");
    		// 消息发送
    		ResultMap dispatcherResultMap = messageDispatcherService.messageDispatcher(messageObject);
    		if(dispatcherResultMap.isSuccess()){
    			// 转发成功
    			resultMap.setSuccess(true);
    			resultMap.setMsg("提示：发送成功！");
    			
    			logger.bizLog("", appModule, "200", resultMap.getMsg());
    			
    			return resultMap;
    		}else{
    			// 转发失败
    			return dispatcherResultMap;
    		}
    	}else{
    		// 解析出错
    		return parserResultMap;
    	}
	}
	
}
