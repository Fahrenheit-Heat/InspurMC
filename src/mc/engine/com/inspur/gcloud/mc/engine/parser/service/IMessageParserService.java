package com.inspur.gcloud.mc.engine.parser.service;

import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ResultMap;

public interface IMessageParserService {
	
	/**
	 * 站内消息页面接受视图表单对象解析为MessageObject对象
	 * 
	 * @param messageView 视图表单消息对象
	 * @return {MessageObject}
	 */
	public ResultMap instationMsgParser(MessageView messageView);
	
	/**
	 * 短信页面接受视图表单对象解析为MessageObject对象
	 * 
	 * @param messageView
	 * @return {MessageObject}
	 */
	public ResultMap shortMessageParser(MessageView messageView);

}
