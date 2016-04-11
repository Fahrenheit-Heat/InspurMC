package com.inspur.gcloud.mc.core.dao;

import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.mapper.EntityMapper;

import com.inspur.gcloud.mc.core.data.Envelope;

/**
 * Dao层 接口类，用于持久化数据处理
 * 
 * 信封接口类
 * 
 * @author ZXh
 * 
 */
public interface EnvelopeDao extends EntityMapper<Envelope> {
	
	public List<Envelope> findEnvelopeListByMessageId(String messageId);
	
	public Envelope findEnvelopeByMessageIdAndLoginId(String messageId, String loginId);
	
	 /**
     * 查询当前用户的收件箱、已发送、草稿箱列表
     * 
     * @param map, key 分别为 ： 
     *              <code>messageType<code>[消息类型：M：站内消息；W：站内邮件；S：短信；    E：电子邮件；]
     *              <code>receiverId<code>[查询收件箱时为当前用户ID]
     *              <code>senderId<code>[查询发件箱时为当前用户ID]
     *              <code>boxType<code>[邮箱类型：in：收件箱；out:已发送；draft:草稿箱]
     *              <code>orderfield<code>[排序列 ]
     *              <code>orderdir<code>[排序方向desc或asc] 
     *              <code>start<code>[起始行]
     *              <code>limit<code>[每页显示条数]
     *              
     * @return List, 内容为Envelope对象列表
     * 
     */
	public List<Envelope> findList(Map map);
    
    /**
     * 根据消息主题、消息状态（已读或未读）发件人或收件人 查找信息
     * @param map key分别为： 
     * 				<code>messageTopic<code>[消息主题]
     *              <code>receiveState<code>阅读状态：0：未读；1：已读；]
     *              <code>senderId<code>[发件人ID]
     *              <code>receiverId<code>[收件人ID]              
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
     * 删除已发送的信封
     * 
     * @param map, key 分别为 ： 
     *              <code>id<code>[信封ID]
     *              <code>boxType<code>[邮箱类型：in：收件箱；out:已发送；draft:草稿箱]
     * 
     */
	public void changeState(Map map);
    
    /**
     * 删除已发送的信封
     * @param map, key 分别为 ： 
     *              <code>id<code>[信封ID]
     *              <code>boxType<code>[邮箱类型：in：收件箱；out:已发送；draft:草稿箱]
     *              
     * 
     * 
     */
    
}
