package com.hds.api.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import com.hds.api.sys.service.LoadCacheService;

/**
* @ClassName:com.hds.api.common.util.ManagerBeanPostProcessor
* @Description: 实现BeanPostProcessor接口，可以实现在Spring加载时，调取一个的对象的方法，使其能够取得实例化以后的DAO.
* @author zengli
* @date 创建时间：2016年4月17日 下午4:18:10
 */
public class ManagerBeanPostProcessor implements BeanPostProcessor {
	public Object postProcessAfterInitialization(Object obj, String s)
			throws BeansException {
		// StartLoadCacheService为需要初始化的类名称
		if (obj instanceof LoadCacheService) {
			// loadAllData为StartLoadCacheService中的数据加载的方法
			//((StartLoadCacheService) obj).loadAllData();
			((LoadCacheService) obj).startLoadData();
		}
		return obj;
	}

	public Object postProcessBeforeInitialization(Object obj, String s)
			throws BeansException {
		return obj;
	}
}
