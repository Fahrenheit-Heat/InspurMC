package com.inspur.gcloud.mc.common;

public class McDataObjectConstants {
	
	/**
	 * 发送状态：未发送
	 */
	public static final String SEND_STATE_NOSEND = "0";
	
	/**
	 * 发送状态：已发送
	 */
	public static final String SEND_STATE_SUCCESS = "1";
	
	/**
	 * 发送状态：删除
	 */
	public static final String SEND_STATE_DELETE = "2";
	
	/**
	 * 发送状态：彻底删除
	 */
	public static final String SEND_STATE_REMOVE = "3";
	
	/**
	 * 发送中
	 */
	public static final String SEND_STATE_LOADING = "4";
	
	/**
	 * 发送失败
	 */
	public static final String SEND_STATE_FAIL = "5";
	
	/**
	 * 发送类型--原件
	 */
	public static final String SEND_TYPE_ORIGINAL = "0";
	
	/**
	 * 发送类型--回复
	 */
	public static final String SEND_TYPE_REPLAY = "1";
	
	/**
	 * 发送类型--转发
	 */
	public static final String SEND_TYPE_FORWARD = "2";
	
	/**
	 * 接受状态-未读
	 */
	public static final String RECEIVE_STATE_NOREAD = "0";
	
	/**
	 * 接受状态--已读
	 */
	public static final String RECEIVE_STATE_READED = "1";
	
	/**
	 * 接受状态--删除
	 */
	public static final String RECEIVE_STATE_DELETE = "2";
	
	/**
	 * 接受状态--彻底删除
	 */
	public static final String RECEIVE_STATE_REMOVE = "3";
	
	/**
	 * 接受状态--未接收
	 */
	public static final String RECEIVE_STATE_NORECEIVE = "NULL";
	
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
	 * 接收人类型-一对一
	 */
	public static final String RECEIVER_TYPE_ONEBYONE = "0";
	
	/**
	 * 接收人类型：组织机构ID
	 */
	public static final String RECEIVER_TYPE_ORAGINID = "1";
	
	/**
	 * 接收人类型：系统消息
	 */
	public static final String RECEIVER_TYPE_SYSTEM = "3";

}
