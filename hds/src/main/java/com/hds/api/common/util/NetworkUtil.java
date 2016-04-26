package com.hds.api.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * @ClassName:com.hds.api.common.util.NetworkUtil
 * @Description: 网络相关工具类
 * @author zengli
 * @date 创建时间：2016年4月17日 下午4:19:36
 */
public final class NetworkUtil {

	private static Logger logger = Logger.getLogger(NetworkUtil.class);

	/**
	 * @method getIpAddress
	 * @Description: 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * @author zengli
	 * @date 2016年4月17日 下午4:20:19
	 * @parameter request
	 * @return String
	 * @throws IOException
	 */
	public final static String getIpAddress(HttpServletRequest request) {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
		String ip = request.getHeader("X-Forwarded-For");
		if (logger.isInfoEnabled()) {
			logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip="
					+ ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip="
							+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip="
							+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip="
							+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip="
							+ ip);
				}
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip="
							+ ip);
				}
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}

	/**
	 * @method getRemoteAddr
	 * @Description:获取客户端IP地址 支持多级反向代理
	 * @author zengli
	 * @date 2016年4月17日 下午4:22:18
	 * @parameter HttpServletRequest request
	 * @return String 客户端真实IP地址
	 */
	public static String getRemoteAddr(final HttpServletRequest request) {
		try {
			String remoteAddr = request.getHeader("X-Forwarded-For");
			// 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，
			// 此时取X-Forwarded-For中第一个非unknown的有效IP字符串
			if (isEffective(remoteAddr) && (remoteAddr.indexOf(",") > -1)) {
				String[] array = remoteAddr.split(",");
				for (String element : array) {
					if (isEffective(element)) {
						remoteAddr = element;
						break;
					}
				}
			}
			if (!isEffective(remoteAddr)) {
				remoteAddr = request.getHeader("X-Real-IP");
			}
			if (!isEffective(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
			return remoteAddr;
		} catch (Exception e) {
			logger.error("get romote ip error,error message:" + e.getMessage());
			return "";
		}
	}

	/**
	* @method  getRemotePort
	* @Description:  获取客户端源端口
	* @author  zengli
	* @date 2016年4月17日 下午4:23:22
	* @parameter  HttpServletRequest request
	* @return  Long 
	 */
	public static Long getRemotePort(final HttpServletRequest request) {
		try {
			String port = request.getHeader("remote-port");
			if (!StringUtils.isEmpty(port)) {
				try {
					return Long.parseLong(port);
				} catch (NumberFormatException ex) {
					logger.error("convert port to long error , port: " + port);
					return 0l;
				}
			} else {
				return 0l;
			}
		} catch (Exception e) {
			logger.error("get romote port error,error message:"
					+ e.getMessage());
			return 0l;
		}
	}

	/**
	* @method  isEffective
	* @Description 远程地址是否有效
	* @author  zengli
	* @date 2016年4月17日 下午4:23:55
	* @parameter  remoteAddr 远程地址
	* @return  boolean
	*/
	private static boolean isEffective(final String remoteAddr) {
		boolean isEffective = false;
		if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
				&& (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
			isEffective = true;
		}
		return isEffective;
	}

}