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

import com.inspur.gcloud.mc.common.McConstants;
import com.inspur.gcloud.mc.common.data.MessageObject;
import com.inspur.gcloud.mc.common.data.MessageView;
import com.inspur.gcloud.mc.common.data.ResultMap;
import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.core.service.IEnvelopeService;
import com.inspur.gcloud.mc.core.service.IMessageService;
import com.inspur.gcloud.mc.engine.builder.service.IMessageBuilderService;
import com.inspur.gcloud.mc.engine.dispatcher.service.IMessageDispatcherService;
import com.inspur.gcloud.mc.engine.parser.service.IMessageParserService;


/**
 * <p>Controller层，用于前后台交互、前后台数据格式转换</p>
 * 站内消息Command
 * @author songH
 *
 */
@Controller
@RequestMapping(value = "/mc/core/instationmessage")
public class instationMsgCommand {
	
	@Autowired
	private IMessageService messageService;
	@Autowired
    private IEnvelopeService envelopeService;
	@Autowired
	private IMessageDispatcherService messageDispatcherService;
	@Autowired
	private IMessageParserService messageParserService;
	@Autowired
	private IMessageBuilderService messageBuilderService;
	
	/**
	 * 返回按钮页面跳转方法
	 * @param forwardType 跳转类型：
	 * 	             <code>收件箱：instationMsgIn</code>
	 * 				 <code>发件箱：instationMsgOut</code>
	 *  			 <code>草稿箱：instationMsgDraft</code>
	 *     			 <code>废件箱：instationMsgScrap</code>
	 * @return Url
	 */
	@RequestMapping("/forward/{boxType}")
	public String forwardInstationMsgList(@PathVariable(value = "boxType") String boxType){
		// 根据消息箱类型跳转至对应页面
		if(McConstants.INSTATIONMSG_IN_BOX.equals(boxType)){
			// 收件箱
			return "mc/instationmessage/inbox/msg_inbox_query";
		}else if(McConstants.INSTATIONMSG_OUT_BOX.equals(boxType)){
			// 发件箱
			return "mc/instationmessage/outbox/msg_outbox_query";
		}else if(McConstants.INSTATIONMSG_DRAFT_BOX.equals(boxType)){
			// 草稿箱
			return "mc/instationmessage/draftbox/msg_draft_query";
		}else if(McConstants.INSTATIONMSG_SCRAP_BOX.equals(boxType)){
			// 废件箱
			return "mc/instationmessage/scrapbox/msg_scrapbox_query";
		}else{
			// 未匹配类型，返回空
			return "";
		}
	}
	
	/**
	 * 初始化展示消息列表方法（查询方法需要修改）
	 * @param parameters 查询参数
	 * @return envelopeMap 信封对象
	 */
	@RequestMapping("/instationMsgList")
	@ResponseBody
	public Map<String, Object> getInMessageList(@RequestBody Map<String, String> parameters) {
		Map<String,Object> envelopeMap = new HashMap<String, Object>();
		List<Envelope> envelopeList = envelopeService.findList(parameters);
		envelopeMap.put("data", envelopeList);
		// 获取总记录条数
		int total = PageUtil.getTotalCount();
		envelopeMap.put("total", total != -1 ? total : envelopeList.size());
		return envelopeMap;
	}
	
	/**
     * 根据主题、发件人、收件人或日期、信封状态查找用户
     *
     * @param map key分别为： 
     *              <code>messageTopic<code>[消息主题]
     *              <code>senderName<code>[发件人]
     *              <code>receiverName<code>[收件人]
     *              <code>receiveState<code>[收件状态]
     *              <code>sendTimeFrom<code>[发送时间]
     *              <code>sendTimeTo<code>[发送时间]
     * @return Map key分别为：
     *              <code>total<code>[总记录条数] 
     *              <code>data<code>[用户信息列表], List 内容为 User
     * 
     */
    @RequestMapping("/query")
    @ResponseBody
    public Map<String, Object> getByParams(@RequestBody Map<String, String> parameters) {
        Map<String, Object> envelopedata = new HashMap<String, Object>();
        List<Envelope> envelopes = new ArrayList<Envelope>();
        envelopes = envelopeService.getByParams(parameters);
        envelopedata.put("data", envelopes);
        // 获取总记录条数
        int total = PageUtil.getTotalCount();
        envelopedata.put("total", total != -1 ? total : envelopes.size());
        return envelopedata;
    }

	 /**
     * 新建消息页面跳转方法
     * @param boxType [主键ID]
     * @return ModelAndView 
     */
    @RequestMapping("/newMessage")
    public ModelAndView newMessage(@RequestParam(value = "boxType", required = false) String boxType) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("boxType", boxType);
        return new ModelAndView("mc/instationmessage/newmessage/msg_create", model);
    }
    
    /**
     * 回复消息初始化页面数据方法
     * @param messageId 消息主键ID
     * @param loginId 当前登录人OrangeId
     * @return messageView对象
     */
    @RequestMapping("/replyMessage/{messageId}/{loginId}/{boxType}")
    @ResponseBody
    public MessageView replayMessage(@PathVariable(value = "messageId") String messageId,
    		@PathVariable(value="loginId") String loginId,
    		@PathVariable(value="boxType") String boxType){
    	// 新建页面视图对象
    	MessageView messageView = new MessageView();
    	// 判断messageId是否为空
    	if(messageId != null && !"".equals(messageId)){
    		// messageId不为空，获取组装视图对象的Envelope对象和Message对象
    		Envelope envelope = envelopeService.findEnvelopeByMessageIdAndLoginId(messageId, loginId, boxType);
    		Message message = messageService.findMessageById(messageId);
    		// 构建回复页面视图对象
    		messageView = messageBuilderService.builderReplyMessage(message, envelope);
    		// 判断是否为未读
    		if(envelope.getReceiveState() == null || "0".equals(envelope.getReceiveState())){
    			// 未读状态：更新消息状态
    			envelope.setReadTime(new Date());
        		envelope.setReceiveState("1");
        		envelopeService.updateEnvelope(envelope);
    		}
    	}
    	return messageView;
    }
    
    /**
     * 转发消息初始化页面数据方法
     * @param messageId 消息主键ID
     * @param loginId 当前登录人OrangeId
     * @return messageView对象
     */
    @RequestMapping("/forwardMessage/{messageId}/{loginId}/{boxType}")
    @ResponseBody
    public MessageView forwardMessage(@PathVariable(value = "messageId") String messageId,
    		@PathVariable(value="loginId") String loginId,
    		@PathVariable(value="boxType") String boxType){
    	// 新建页面视图对象 
    	MessageView messageView = new MessageView();
    	// 判断messageId是否为空
    	if(messageId != null && !"".equals(messageId)){
    		// messageId不为空，获取组装视图对象的Envelope对象和Message对象
    		Envelope envelope = envelopeService.findEnvelopeByMessageIdAndLoginId(messageId, loginId, boxType);
    		Message message = messageService.findMessageById(messageId);
    		// 构建转发页面视图对象
    		messageView = messageBuilderService.builderForwardMessage(message, envelope);
    		// 判断是否为未读
    		if(envelope.getReceiveState() == null || "0".equals(envelope.getReceiveState())){
    			// 未读状态：更新消息状态
    			envelope.setReadTime(new Date());
        		envelope.setReceiveState("1");
        		envelopeService.updateEnvelope(envelope);
    		}
    	}
    	return messageView;
    }
    
    /**
     * 查看消息加载数据方法
     * @param messageId 消息ID
     * @param loginId 登录人ID
     * @return MessageView {Object}
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
    		Envelope envelope = envelopeService.findEnvelopeByMessageIdAndLoginId(messageId, loginId, boxType);
    		Message message = messageService.findMessageById(messageId);
    		// 构建页面展示消息对象
    		messageView = messageBuilderService.builderViewMessage(message, envelope);
    		// 判断是否为未读
    		if(envelope.getReceiveState() == null || "0".equals(envelope.getReceiveState())){
    			// 未读状态：更新消息状态
    			envelope.setReadTime(new Date());
        		envelope.setReceiveState("1");
        		envelopeService.updateEnvelope(envelope);
    		}
    	}
    	return messageView;
    }
    
    /**
     * 展示消息内容方法
     * <p>用途：编辑/查看/转发等操作时，控制页面跳转</p>
     * @param messageId 消息ID
     * @param type 类型：
     * 				 <code>edit：编辑</code>
     * 				 <code>forward：转发</code>
     * 				 <code>reply：回复</code>
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
    	
    	if(McConstants.OPER_CODE_EDIT.equals(operType)){
    		// 编辑
    		model.put("operType", operType);
    		return new ModelAndView("mc/instationmessage/newmessage/msg_create", model);
    	}else if(McConstants.OPER_CODE_FORWARD.equals(operType)){
    		// 转发
    		model.put("operType", operType);
    		return new ModelAndView("mc/instationmessage/newmessage/msg_create", model);
    	}else if(McConstants.OPER_CODE_REPLY.equals(operType)){
    		// 回复
    		model.put("operType", operType);
    		return new ModelAndView("mc/instationmessage/newmessage/msg_create", model);
    	}else if(McConstants.OPER_CODE_VIEW.equals(operType)){
    		// 查看
    		return new ModelAndView("mc/instationmessage/newmessage/msg_view", model);
    	}else{
    		return new ModelAndView("", model);
    	}
    }
    
    /**
     * 站内消息发送方法
     * 
     * @param messageView 消息视图对象
     * 
     * @return model对象{成功；失败}
     * 
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendInstationMsg(MessageView messageView) {
    	// 解析视图消息对象
    	ResultMap parserResultMap = messageParserService.instationMsgParser(messageView);
    	Map<String, Object> model = new HashMap<String, Object>();
    	if(parserResultMap.isSuccess()){
    		// 解析成功
    		MessageObject messageObject = (MessageObject)parserResultMap.get("messageObject");
    		// 消息转发
    		ResultMap dispatcherResultMap = messageDispatcherService.messageDispatcher(messageObject);
    		if(dispatcherResultMap.isSuccess()){
    			// 转发成功
    	        model.put("success", true);
    		}else{
    			// 转发失败
    			model.put("success", false);
    		}
    	}else{
    		// 解析出错
    		model.put("success", false);
    	}
    	return model;
    } 
    
    /**
     * 新增、修改消息的异步保存操作
     * 
     * @param MessageView  消息视图对象
     * 
     * @return model  对象{成功；失败}
     * 
     */
    @RequestMapping(value = "/ajaxsave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxSave(MessageView messageView) {
    	List<Envelope> envelopeList = null;
    	String messageId = null;
    	Message message = new Message();
    	Boolean isNewMessage = null;
    	
    	if (messageView.getEnvelopeId() != "" && messageView.getEnvelopeId() != null) {
    		isNewMessage = false;
    	} else {
    		isNewMessage = true;
    	}
    	
    	ResultMap parserResultMap = messageParserService.instationMsgParser(messageView);
    	MessageObject messageObject = (MessageObject) parserResultMap.get("messageObject");
    	
    	if (isNewMessage) {
    		//新增
    		message = messageObject.getMessage();
    		//messageService.insertMessage(message);
    		envelopeList = messageObject.getEnvelopeList();
    		envelopeService.batchInsertEnvelope(envelopeList, message);
    	} else {
    		message = messageObject.getMessage();
        	messageId = messageObject.getMessage().getId();
        	envelopeList = messageObject.getEnvelopeList();
        	//先删除
        	envelopeService.physicalDelete(envelopeList, messageId);
    		//再插入
        	envelopeService.batchInsertEnvelope(envelopeList, message);
    	}
    	
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("success", true);
        return model;
    }

    /**
     * 编辑消息
     * @param id
     * @return ModelAndView
     */
	@RequestMapping(value = "/editMessage/{messageId}/{loginId}")
    public ModelAndView editNewDraft(@PathVariable(value = "messageId") String messageId,
    						@PathVariable(value = "loginId") String loginId){
    	MessageView messageView = new MessageView();
    	if(messageId != null && !"".equals(messageId)){
    		List<Envelope> envelopeList = envelopeService.findEnvelopeListByMessageId(messageId);
    		Message message = messageService.findMessageById(messageId);
    		messageView = messageBuilderService.builderMessage(message, envelopeList);
    	}
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("messageView", messageView);
    	return new ModelAndView("mc/instationmessage/newmessage/msg_create",model);
    }

	/**
	 * 逻辑删除消息
	 * @param ids
	 * @param boxType
	 * @return model  对象{成功；失败}
	 */
	@RequestMapping(value = "/delete/{ids}/{boxType}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("ids") String ids,@PathVariable("boxType") String boxType){
    	Map<String, Object> envelopeMap = new HashMap<String, Object>(); 
    	if (ids != null) {
             String[] idArray = ids.split(",");
    		 List<Envelope> envelopeList = new ArrayList<Envelope>();
    		 for(int i = 0;i < idArray.length;i++){
    			 Envelope temp = envelopeService.findEnvelopeById(idArray[i]);
    			 envelopeList.add(temp);
    		 }
    		 envelopeMap.put("envelopeList", envelopeList);
    		 envelopeMap.put("boxType", boxType);
             envelopeService.delete(envelopeMap);
         }
    	 Map<String, Object> model = new HashMap<String, Object>();
    	 model.put("success", true);
         return model;
    }
}
