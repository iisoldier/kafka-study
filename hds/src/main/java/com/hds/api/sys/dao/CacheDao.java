package com.hds.api.sys.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.hds.api.sys.vo.HDSApi;
import com.hds.api.sys.vo.HDSApiSys;
import com.hds.api.sys.vo.HDSSys;
import com.hds.api.sys.vo.HDSIPWhiteList;

/**
 * @ClassName:com.hds.api.sys.dao.CacheDao
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月17日 下午4:33:12
 */
public interface CacheDao {

	/**
	 * @method getHDSSysList
	 * @Description: cache加载数据   获取接入系统Dao层接口
	 * @author zengli
	 * @date 2016年4月17日 下午4:33:16
	 * @parameter
	 * @return List<HDSSys>
	 */
	public List<HDSSys> getHDSSysList() throws DataAccessException;

	/**
	 * @method getHDSAPIList
	 * @Description:  cache加载数据   获取接口列表Dao层接口
	 * @author zengli
	 * @date 2016年4月17日 下午4:33:23
	 * @parameter
	 * @return List<HDSApi>
	 */
	public List<HDSApi> getHDSAPIList() throws DataAccessException;

	/**
	 * @method getHDSIPWhiteList
	 * @Description:  cache加载数据   获取系统ip白名单Dao层接口
	 * @author zengli
	 * @date 2016年4月17日 下午4:33:32
	 * @parameter
	 * @return List<HDSIPWhiteList>
	 */
	public List<HDSIPWhiteList> getHDSIPWhiteList() throws DataAccessException;

	/**
	 * @method getHDSApiSysList
	 * @Description: cache加载数据   获取系统接口权限Dao层接口
	 * @author zengli
	 * @date 2016年4月17日 下午4:33:40
	 * @parameter
	 * @return List<HDSApiSys>
	 */
	public List<HDSApiSys> getHDSApiSysList() throws DataAccessException;

}
