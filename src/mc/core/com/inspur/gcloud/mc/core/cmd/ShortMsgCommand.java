package com.inspur.gcloud.mc.core.cmd;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.gcloud.mc.common.McSystemConstants;
import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.core.service.IShortMsgService;
import com.inspur.gcloud.mc.engine.builder.service.IMessageBuilderService;
import com.inspur.gcloud.mc.engine.dispatcher.service.IMessageDispatcherService;
import com.inspur.gcloud.mc.engine.parser.service.IMessageParserService;
import com.inspur.hsf.service.common.utils.StringUtils;

/**
 * <p>Controller层，用于前后台交互以及前后套数据转换<p>
 * 短信Command
 * @author ZhaoZhenHua
 *
 */
@Controller
@RequestMapping(value="/mc/core/message")
public class ShortMsgCommand {

	@Autowired
	private IShortMsgService shortMsgService;
	@Autowired
	private IMessageParserService messageParserService;
	@Autowired
	private IMessageDispatcherService messageDispatcherService;
	@Autowired
	private IMessageBuilderService messageBuilderService;
	
	/**
	 * 初始化短信列表页面
	 * 
	 * @param parameters[初始化变量]
	 * 			Map中key分别为
	 * 			<code>messageType<code><br/>
	 * 			<code>loginName<code><br/>
	 * 			<code>loginId<code><br/>
	 * 			<code>groupfield<code><br/>
	 * 			<code>isSended<code><br/>
	 * 			<code>isSending<code><br/>
	 * 			<code>sendFailed<code><br/>
	 * @return envelopeMap[短信信封Map]<br/>
	 * 			Map中key分别为
	 * 			<code>envelopeList<code>短信信封List<br/>
	 * 			<code>total<code>查询到的总条数<br/>
	 */
	@RequestMapping("/MessageList")
	@ResponseBody
	public Map<String, Object> getMessageList(@RequestBody Map<String, String> parameters){
		Map<String, Object> envelopeMap = new HashMap<String, Object>();
		//调用短信初始化方法
		List<Map<String, Object>> mapList = shortMsgService.findShortMessageList(parameters);
		envelopeMap.put("data", mapList);
		// 获取总记录条数
		int total = PageUtil.getTotalCount();
		envelopeMap.put("total", total != -1 ? total : mapList.size());
		return envelopeMap;
	}
	
	/**
	 * 根据条件查询
	 * @param parameters[查询条件变量]
	 * @return Map key分别为
	 * 			<code>envelopeList<code>信封List
	 * 			<code>total<code>查询到的总条数
	 */
	@RequestMapping("/query")
    @ResponseBody
    public Map<String, Object> getByParams(@RequestBody Map<String, String> parameters) {
        Map<String, Object> envelopedata = new HashMap<String, Object>();
        // 处理日期时间
        String sendTimeFrom = parameters.get("sendTimeFrom");
        if(StringUtils.isNotEmpty(sendTimeFrom)){
        	parameters.put("sendTimeFrom", sendTimeFrom + " 00:00:00" );
        }
        String sendTimeTo = parameters.get("sendTimeTo");
        if(StringUtils.isNotEmpty(sendTimeTo)){
        	parameters.put("sendTimeTo",  sendTimeTo + " 23:59:59");
        }
        
        List<Map<String, Object>> mapList = shortMsgService.getShortMsgByParams(parameters);
        envelopedata.put("data", mapList);
        // 获取总记录条数
        int total = PageUtil.getTotalCount();
        envelopedata.put("total", total != -1 ? total : mapList.size());
        return envelopedata;
    }
	
	/**
	 * 新建消息页面跳转
	 * @param boxType [信箱类型]
	 * @return ModelAndView 新建消息页面
	 */
	@RequestMapping("/newMessage")
	public ModelAndView newMessage(@RequestParam (value = "boxType", required = false)String boxType){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boxType", boxType);
		return new ModelAndView("mc/message/newmessage/msg_create",model);
	}
	
	/**
	 * 短信发送方法
	 * @param messageView 页面接受消息对象
	 * @return model 对象{成功；失败}
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMessage(MessageView messageView){
		//解析消息视图
		ResultMap parserResultMap = messageParserService.shortMessageParser(messageView);
		Map<String, Object> model = new HashMap<String, Object>();
		if(parserResultMap.isSuccess()){
			//解析完成
			MessageObject messageObject = (MessageObject) parserResultMap.get("messageObject");
			//消息转发
			ResultMap dispatcherResultMap = messageDispatcherService.messageDispatcher(messageObject);
			if(dispatcherResultMap.isSuccess()){
				// 分发成功
				model.put("success", false);
			} else {
				// 分发失败
				model.put("error", false);
			}
		} else {
			//解析出错
			model.put("error", false);
		}
		return model;
	}
	
	/**
	 * 返回按钮页面跳转方法
	 * @param forwardType 跳转类型：
	 * 				 <code>已发送：messageOut</code>
	 *  			 <code>草稿箱：messageDraft</code>
	 *     			 <code>废件箱：messageScrap</code>
	 * @return Url 
	 */
	@RequestMapping("/forward/{boxType}")
	public String forwardMessageList(@PathVariable(value = "boxType")String boxType){
		if(McSystemConstants.MESSAGE_OUT_BOX.equals(boxType)){
			//已发送
			return "mc/message/outbox/msg_outbox_query";
		} else if(McSystemConstants.MESSAGE_DRAFT_BOX.equals(boxType)) {
			//草稿箱
			return "mc/message/draftbox/msg_draftbox_query";
		} else if(McSystemConstants.MESSAGE_SCRAP_BOX.equals(boxType)){
			//废件箱
			return "mc/message/scrapbox/msg_scrapbox_query";
		} else {
			return "";
		}
		
	}
	
	
    /**
     * 展示消息内容方法
     * <p>用途：编辑/查看等操作时，控制页面跳转</p>
     * @param messageId 消息ID
     * @param type 类型：
     * 				 <code>edit：编辑</code>
     * 				 <code>view：查看</code>
     * @return ModelAndView 
     */
	@RequestMapping("/showMessage")
	public ModelAndView showMessage(@RequestParam(value = "messageId", required = true) String messageId, 
			@RequestParam(value = "operType", required = true) String operType,
    		@RequestParam(value = "boxType", required = true) String boxType){
		
		// 新建视图对象
    	Map<String, Object> model = new HashMap<String, Object>();
    	// messageId
    	model.put("messageId", messageId);
    	// 消息箱类型
    	model.put("boxType", boxType);
    	
    	if(McSystemConstants.OPER_CODE_VIEW.equals(operType)){
    		//查看
    		return new ModelAndView("mc/message/newmessage/msg_view",model);
    	} else if(McSystemConstants.OPER_CODE_EDIT.equals(operType)) {
    		//编辑
    		model.put("operType", operType);
    		return new ModelAndView("mc/message/newmessage/msg_create",model);
    	} else {
    		return new ModelAndView("",model);
    	}
	}
	
	/**
	 * 短信查看方法
	 * @param messageId 消息ID
	 * @param loginId 登陆人Id
	 * @param boxType [信箱类型]
	 * @return messageView 消息视图
	 */
	@RequestMapping(value = "/viewMessage/{messageId}/{loginId}/{boxType}", method = RequestMethod.POST)
    @ResponseBody
    public MessageView viewMessage(@PathVariable("messageId") String messageId, 
    		@PathVariable("loginId") String loginId,
    		@PathVariable("boxType") String boxType){
		// 新建页面视图对象 
    	MessageView messageView = new MessageView();
    	// 判断messageId是否为空
    	if(messageId != null && !"".equals(messageId)){
    		// messageId不为空，获取组装视图对象的Envelope对象和Message对象
    		List<Envelope> envelopeList = shortMsgService.findEnvelopeListByMessageId(messageId);
    		Message message = shortMsgService.findMessageById(messageId);
    		// 构建页面展示消息对象
    		messageView = messageBuilderService.builderViewMessage(message, envelopeList);
    		//批量更新envelope状态
    		for(int i = 0; i < envelopeList.size(); i++){
    			// 收件箱判断是否为未读
    			if(envelopeList.get(i).getReceiverId().equals(loginId) && (envelopeList.get(i).getReceiveState() == null || "0".equals(envelopeList.get(i).getReceiveState()))){
        			// 未读状态：更新消息状态
        			envelopeList.get(i).setReadTime(new Date());
        			envelopeList.get(i).setReceiveState("1");
        			shortMsgService.updateEnvelope(envelopeList.get(i));
        		}
    		}
    	}
    	return messageView;
	}
	
	/**
	 * 编辑消息方法
	 * @param messageId 消息ID
	 * @param loginId 登陆人Id
	 * @param boxType [信箱类型]
	 * @return messageView 消息视图
	 */
	@RequestMapping("/editMessage/{messageId}/{loginId}/{boxType}")
    @ResponseBody
    public MessageView editMessage(@PathVariable(value = "messageId") String messageId,
    		@PathVariable(value="loginId") String loginId,
    		@PathVariable(value="boxType") String boxType){
		// 新建页面视图对象 
    	MessageView messageView = new MessageView();
    	// 判断messageId是否为空
    	if(messageId != null && !"".equals(messageId)){
    		// messageId不为空，获取组装视图对象的Envelope对象和Message对象
    		List<Envelope> envelopeList = shortMsgService.findEnvelopeListByMessageId(messageId);
    		Message message = shortMsgService.findMessageById(messageId);
    		// 构建转发页面视图对象
    		messageView = messageBuilderService.builderMessage(message, envelopeList);
    	}
    	return messageView;
	}
	
	/**
	 * 短信逻辑删除方法
	 * @param messageId 消息ID
	 * @param loginId 登陆人Id
	 * @param boxType [信箱类型]
	 * @return 对象{成功；失败}
	 */
	@RequestMapping(value = "/delete/{messageIds}/{loginId}/{boxType}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("messageIds") String messageIds, 
    		@PathVariable("loginId") String loginId, 
    		@PathVariable("boxType") String boxType){
		Map<String, Object> envelopeMap = new HashMap<String, Object>(); 
    	if (messageIds != null) {
             String[] messageIdArray = messageIds.split(",");
    		 List<Envelope> envelopeList = new ArrayList<Envelope>();
    		 for(int i = 0;i < messageIdArray.length;i++){
    			 Map<String, String> map = new HashMap<String, String>();
    			 map.put("messageId", messageIdArray[i]);
    			 map.put("boxType",boxType);
    			 map.put("loginId", loginId);
    			 //将不同messageId对应的EnvelopeList进行拼接
    			 List<Envelope> envelopeItem = shortMsgService.findEnvelopeListByMessageIdAndLoginId(map);
    			 envelopeList.addAll(envelopeItem);
    		 }
    		 envelopeMap.put("envelopeList", envelopeList);
    		 envelopeMap.put("boxType", boxType);
    		 shortMsgService.delete(envelopeMap);
         }
    	 Map<String, Object> model = new HashMap<String, Object>();
    	 model.put("success", true);
         return model;
	}
	
	/**
	 * 短信保存方法
	 * @param messageView 消息视图
	 * @param boxType [信箱类型]
	 * @return 对象{成功；失败}
	 */
	@RequestMapping(value = "/ajaxsave/{boxType}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxSave(MessageView messageView, @PathVariable("boxType") String boxType){
		List<Envelope> envelopeList = null;
    	String messageId = null;
    	Message message = new Message();
    	
    	ResultMap parserResultMap = messageParserService.shortMessageParser(messageView);
    	MessageObject messageObject = (MessageObject) parserResultMap.get("messageObject");

		message = messageObject.getMessage();
    	messageId = messageObject.getMessage().getId();
    	envelopeList = messageObject.getEnvelopeList();
    	
    	if(McSystemConstants.MESSAGE_DRAFT_BOX.equals(boxType)) {
    		//草稿箱保存的话先删除
    		shortMsgService.physicalDelete(envelopeList, messageId);
    	}
    	
		//设置发送状态为未发送
    	for(int i = 0; i < envelopeList.size(); i++) {
    		envelopeList.get(i).setSendState("0");
    	}
    	//插入
    	boolean flag = shortMsgService.batchInsertEnvelope(envelopeList, message);
    	Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", flag);
        return model;
	}
	
}
