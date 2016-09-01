package com.inspur.gcloud.mc.core.cmd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * <p>Title: 站内邮件Controller层</p>
 * <p>Description: 用于前后台交互、前后台数据格式转换</p>
 * @author h.song
 * @date 2016年5月24日 下午3:18:20
 */
@Controller
@RequestMapping(value="/mc/core/instationmail")
public class InstationMailCommand {
	
	/**
	 * 初始化查询站内邮件方法
	 * @param parameters 查询条件Map Key为：
	 * 					<code></code>
	 * 					<code></code>
	 * @return instationMailMap Key为：
	 * 					<code></code>
	 * 					<code></code>
	 */
	@RequestMapping(value="instationMailList")
	@ResponseBody
	public Map<String, Object> getInstationMailList(Map<String, String> parameters){
		Map<String, Object> instationMailMap = new HashMap<String, Object>();
		
		
		return instationMailMap;
	}

}
