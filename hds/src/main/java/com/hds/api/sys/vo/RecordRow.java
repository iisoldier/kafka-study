/** 
 * @ClassName:com.hds.api.sys.vo
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月22日 下午3:37:12
 */
package com.hds.api.sys.vo;

import java.util.Map;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @ClassName:com.hds.api.sys.vo.RecordRow
 * @Description: 单行数据
 * @author zengli
 * @date 创建时间：2016年4月22日 下午3:37:12
 */
@JSONType(orders={"row"})
public class RecordRow {
	private Map<String, String> row;

	public Map<String, String> getRow() {
		return row;
	}

	public void setRow(Map<String, String> row) {
		this.row = row;
	}

	
	
}
