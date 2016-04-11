package com.inspur.gcloud.mc.common;

/**
 * <p>消息中心常量类</p>
 * 
 * @author Songh
 * @version 1.0
 * @since 2016年3月19日
 *
 */
public class McConstants {
	
	/**
	 * 发送成功{0}
	 */
	public static final String SEND_SUCCESS = "0";
	
	/**
	 * 发送失败{-1}
	 */
	public static final String SEND_FAULT = "-1";
	
	/**
	 * 未知消息类型
	 */
	public static final String MESSAGE_NO_TYPE = "noMsgType";
	
	/**
	 * 消息类型-站内信
	 */
	public static final String MESSAGE_TYPE_INSTATIONMSG = "m";
	
	/**
	 * 消息类型-站内邮件
	 */
	public static final String MESSAGE_TYPE_INSTATIONMAIL = "w";
	
	/**
	 * 消息类型-短信
	 */
	public static final String MESSAGE_TYPE_MESSAGE = "s";
	
	/**
	 * 消息类型-站外邮件
	 */
	public static final String MESSAGE_TYPE_OUTSTATIONMAIL = "e";
	
	/**
	 * 错误类型-消息转发器
	 */
	public static final String ERROR_TYPE_DISPATCHER = "errorDispatcher";
	
	/**
	 * 错误类型-消息解析器
	 */
	public static final String ERROR_TYPE_PARSER = "errorParser";
	
	/**
	 * 错误类型-消息构建器
	 */
	public static final String ERROR_TYPE_BUILDER = "errorBuilder";
	
	/**
	 * 错误类型-消息发送器
	 */
	public static final String ERROR_TYPE_SENDER = "errorSender";
	
	/**
	 * 操作码-编辑
	 */
	public static final String OPER_CODE_EDIT = "edit";
	
	/**
	 * 操作码-回复
	 */
	public static final String OPER_CODE_REPLY = "reply";
	
	/**
	 * 操作码-转发
	 */
	public static final String OPER_CODE_FORWARD = "forward";
	
	/**
	 * 操作码-查看
	 */
	public static final String OPER_CODE_VIEW = "view";
	
	/**
	 * 站内消息-收件箱
	 */
	public static final String INSTATIONMSG_IN_BOX = "instationMsgIn";
	
	/**
	 * 站内消息-发件箱
	 */
	public static final String INSTATIONMSG_OUT_BOX = "instationMsgOut";
	
	/**
	 * 站内消息-草稿箱
	 */
	public static final String INSTATIONMSG_DRAFT_BOX = "instationMsgDraft";
	
	/**
	 * 站内消息-废件箱
	 */
	public static final String INSTATIONMSG_SCRAP_BOX = "instationMsgScrap";

}
