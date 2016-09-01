package com.inspur.gcloud.mc.common.util;

import java.util.Date;

import com.inspur.gcloud.logcenter.primary.data.PrimaryItem;
import com.inspur.gcloud.logcenter.util.GCloudLogUtil;
import com.inspur.gcloud.mc.common.McSystemConstants;
import com.inspur.gcloud.sc.api.GCloudSCUtil;
import com.inspur.hsf.service.common.utils.ServiceConfigUtils;
import com.lc.gcloud.framework.util.GCloudUtil;

/**
 * <p>Title: 消息服务日志集成日志中心日志打印工具类</p>
 * <p>Description: 用于消息服务向日志中心记录日志</p>
 * @author h.song
 * @date 2016年6月28日 上午11:12:03
 * @vision 1.0
 */
public class McLoggerUtil {
	
	private final static String LOGGER_TYPE_BIZ = "10";
	
	private final static String LOGGER_TYPE_LOG4J = "20";
	
	private final static String LOGGER_TYPE_ANALYSIS = "30";
	
	// 获取日志中心工具对象
	private GCloudLogUtil logUtil = GCloudLogUtil.getInstance(McSystemConstants.MESSAGE_CENTER_SYSTEM_CODE);
	
	private static McLoggerUtil instance = new McLoggerUtil();
	
	private McLoggerUtil(){}
	
	/**
	 * 单利模式：获取唯一实例
	 * @return
	 */
	public static McLoggerUtil getInstance(){
		return instance;
	}
	
	/**
	 * 记录系统错误日志方法
	 * @param serialNo 业务主键ID
	 * @param logState 日志状态
	 * @param content 日志内容
	 */
	public void systemLog(String serialNo, String logState, String content){
		
		String logSwitch =  GCloudSCUtil.getInstance().getParameter("Mc.logSwitch");
		
		// 通过参数中心获取日志开关
		boolean loggerSwitch = Boolean.parseBoolean(logSwitch);
		
		if(loggerSwitch){
			// 定义日志条目对象
			PrimaryItem item = new PrimaryItem();
			
			// 设置日志条目对象内容
			// 设置主键ID
			item.setId(GCloudUtil.getInstance().getNextSeqId(32));
			// 设置日志类型 技术分析
			item.setLogType(LOGGER_TYPE_ANALYSIS);
			// 设置用户ID 系统内部日志默认为系统表示编码
			item.setOrganId(McSystemConstants.MESSAGE_CENTER_SYSTEM_CODE);
			// 设置用户名 系统内部日志默认为系统表示编码
			item.setOrganName(McSystemConstants.MESSAGE_CENTER_SYSTEM_CODE);
			// 设置当前系统时间
			item.setLogTime(new Date());
			// 设置系统状态码
			item.setState(logState);
			// 设置业务主键
			item.setSerialNo(serialNo);
			// 设置应用实例编码 以高速服务发布端口号代替应用实例编码
			item.setAppCode(String.valueOf(ServiceConfigUtils.getServicePort()));
			// 设置日志内容
			item.setContent(content);
			
			logUtil.append(item);
		}
		
	}
	

	/**
	 * 记录业务系统日志方法
	 * @param serialNo 业务主键ID
	 * @param appModule 业务模块
	 * @param logState 日志状态
	 * @param content 日志内容
	 */
	public void bizLog(String serialNo, String appModule, String logState, String content){
		
		String logSwitch =  GCloudSCUtil.getInstance().getParameter("Mc.logSwitch");
		
		// 通过参数中心获取日志开关
		boolean loggerSwitch = Boolean.parseBoolean(logSwitch);
		
		if(loggerSwitch){
			// 定义日志条目对象
			PrimaryItem item = new PrimaryItem();
			
			// 设置日志条目对象内容
			// 设置主键ID
			item.setId(GCloudUtil.getInstance().getNextSeqId(32));
			// 设置日志类型 技术分析
			item.setLogType(LOGGER_TYPE_BIZ);
			// 设置用户ID 系统内部日志默认为系统表示编码
			item.setOrganId(appModule);
			// 设置用户名 系统内部日志默认为系统表示编码
			item.setOrganName(appModule);
			// 设置当前系统时间
			item.setLogTime(new Date());
			// 设置系统状态码
			item.setState(logState);
			// 设置业务主键
			item.setSerialNo(serialNo);
			// 设置应用实例编码 以高速服务发布端口号代替应用实例编码
			item.setAppCode(ServiceConfigUtils.SERVICE_PORT);
			// 设置日志内容
			item.setContent(content);
			
			logUtil.append(item);
		}
		
	}
	
}
