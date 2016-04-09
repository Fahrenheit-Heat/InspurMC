package com.inspur.gcloud.mc.core.cmd;

import java.util.ArrayList;
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
 * Controller层，用于前后台交互、前后台数据格式转换
 * 信封Command
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
	
	@RequestMapping("/forward")
	public String forwardInstationMsgList(){
		return "mc/instationmessage/inbox/msg_inbox_query";
	}
	
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
     * 用户修改页面的弹出
     * 
     * @param id [主键ID]
     * 
     * @return Map key为
     *          <code>user<code>[User对象]
     * 
     */
    @RequestMapping("/newMessage")
    public ModelAndView newMessage(@RequestParam(value = "id", required = false) String id) {
    	Envelope envelope = null;
    	if(id != null && !"".equals(id)){
    		envelope = envelopeService.findEnvelopeById(id);
    		if(envelope != null){
    			String messageId = envelopeService.findMessageId(id);
    			Message temp = messageService.findMessageById(messageId);
    			envelope.setMessage(temp);
    		}
    	}
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("envelope", envelope);
        return new ModelAndView("mc/instationmessage/newmessage/msg_create", model);
    }
    
    
    @RequestMapping("/replayMessage")
    public ModelAndView replayMessage(@RequestParam(value = "messageId", required = false) String messageId){
    	MessageView messageView = new MessageView();
    	if(messageId != null && !"".equals(messageId)){
    		Map<String, Object> paramterMap = new HashMap<String, Object>();
    		paramterMap.put("messageId", messageId);
    		List<Envelope> envelopeList = envelopeService.findEnvelopeListByMessageId(messageId);
    		Message message = messageService.findMessageById(messageId);
    		messageView = messageBuilderService.builderReplyMessage(message, envelopeList);
    	}
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("messageView", messageView);
    	return new ModelAndView("mc/instationmessage/newmessage/msg_create", model);
    }
    
    @RequestMapping("/forwardMessage")
    public ModelAndView forwardMessage(@RequestParam(value = "messageId", required = true) String messageId){
    	MessageView messageView = new MessageView();
    	if(messageId != null && !"".equals(messageId)){
    		Map<String, Object> paramterMap = new HashMap<String, Object>();
    		paramterMap.put("messageId", messageId);
    		List<Envelope> envelopeList = envelopeService.findEnvelopeListByMessageId(messageId);
    		Message message = messageService.findMessageById(messageId);
    		messageView = messageBuilderService.builderForwardMessage(message, envelopeList);
    	}
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("messageView", messageView);
    	return new ModelAndView("mc/instationmessage/newmessage/msg_create", model);
    }
	
	
    
    /**
     * 新增、修改用户的保存操作
     * 
     * @param user
     * 
     * @return 用户列表页面路径
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
     * @param message
     * 
     * @return 用户列表页面路径
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
	@RequestMapping(value = "/edit")
    public ModelAndView editNewDraft(@RequestParam(value = "messageId",required = true)String messageId){
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
    
	@RequestMapping(value = "/delete/{ids}/type/{boxType}",method = RequestMethod.POST)
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
