package com.inspur.gcloud.mc.core.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

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
import com.inspur.gcloud.mc.core.data.Subscribe;
import com.inspur.gcloud.mc.core.service.IEnvelopeService;
import com.inspur.gcloud.mc.core.service.IMessageService;
import com.inspur.gcloud.mc.engine.dispatcher.service.IMessageDispatcherService;
import com.inspur.gcloud.mc.engine.parser.service.IMessageParserService;


/**
 * Controller层，用于前后台交互、前后台数据格式转换
 * 信封Command
 * @author ZXh
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
    		envelope = envelopeService.findOne(id);
    		if(envelope != null){
    			String messageId = envelopeService.findMessageId(id);
    			Message temp = messageService.findOne(messageId);
    			envelope.setMessage(temp);
    		}
    	}
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("envelope", envelope);
        return new ModelAndView("mc/instationmessage/newmessage/msg_create", model);
    }
    
    
    @RequestMapping("/replayMessage")
    public ModelAndView replayMessage(@RequestParam(value = "id", required = true) String id){
    	
    	return new ModelAndView("", null);
    }
    
    @RequestMapping("/forwardMessage")
    public ModelAndView forwardMessage(@RequestParam(value = "id", required = true) String id){
    	
    	return new ModelAndView("", null);
    }
	
	
    
    /**
     * 新增、修改用户的保存操作
     * 
     * @param user
     * 
     * @return 用户列表页面路径
     * 
     */
    @RequestMapping(value = "/send")
    public String sendInstationMsg(MessageView messageView) {
    	// 解析视图消息对象
    	ResultMap parserResultMap = messageParserService.instationMsgParser(messageView);
    	if(parserResultMap.isSuccess()){
    		// 解析成功
    		MessageObject messageObject = (MessageObject)parserResultMap.get("messageObject");
    		// 消息转发
    		ResultMap dispatcherResultMap = messageDispatcherService.messageDispatcher(messageObject);
    		if(dispatcherResultMap.isSuccess()){
    			// 转发成功
    			
    		}else{
    			// 转发失败
    		}
    	}else{
    		// 解析出错
    	}
        //页面重定向
        return "redirect:/command/mc/core";
    } 
    
    /**
     * 修改用户的异步保存操作
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
    	ResultMap parserResultMap = messageParserService.instationMsgParser(messageView);
    	MessageObject messageObject = (MessageObject) parserResultMap.get("messageObject");
    	messageService.updateMessage(messageObject.getMessage());
    	messageId = messageObject.getMessage().getId();
    	envelopeList = messageObject.getEnvelopeList();
    	envelopeService.batchUpdateEnvelope(envelopeList, messageId);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("success", true);
        return model;
    }
    
    /**
     * 新增用户的异步保存操作
     * 
     * @param message
     * 
     * @return 用户列表页面路径
     * 
     */
    @RequestMapping(value = "/ajaxnewsave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxNewSave(MessageView messageView) {
    	List<Envelope> envelopeList = null;
    	String messageId = null;
    	ResultMap parserResultMap = messageParserService.instationMsgParser(messageView);
    	MessageObject messageObject = (MessageObject) parserResultMap.get("messageObject");
    	messageService.updateMessage(messageObject.getMessage());
    	messageId = messageObject.getMessage().getId();
    	envelopeList = messageObject.getEnvelopeList();
    	envelopeService.batchSaveEnvelope(envelopeList, messageId);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("success", true);
        return model;
    }

    /**
     * 编辑草稿消息
     * @param id
     * @return ModelAndView
     */
	@RequestMapping(value = "/editdraft")
    public ModelAndView editNewDraft(@RequestParam(value = "id",required = false)String id){
    	MessageView messageView = new MessageView();
    	Envelope envelope = null;
    	if(id != null && !"".equals(id)){
    		envelope = envelopeService.findOne(id);
    		if(envelope != null){
    			String messageId = envelopeService.findMessageId(id);
    			Message message = messageService.findOne(messageId);
    			envelope.setMessage(message);
    		}
    		messageView = makeUpMessageView(envelope);
    	}
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("messageView", messageView);
    	return new ModelAndView("mc/instationmessage/newmessage/msg_create",model);
    }
	
	/**
	 * 用于组装对编辑页面进行展示的MessageView
	 * @param envelope
	 * @return MessageView
	 */
	public MessageView makeUpMessageView(Envelope envelope){
		MessageView messageView = new MessageView();
		messageView.setEnvelopeId(envelope.getId());
		messageView.setMessageId(envelope.getMessageId());
		messageView.setMessageTopic(envelope.getMessage().getMessageTopic());
		messageView.setMessageType(envelope.getMessageType());
		messageView.setSenderId(envelope.getSenderId());
		messageView.setSenderName(envelope.getSenderName());
		messageView.setReceiverId(envelope.getReceiverId());
		messageView.setReceiverName(envelope.getReceiverName());
		messageView.setIsSchedule(envelope.getIsSchedule());
		messageView.setIsReadReceipt(envelope.getIsReadReceipt());
		messageView.setSendType(envelope.getSendType());
		messageView.setSendState(envelope.getSendState());
		messageView.setMessageLevel(envelope.getMessage().getMessageLevel());
		messageView.setMessageContent(envelope.getMessage().getMessageContent());
		return messageView;
	}
    
    @RequestMapping("/delete/{ids,boxType}")
    public String delete(@PathVariable String ids, String boxType){
    	 if (ids != null) {
             String[] idArray = ids.split(",");
    		 Map<String, String> envelopeMap = new HashMap<String, String>();
             for(int i = 0; i < idArray.length; i++){
            	 envelopeMap.put("id", idArray[i]);
            	 envelopeMap.put("boxType", boxType);
             }
             envelopeService.delete(envelopeMap);;
         }
         return "redirect:/command/mc/core";
    }
}
