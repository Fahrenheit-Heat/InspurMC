package com.inspur.gcloud.mc.core.dao;

import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.mapper.EntityMapper;

import com.inspur.gcloud.mc.core.data.Envelope;

/**
 * Dao层 接口类，用于持久化数据处理<br/>
 * 
 * 信封接口类
 * 
 * @author ZhaoZhenhua
 * 
 */
public interface EnvelopeDao extends EntityMapper<Envelope> {
	
	/**
	 * 查询当前登陆人未读消息数量
	 * @param paramterMap key分别为：
	 * 					<code>loginId：登陆人ID</code>
	 * 					<code>messageType：消息类型</code>
	 * @return Map结果集
	 */
	public List<Map<String, Integer>> getUnreadMessageCount(Map<String, Object> paramterMap);
	
	/**
	 * 获取当前登陆人未读消息总数量方法
	 * @param loginId 当前登陆人ID
	 * @return count 未读消息数量
	 */
	public int getTotalUnreadMessageCount(String loginId);
	
	/**
	 * 通过MessageId寻找信封列表
	 * 
	 * @param messageId 消息Id
	 * @return List<Envelope> 信封列表	
	 */
	public List<Envelope> findEnvelopeListByMessageId(String messageId);
	
	/**
	 * 通过MessageId，LoginId，boxtype查找对应信封
	 * 
	 * @param map , key 分别为：<br/>
	 * 				<code>messageId<code>[消息ID]<br/>
	 * 				<code>boxType<code>[消息邮箱类型：参见McConstants]<br/>
	 * 				<code>loginId<code>[当前用户ID]<br/>
	 * @return	Envelope对象
	 */
	public Envelope findEnvelopeByMessageIdAndLoginId(Map<String, String> map);
	
	/**
	 * 通过MessageId，LoginId，boxtype查找对应信封List
	 * 
	 * @param map , key 分别为：<br/>
	 * 				<code>messageId<code>[消息ID]<br/>
	 * 				<code>boxType<code>[消息邮箱类型：参见McConstants]<br/>
	 * 				<code>loginId<code>[当前用户ID]<br/>
	 * @return
	 */
	public List<Envelope> findEnvelopeListByMessageIdAndLoginId(Map<String, String> map);
	
	 /**
     * 查询当前用户的收件箱、已发送、草稿箱列表
     * 
     * @param map , key 分别为 ： <br/>
     *              <code>messageType<code>[消息类型：M：站内消息；W：站内邮件；S：短信；    E：电子邮件；]<br/>
     *              <code>loginId<code>[当前用户ID]<br/>
     *              <code>receiverId<code>[收件人ID]<br/>
     *              <code>senderId<code>[发件人ID]<br/>
     *              <code>senderName<code>[发件人姓名]<br/>
     *              <code>isNotSended<code>[未发送：0]<br/>
     *              <code>isSended<code>[已发送：1]<br/>
     *              <code>isScrap<code>[已删除：2]<br/>
     *              <code>isNotRead<code>[未读：0]<br/>
     *              <code>isRead<code>[已读：1]<br/>
     *              <code>groupfield<code>[是否按照messageID分组查询]<br/>
     *              <code>start<code>[起始行]<br/>
     *              <code>limit<code>[每页显示条数]<br/>
     *              
     * @return List, 内容为Envelope对象列表
     * 
     */
	public List<Map<String, Object>> findInstaticonMsgList(Map<String, String> parameterMap);
	
	/**
	 * 查询当前用户的短信的已发送、草稿箱、废件箱列表
     * @param map , key 分别为 ： <br/>
     *              <code>messageType<code>[消息类型：M：站内消息；W：站内邮件；S：短信；    E：电子邮件；]<br/>
     *              <code>loginId<code>[当前用户ID]<br/>
     *              <code>receiverId<code>[收件人手机号]<br/>
     *              <code>senderId<code>[发件人ID]<br/>
     *              <code>senderName<code>[发件人姓名]<br/>
     *              <code>isNotSended<code>[未发送：0]<br/>
     *              <code>isSended<code>[已发送：1]<br/>
     *              <code>isScrap<code>[已删除：2]<br/>
     *              <code>isSending<code>[发送中：4]<br/>
     *              <code>sendFailed<code>[发送失败：5]<br/>
     *              <code>isNotRead<code>[未读：0]<br/>
     *              <code>isRead<code>[已读：1]<br/>
     *              <code>groupfield<code>[是否按照messageID分组查询]<br/>
     *              <code>start<code>[起始行]<br/>
     *              <code>limit<code>[每页显示条数]<br/>
     *              
     * @return List, 内容为Envelope对象列表
     * 
     */
	public List<Map<String, Object>> findShortMessageList(Map<String, String> parameterMap);
    
    /**
     * 根据消息主题、消息状态、发送时间、发件人或收件人 查找信息
     * @param map key分别为： <br/>
     * 				<code>messageTopic<code>[消息主题] <br/>
     * 				<code>sendTime<code>[发送时间] <br/>
     *              <code>receiveState<code>阅读状态：0：未读；1：已读；] <br/>
     *              <code>senderId<code>[发件人ID] <br/>
     *              <code>receiverId<code>[收件人ID]<br/> 
     *              <code>senderName<code>[发件人姓名]<br/>
     *              <code>receiverName<code>[收件人姓名]<br/>
     *              <code>sendState<code>[发送状态]<br/>
     *              <code>messageType<code>[消息类型：M：站内消息；W：站内邮件；S：短信；    E：电子邮件；]<br/>
     *              <code>messageContent<code>[消息内容]<br/>
     *              <code>isNotSended<code>[未发送：0]<br/>
     *              <code>isSended<code>[已发送：1]<br/>
     *              <code>isScrap<code>[已删除：2]<br/>
     *              <code>isSending<code>[发送中：4]<br/>
     *              <code>sendFailed<code>[发送失败：5]<br/>
     *              <code>isNotRead<code>[未读：0]<br/>
     *              <code>isRead<code>[已读：1]<br/>
     *              <code>groupfield<code>[是否按照messageID分组查询]<br/>
     *              <code>sendTimeFrom<code>[查询信封开始日期](不包括)<br/>
     *              <code>sendTimeTo<code>[查询信封截止日期](不包括)<br/>
     *              
     * @return List， 内容为 Envelope对象列表
     * 
     */
	public List<Map<String, Object>> getInstationMsgByParams(Map<String, String> parameterMap);
	
	 /**
     * 根据消息主题、消息状态、发送时间、发件人或收件人 查找信息
     * @param map key分别为： <br/>
     * 				<code>messageTopic<code>[消息主题] <br/>
     * 				<code>sendTime<code>[发送时间] <br/>
     *              <code>receiveState<code>阅读状态：0：未读；1：已读；] <br/>
     *              <code>senderId<code>[发件人ID] <br/>
     *              <code>receiverId<code>[收件人ID]<br/> 
     *              <code>senderName<code>[发件人姓名]<br/>
     *              <code>receiverName<code>[收件人姓名]<br/>
     *              <code>sendState<code>[发送状态]<br/>
     *              <code>messageType<code>[消息类型：M：站内消息；W：站内邮件；S：短信；    E：电子邮件；]<br/>
     *              <code>messageContent<code>[消息内容]<br/>
     *              <code>isNotSended<code>[未发送：0]<br/>
     *              <code>isSended<code>[已发送：1]<br/>
     *              <code>isScrap<code>[已删除：2]<br/>
     *              <code>isSending<code>[发送中：4]<br/>
     *              <code>sendFailed<code>[发送失败：5]<br/>
     *              <code>isNotRead<code>[未读：0]<br/>
     *              <code>isRead<code>[已读：1]<br/>
     *              <code>groupfield<code>[是否按照messageID分组查询]<br/>
     *              <code>sendTimeFrom<code>[查询信封开始日期](不包括)<br/>
     *              <code>sendTimeTo<code>[查询信封截止日期](不包括)<br/>
     *              
     * @return List， 内容为 Envelope对象列表
     * 
     */
	public List<Map<String, Object>> getShortMsgByParams(Map<String, String> parameterMap);
    
    /**
     * 根据Envelope的id找对应的MessageId
     * @param id
     * @return MessageId
     */
	public String getMessageId(String id);
    
    /**
     * 逻辑删除信封的方法
     * 
     * @param map, key 分别为 ： 
     *              <code>id<code>[信封ID]
     *              <code>boxType<code>[邮箱类型：in：收件箱；out:已发送；draft:草稿箱]
     * 
     */
	public void changeState(Map<String, String> map);
	
	/**
	 * 通过当前登陆人ID获取系统消息结果集中当前登陆人未有消息ID
	 * @param loginId 当前登录人ID
	 * @return 消息ID列表
	 */
	public List<Envelope> getSystemMessageByLoginId(String loginId);
	
	/**
	 * 通过信封表主键ID更新发送状态
	 * @param id 信封表主键ID
	 * @return int 更新数量
	 */
	public int updateSendStateById(Map<String, String> parameterMap);
}
