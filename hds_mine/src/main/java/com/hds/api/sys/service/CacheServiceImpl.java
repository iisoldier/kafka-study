package com.hds.api.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hds.api.sys.dao.CacheDao;
import com.hds.api.sys.vo.HDSApi;
import com.hds.api.sys.vo.HDSApiSys;
import com.hds.api.sys.vo.HDSSys;
import com.hds.api.sys.vo.HDSIPWhiteList;

/**
* @ClassName:com.hds.api.sys.service.impl.CacheServiceImpl
* @Description: cache服务实现类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:49:23
 */
@Service("CacheService")
public class CacheServiceImpl implements CacheService {

	@Autowired
	private CacheDao cacheDao;

    /**
     *   查询HDS接入的系统信息
     */
	@Override
	public List<HDSSys> getHDSSysList() {
		// TODO Auto-generated method stub
		List<HDSSys> syslist = cacheDao.getHDSSysList();
		return syslist;
	}
	
    /**
     *   查询HDS的接口信息
     */
	@Override
	public List<HDSApi> getHDSAPIList() {
		// TODO Auto-generated method stub
		List<HDSApi> apilist = cacheDao.getHDSAPIList();
		return apilist;
	}
	
    /**
     *   查询HDS接入系统的IP白名单信息
     */
	@Override
	public List<HDSIPWhiteList> getHDSIPWhiteList() {
		// TODO Auto-generated method stub
		List<HDSIPWhiteList> iplist = cacheDao.getHDSIPWhiteList();
		return iplist;
	}

    /**
     *   查询HDS接入系统的接口权限信息
     */
	@Override
	public List<HDSApiSys> getHDSApiSysList() {
		// TODO Auto-generated method stub		
		List<HDSApiSys> aslist = cacheDao.getHDSApiSysList();
		return aslist;
	}
}
