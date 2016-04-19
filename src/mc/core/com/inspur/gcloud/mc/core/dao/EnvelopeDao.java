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
     *              <code>receiverId<code>[查询收件箱时为当前用户ID]<br/>
     *              <code>senderId<code>[查询发件箱时为当前用户ID]<br/>
     *              <code>boxType<code>[邮箱类型：in：收件箱；out:已发送；draft:草稿箱]<br/>
     *              <code>orderfield<code>[排序列 ]<br/>
     *              <code>orderdir<code>[排序方向desc或asc] <br/>
     *              <code>start<code>[起始行]<br/>
     *              <code>limit<code>[每页显示条数]<br/>
     *              
     * @return List, 内容为Envelope对象列表
     * 
     */
	public List<Envelope> findList(Map map);
    
    /**
     * 根据消息主题、消息状态、发送时间、发件人或收件人 查找信息
     * @param map key分别为： <br/>
     * 				<code>messageTopic<code>[消息主题] <br/>
     * 				<code>sendTime<code>[发送时间] <br/>
     *              <code>receiveState<code>阅读状态：0：未读；1：已读；] <br/>
     *              <code>senderId<code>[发件人ID] <br/>
     *              <code>receiverId<code>[收件人ID]<br/>       
     * @return List， 内容为 Envelope对象列表
     * 
     */
	public List<Envelope> getByParams(Map map);
    
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
	public void changeState(Map map);
}
