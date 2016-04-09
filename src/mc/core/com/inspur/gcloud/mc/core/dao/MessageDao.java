/**
 * 
 */
package com.inspur.gcloud.mc.core.dao;

import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.mapper.EntityMapper;

import com.inspur.gcloud.mc.core.data.Message;

/**
 * Dao层 接口类，用于持久化数据处理
 * 
 * 消息接口类
 * 
 * @author ZXh
 * 
 */
public interface MessageDao extends EntityMapper<Message> {

/**
* 根据消息主题、消息状态（已读或未读）发件人或收件人 查找信息
* @param map key分别为： 
* 			   <code>messageTopic<code>[消息主题]
*              <code>receiveState<code>阅读状态：0：未读；1：已读；]
*              <code>senderId<code>[发件人ID]
*              <code>receiverId<code>[收件人ID]              
* @return List， 内容为 Envelope对象列表
* 
*/
List<Message> getByParams(Map map);

//////////// 删除   ///////////////////////////

/**
* 删除消息
* 
* @param map, key 分别为 ： 
*              <code>id<code>[信封ID]
*              <code>boxType<code>[邮箱类型：in：收件箱；out:已发送；draft:草稿箱]
* 
*/
//void delete(Map map);

Message getMessageById(String id);

}
