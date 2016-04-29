package com.hds.api.sys.service;

import java.util.List;

import com.hds.api.sys.vo.HDSApi;
import com.hds.api.sys.vo.HDSApiSys;
import com.hds.api.sys.vo.HDSSys;
import com.hds.api.sys.vo.HDSIPWhiteList;
/**
* @ClassName:com.hds.api.sys.service.CacheService
* @Description:  cache加载数据  服务接口  用于CacheDao测试，测试完成后不加载到spring当中，前台系统可以复用
* @author zengli
* @date 创建时间：2016年4月17日 下午4:36:59
 */
public interface CacheService {

	/**
	 * @method getHDSApiSysList
	 * @Description: cache加载数据   获取系统接口权限service层接口
	 * @author zengli
	 * @date 2016年4月17日 下午4:33:40
	 * @parameter
	 * @return List<HDSApiSys>
	 */
	public List<HDSSys> getHDSSysList();
	
	/**
	* @method  getAPIList
	* @Description:cache加载数据   获取接口列表service层接口 
	* @author  zengli
	* @date 2016年4月17日 下午4:38:45
	* @parameter  
	* @return  List<HDSApi>
	 */
	public List<HDSApi>  getHDSAPIList();
	
	/**
	* @method  getHDSIPWhiteList
	* @Description: cache加载数据   获取系统ip白名单service层接口 
	* @author  zengli
	* @date 2016年4月17日 下午4:40:22
	* @parameter  
	* @return  List<HDSIPWhiteList>
	 */
	public List<HDSIPWhiteList>  getHDSIPWhiteList();

  /**
   * @method  getHDSApiSysList
   * @Description:cache加载数据   获取系统接口权限 service层接口 
   * @author  zengli
   * @date 2016年4月17日 下午4:41:00
   * @parameter  
   * @return  List<HDSApiSys>
    */
	public List<HDSApiSys> getHDSApiSysList();
	
}
