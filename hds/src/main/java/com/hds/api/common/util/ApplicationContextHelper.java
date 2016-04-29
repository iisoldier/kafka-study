package com.hds.api.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
* @ClassName:com.hds.api.common.util.ApplicationContextHelper
* @Description: 从context中获取spring xml配置文件中的初始化对象 
* @author zengli
* @date 创建时间：2016年4月17日 下午4:08:48
 */
public class ApplicationContextHelper implements ApplicationContextAware {
	 private static ApplicationContext context;
	 @SuppressWarnings("static-access")
	 @Override
	 public void setApplicationContext(ApplicationContext applicationContext)
	   throws BeansException {
	  this.context = applicationContext;
	 }
	 public static Object getObject(String id) {
	  Object object = null;
	  object = context.getBean(id);
	  return object;
	 }
	}