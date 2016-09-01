package com.inspur.gcloud.ext.client;

import java.util.Map;

import com.inspur.gcloud.ext.service.IMessageCenterService;
import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.hsf.config.ServiceFactory;

/**
 * <p>Title: 消息服务客户端工具类</p>
 * <p>Description: 用于消息服务服务端发布服务提供给客户端使用</p>
 * @author h.song
 * @date 2016年6月22日 上午10:18:58
 * @vision 1.0
 */
public class GCloudMcUtil {
	
	// 发布消息服务中心高速服务接口
	private IMessageCenterService messageCenterService = (IMessageCenterService) ServiceFactory.getService("mc.mcServiceHsfProvider");
	
	// 初始化消息服务工具类对象
	private static GCloudMcUtil gCloudMcUtil = new GCloudMcUtil();
	
	private GCloudMcUtil(){}
	
	/**
	 * 获取唯一实例
	 * @return {GCloudMcUtil对象}
	 */
	public static GCloudMcUtil getInstance(){
		return gCloudMcUtil;
	}
	
	/**
	 * 获取当前未读消息数量方法
	 * @param loginId 当前登陆人ID
	 * @param messageTypes 消息类型数组
	 * 				  <code>m：站内消息</code>
	 * 				  <code>w：站内邮件</code>
	 *				  <code>e：站外邮件</code>
	 *				  <code>s：站外邮件</code>
	 * @return resultMap 结果集Map
	 * 				  <code>key：消息类型</code>
	 * 				  <code>value：未读消息数量</code>
	 */
	public Map<String, Integer> getUnreadMessageCount(String loginId, String[] messageTypes){
		return messageCenterService.getUnreadMessageCount(loginId, messageTypes);
	}
	
	/**
	 * 获取当前登陆人未读消息总数量方法
	 * @param loginId 当前登陆人ID
	 * @return count 未读消息数量
	 */
	public int getTotalUnreadMessageCount(String loginId){
		return messageCenterService.getTotalUnreadMessageCount(loginId);
	}
	
	/**
	 * 发送消息方法
	 * @param messageView 消息对象
	 * @param appModule 应用模块
	 * @return resultMap 消息平台统一返回对象
	 */
	public ResultMap sendMessage(MessageView messageView, String appModule){
		return messageCenterService.sendMessage(messageView, appModule);
	}
	
	/**
	 * 根据当前登录用户ID检查
	 * @param loginId 登录用户ID
	 */
	public void checkSystemMessage(String loginId, String loginUserId){
		messageCenterService.checkSystemMessage(loginId, loginUserId);
	}
	
}
