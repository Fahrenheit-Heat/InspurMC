package com.inspur.gcloud.mc.common.util;

import com.inspur.gcloud.mc.common.McHandleTextConstants;

public class HandleMessageContentUtil {
	
	public static HandleMessageContentUtil handleMessageContentUtil;
	
	/**
	 * 单例模式，获取工具类入口
	 * @return handleMessageContentUtil
	 */
	public static synchronized HandleMessageContentUtil getInstance(){
		if(handleMessageContentUtil == null){
			handleMessageContentUtil = new HandleMessageContentUtil();
		}
		return handleMessageContentUtil;
	}
	
	public String addCSSMessageContext(String senderName, String sendTime, String messageTopic, String messageContent){
		String handleStr = "";
		handleStr += "<br/>";
		handleStr += "<br/>";
		handleStr += "<hr>";
		handleStr += "<p style='background-color: rgb(191, 191, 191); '>";
		handleStr += "<label>";
		handleStr += "<font face='微软雅黑' size='2' style='font-weight:bold;'>"+McHandleTextConstants.HANDLE_SENDER_NAME+"</font>";
		handleStr += "<font face='微软雅黑' size='2'>"+senderName+"</font>";
		handleStr += "</label>";
		handleStr += "<br/>";
		handleStr += "<label>";
		handleStr += "<font face='微软雅黑' size='2' style='font-weight:bold;'>"+McHandleTextConstants.HANDLE_SEND_TIME+"</font>";
		handleStr += "<font face='微软雅黑' size='2'>"+sendTime+"</font>";
		handleStr += "</label>";
		handleStr += "<br/>";
		handleStr += "<label>";
		handleStr += "<font face='微软雅黑' size='2' style='font-weight:bold;'>"+McHandleTextConstants.HANDLE_MESSAGE_TOPIC+"</font>";
		handleStr += "<font face='微软雅黑' size='2'>"+messageTopic+"</font>";
		handleStr += "</label>";
		handleStr += "</p>";
		handleStr += "<br/>";
		handleStr += messageContent;
		return handleStr;
	}

}
