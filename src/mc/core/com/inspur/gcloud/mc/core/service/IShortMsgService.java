package com.inspur.gcloud.mc.core.service;

import java.util.List;
import java.util.Map;

import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;

public interface IShortMsgService {
	
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
    public List<Map<String,Object>> getShortMsgByParams(Map<String, String> map);
    
	/**
	 * 通过MessagId查找Envelope信封List
	 * 
	 * @param messageId[消息ID]
	 * @return List Envelope信封列表
	 */
	public List<Envelope> findEnvelopeListByMessageId(String messageId);
	
	/**
     * 查询当前用户的短信的已发送、草稿箱、废件箱列表
     * 
     * @param map key 分别为 ： <br/>
     *              <code>messageType<code>[消息类型：M：站内消息；W：站内邮件；S：短信；    E：电子邮件；] <br/>
     *              <code>loginName<code>[当前用户名称] <br/>
     *              <code>loginId<code>[当前用户ID] <br/>
     *              <code>groupfield<code>[是否群发显示一条] <br/>
     *              <code>isSended<code>[短信状态：已发送] <br/>
     *              <code>isSending<code>[短信状态：正在发送] <br/>
     *              <code>sendFailed<code>[短信状态：发送失败]  <br/>
     *              <code>start<code>[起始行] <br/>
     *              <code>limit<code>[每页显示条数] <br/>
     *              
     * @return List, 内容为Envelope对象列表
     * 
     */
    public List<Map<String, Object>> findShortMessageList(Map<String, String> map);

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
    * 保存新增和修改后的信封信息
    * 
    * @param envelope  信封对象
    * @param messageId 消息ID
    * @return 保存条数
    */
   public int saveEnvelope(Envelope envelope, String messageId);
    
    /**
     * 更新信封表状态方法
     * <p>主要用于：调用外部发送器后，回调更新信封表状态</p>
     * @param idList 主键ID列表
     * @param sendState 发送状态
     * @return int 更新成功数量
     */
    public int batchUpdateEnvelopeSendState(List<String> idList, String sendState);
    
    /**
     * 批量插入信封和信息
     * @param envelopeList 信封表列表
     * @param messageId 消息Id
     * @return Boolean 操作结果
     */
    public Boolean batchInsertEnvelope(List<Envelope> envelopeList, Message message);
    
    /**
     * 更新信封表方法
     * <p>主要用于：更新信封表接收状态和阅读时间</p>
     * @param envelopeId 信封表主键ID
     * @param receiveState 接受状态
     * @return 更新数量
     */
    public int updateEnvelope(Envelope envelope);
    
    /**
     * 根据ID批量删除信封，物理删除，更新时使用
     * 
     * @param envelopeList 待删除的信封List
     * @param messageId 待删除的消息Id
     */
    public void physicalDelete(List<Envelope> envelopeList, String messageId);
    
    /**
     * 
     * @param map key 分别为：<br/>
     * 				<code>envelopeList<code>[待删除的消息List]
     * 				<code>boxType<code>[消息邮箱类型]
     */
    public void delete(Map<String, Object> map);
    
    /**
     * 通过MessageId查找Message对象
     * 
     * @param id
     * @return Message
     */
	public Message findMessageById(String id);
	
	/**
     * 保存新增和修改后的信封信息
     * 
     * @param envelope
     * 
     * @return Envelope
     * 
     */
    public String insertMessage(Message message);
}
