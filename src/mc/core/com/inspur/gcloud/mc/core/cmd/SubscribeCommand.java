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

import com.inspur.gcloud.mc.core.data.Subscribe;
import com.inspur.gcloud.mc.core.service.ISubscribeService;
/**
 * Controller层，用于前后台交互、前后台数据格式转换
 * 订阅Command
 * @author Howard
 *
 */
@Controller
@RequestMapping(value = "/mc/core/subscribe")
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
    	return "redirect:/command/mc/core/subscribe/inSubscribeList";
    } 
    
    /**
     * 新增、修改订阅的异步保存操作
     * 
     * @param subscribe
     * 
     * @return 订阅列表页面路径
     * 
     */
    @RequestMapping(value = "/ajaxsave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxSaveSubscribe(Subscribe subscribe) {
    	subscribeService.save(subscribe);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("success", true);
        return model;
    }
    
    @RequestMapping(value = "/edit")
    public ModelAndView editpage(@RequestParam(value = "id",required = false)String id){
    	Subscribe subscribe = null;
    	if(id != null && !"".equals(id)){
    		subscribe = subscribeService.findOne(id);
    	}
    	 Map<String, Object> model = new HashMap<String, Object>();
    	 model.put("subscribe", subscribe);
    	 return new ModelAndView("mc/messagesubscribe/subscribe_edit",model);
    }
    
    /**
     * 订阅异步删除操作
     * 
     * @param ids [主键ID数组]
     * 
     * @return 订阅页面路径
     * 
     */
    @RequestMapping(value = "/ajaxdelete/{ids}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxDeleteSubscribe(@PathVariable String ids) {
    	if (ids != null) {
            String[] idArray = ids.split(",");
            subscribeService.delete(idArray);
        }
    	
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("success", true);
        return model;
    } 
}
