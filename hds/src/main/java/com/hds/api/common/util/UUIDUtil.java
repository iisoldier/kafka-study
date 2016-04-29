package com.hds.api.common.util;

import java.util.UUID;

/**
* @ClassName:com.hds.api.common.util.UUIDUtil
* @Description: UUID生成方法
* @author zengli
* @date 创建时间：2016年4月17日 下午4:24:32
 */
public class UUIDUtil {
	public UUIDUtil() {
	}

	/**
	* @method  getUUIDString
	* @Description: 获取UUID 包括 "-" 符号
	* @author  zengli
	* @date 2016年4月17日 下午4:25:15
	* @parameter  
	* @return  String
	 */
	public static String getUUIDString() {
		return UUID.randomUUID().toString();
	}

	/**
	* @method  get32BitUUIDString
	* @Description: 获取32位UUID 去除 "-" 符号
	* @author  zengli
	* @date 2016年4月17日 下午4:24:50
	* @parameter  
	* @return  String
	 */
	public static String get32BitUUIDString() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	// 获得指定数量的UUID
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = get32BitUUIDString();
		}
		return ss;
	}

	public static void main(String[] args) {
		String[] ss = getUUID(10);
		for (int i = 0; i < ss.length; i++) {
			System.out.println("ss[" + i + "]=====" + ss[i] + "------"
					+ ss[i].length());
		}
	}
}