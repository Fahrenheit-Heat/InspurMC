
package com.inspur.gcloud.mc.core.service;

import java.util.List;
import java.util.Map;

import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;

/**
 * 信封接口类
 * 
 * Service层 接口类，用于业务逻辑处理，事务控制等
 * 
 * @author ZhaoZhenHua
 *
 */
public interface IEnvelopeService {
	/**
	 * 通过MessagId查找Envelope信封List
	 * 
	 * @param messageId[消息ID]
	 * @return List Envelope信封列表
	 */
	public List<Envelope> findEnvelopeListByMessageId(String messageId);
	
	/**
	 * 根据Map信息获取Envelope
	 * @param map key 分别为：<br/>
	 * 				<code>messageId<code>[消息ID]<br/>
	 * 				<code>loginId<code>[当前用户ID]<br/>
	 * 				<code>boxType<code>[邮箱类型]
	 * @return Envelope对象
	 */
	public Envelope findEnvelopeByMessageIdAndLoginId(Map<String, String> map);
	
	/**
	 * 通过messageId、boxType和LoginId查找对应EnvelopeList
	 * 
	 * @param map key 分别为：<br/>
	 * 				<code>messageId<code>[消息Id]<br/>
	 * 				<code>boxType<code>[邮箱类型]<br/>
	 * 				<code>LoginId<code>[当前用户ID]<br/>
	 * @return	List Envelope信封列表
	 */
	public List<Envelope> findEnvelopeListByMessageIdAndLoginId(Map<String, String> map);
	 
	/**
     * 查询当前用户的收件箱、已发送、草稿箱列表
     * 
     * @param map key 分别为 ： <br/>
     *              <code>messageType<code>[消息类型：M：站内消息；W：站内邮件；S：短信；    E：电子邮件；] <br/>
     *              <code>organId<code>[当前用户ID] <br/>
     *              <code>boxType<code>[邮箱类型：in：收件箱；out:已发送；draft:草稿箱] <br/>
     *              <code>orderfield<code>[排序列 ] <br/>
     *              <code>orderdir<code>[排序方向desc或asc]  <br/>
     *              <code>start<code>[起始行] <br/>
     *              <code>limit<code>[每页显示条数] <br/>
     *              
     * @return List, 内容为Envelope对象列表
     * 
     */
    public List<Envelope> findList(Map<String, String> map);

    /**
     * 根据消息主题、消息状态（已读或未读）发件人或收件人 查找信息
     * @param map key分别为：  <br/>
     * 				<code>messageTopic<code>[消息主题] <br/>
     *              <code>receiveState<code>阅读状态：0：未读；1：已读；] <br/>
     *              <code>senderId<code>[发件人ID] <br/>
     *              <code>receiverId<code>[收件人ID]      <br/>         
     * @return List， 内容为 Envelope对象列表
     * 
     */
    public List<Envelope> getByParams(Map<String, String> map);

    /**
     * 根据ID获取用户信息
     * 
     * @param id [ID主键]
     * 
     * @return Envelope 对象
     * 
     */
    public Envelope findEnvelopeById(String id);
    
    /**
     * 根据信封Id查找消息Message_Id
     * @param id 信封ID
     * @return Message_Id 消息ID
     */
    public String findMessageId(String id);

    /**
     * 根据id查询用户档案信息
     * 
     * @param id [消息ID]
     * 
     * @return Message  对象
     * 
     */
   public Message findMessage(String id);
   
   /**
    * 保存新增和修改后的信封信息
    * 
    * @param envelope  信封对象
    * @param messageId 消息ID
    * @return 保存条数
    */
   public int saveEnvelope(Envelope envelope, String messageId);
    
    /**
     * 批量插入信封和信息
     * @param envelopeList 信封表列表
     * @param messageId 消息Id
     * @return Boolean 操作结果
     */
    public Boolean batchInsertEnvelope(List<Envelope> envelopeList, Message message);

    /**
     * 
     * @param map key 分别为：<br/>
     * 				<code>envelopeList<code>[待删除的消息List]
     * 				<code>boxType<code>[消息邮箱类型]
     */
    public void delete(Map<String, Object> map);

    /**
     * 根据ID批量删除信封，物理删除，更新时使用
     * 
     * @param envelopeList 待删除的信封List
     * @param messageId 待删除的消息Id
     */
    public void physicalDelete(List<Envelope> envelopeList, String messageId);
    
    /**
     * 更新信封表方法
     * <p>主要用于：更新信封表接收状态和阅读时间</p>
     * @param envelopeId 信封表主键ID
     * @param receiveState 接受状态
     * @return 更新数量
     */
    public int updateEnvelope(Envelope envelope);

}
