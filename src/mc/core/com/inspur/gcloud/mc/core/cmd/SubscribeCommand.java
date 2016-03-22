package com.inspur.gcloud.mc.core.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.loushang.framework.mybatis.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inspur.gcloud.mc.core.data.Envelope;
import com.inspur.gcloud.mc.core.data.Message;
import com.inspur.gcloud.mc.core.data.Subscribe;
import com.inspur.gcloud.mc.core.service.ISubscribeService;
/**
 * Controller层，用于前后台交互、前后台数据格式转换
 * 订阅Command
 * @author Howard
 *
 */
@Controller
@RequestMapping(value = "/mc/subscribe")
public class SubscribeCommand {
	@Autowired
	private ISubscribeService subscribeService;
	
    /**
     * 跳转到列表页
     * 
     * @return  订阅列表
     */
    @RequestMapping
    public String querySubscribe() {
        return "mc/messagesubscribe/subscribe_query";
    }
	
	@RequestMapping("/inSubscribeList")
	@ResponseBody
	public Map getInSubscribeList(@RequestBody Map<String,Object> parameters){
		Map<String,Object> subscribeData = new HashMap<String,Object>();
		List<Subscribe> subscrivbes = subscribeService.findAll(parameters);
		subscribeData.put("data", subscrivbes);
		//获取记录总条数
		int total = PageUtil.getTotalCount();
		subscribeData.put("total", total != -1 ? total : subscrivbes.size());
		
		return subscribeData;
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public 	Map<String,Object> getByParams(@RequestBody Map<String,String> parameters){
		Map<String, Object> subscribedata = new HashMap<String, Object>();
		List<Subscribe> subscribes = new ArrayList<Subscribe>();
		subscribes = subscribeService.getByParams(parameters);
		subscribedata.put("data", subscribes);
		
        int total = PageUtil.getTotalCount();
        subscribedata.put("total", total != -1 ? total : subscribes.size());
        return subscribedata;
	}
	
	
    /**
     * 新增、修改订阅的保存操作
     * 
     * @param 
     * 
     * @return 订阅列表页面路径
     * 
     */
    @RequestMapping(value = "/save")
    public String saveSubscribe(Subscribe subscribe) {
    	subscribeService.save(subscribe);
    	return "redirect:/command/mc/subscribe";
    } 
}
