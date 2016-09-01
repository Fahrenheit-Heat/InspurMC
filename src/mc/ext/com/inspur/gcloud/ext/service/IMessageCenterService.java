package com.inspur.gcloud.ext.service;

import java.util.Map;

import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ResultMap;

/**
 * <p>Title: 消息服务接口服务接口</p>
 * <p>Description: 用于发布消息服务对外接口</p>
 * @author h.song
 * @date 2016年6月22日 上午10:57:12
 * @vision 1.0
 */
public interface IMessageCenterService {
	
	/**
	 * 获取当前未读消息数量方法
	 * @param loginId 当前登陆人ID
	 * @param messageTypes 消息类型数组
	 * 				  <code>m：站内消息</code>
	 * 				  <code>w：站内邮件</code>
	 *				  <code>e：站外邮件</code>
	 * @return resultMap 结果集Map
	 * 				  <code>key：消息类型</code>
	 * 				  <code>value：未读消息数量</code>
	 */
	public Map<String, Integer> getUnreadMessageCount(String loginId, String[] messageTypes);
	
	/**
	 * 获取当前登陆人未读消息总数量方法
	 * @param loginId 当前登陆人ID
	 * @return count 未读消息数量
	 */
	public int getTotalUnreadMessageCount(String loginId);
	
	/**
	 * 发送消息方法
	 * @param messageView 消息对象
	 * @param appModule 应用模块
	 * @return resultMap 消息平台统一返回对象
	 */
	public ResultMap sendMessage(MessageView messageView, String appModule);
	
   /**
     * 根据当前登录用户ID检查
     * @param loginId 当前登陆人ID
     * @param loginUserName 当前登陆人姓名
     */
    public void checkSystemMessage(String loginId, String loginUserName);

}
