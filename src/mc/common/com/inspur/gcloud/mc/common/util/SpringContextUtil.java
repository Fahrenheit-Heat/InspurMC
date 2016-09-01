package com.inspur.gcloud.mc.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * <p>Title: Spring容器工具类</p>
 * <p>Description: 用于通过spring容器获取bean</p>
 * @author h.song
 * @date 2016年6月20日 下午9:57:23
 * @vision 1.0
 */
@Service("springContextUtil")
public class SpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}
	
	/**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
    public static Object getBeanByClass(Class<?> c){
        return applicationContext.getBean(c);
    }

}
