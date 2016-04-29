/** 
* @ClassName:com.hds.api.ccs.dao
* @Description:
* @author zengli
* @date 创建时间：2016年4月23日 下午4:41:37
*/
package com.hds.api.ccs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.aliyun.openservices.ots.ClientException;
import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.ServiceException;
import com.aliyun.openservices.ots.model.BatchGetRowRequest;
import com.aliyun.openservices.ots.model.BatchGetRowResult;
import com.aliyun.openservices.ots.model.BatchGetRowResult.RowStatus;
import com.aliyun.openservices.ots.model.Direction;
import com.aliyun.openservices.ots.model.GetRangeRequest;
import com.aliyun.openservices.ots.model.GetRangeResult;
import com.aliyun.openservices.ots.model.MultiRowQueryCriteria;
import com.aliyun.openservices.ots.model.PrimaryKeyValue;
import com.aliyun.openservices.ots.model.RangeRowQueryCriteria;
import com.aliyun.openservices.ots.model.Row;
import com.aliyun.openservices.ots.model.RowPrimaryKey;
import com.hds.api.ccs.vo.FenrunJourRequest;
import com.hds.api.ccs.vo.FenrunJourResponse;
import com.hds.api.common.constant.OTSConstant;
import com.hds.api.common.constant.QueryHandleConstant;
import com.hds.api.common.constant.R;
import com.hds.api.common.util.DateUtils;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.OTSConfig;
import com.hds.api.sys.vo.RecordRow;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.dao.CCSFenrunJourDaoImpl
* @Description:
* @author zengli
* @date 创建时间：2016年4月26日 下午4:59:53
 */
@Repository("CCSFenrunJourDao")
public class CCSFenrunJourDaoImpl implements CCSFenrunJourDao{

	
	private static Logger logger = Logger.getLogger(CCSFenrunJourDaoImpl.class);
	
	/**
	* @method  getFeeSettleOrder
	* @Description: getCCSFeeSettleOrder 接口实现方法  用于划付指令查询OTS
	* @author  zengli
	* @date  2016年4月23日 下午4:16:23
	* @parameter  FeeSettleOrderRequest fsoVo                 查询请求对象
	* @parameter  OTSConfig ots_config                              OTS参数缓存
	* @return  FeeSettleOrderResponse
	 * @throws HDSServiceException 
	 */
	@Override
	public FenrunJourResponse getFenrunJour(FenrunJourRequest fjVo, OTSConfig ots_config) {
		// TODO Auto-generated method stub

		OTSClient client = new OTSClient( ots_config.getAliyun_ots_endpoint(), ots_config.getAliyun_accesskey_id(), 
				ots_config.getAliyun_accesskey_secret() ,ots_config.getAliyun_ots_instance()) ;
		String index_tableName =OTSConstant.OTSTableName.FenrunJourIndex;
		String detail_tableName =OTSConstant.OTSTableName.FenrunJourDetail;

		
		//抓取OTS异常
		try {
			//------------------------------------范围查询  获取index列表-------------------------------------------------
			//索引查询条件
			RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(index_tableName);
		    
			//设置StartPrimaryKey  ---------------------------------------------------  
			RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
			//如果为空则指为无限小(初次查询)
			if(fjVo.getS_fee_sn().equals("")){
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_SN.getName(),
						 PrimaryKeyValue.INF_MIN);
			}//后续被NextPrimaryKey替代后，则可以直接进行查询
			else{
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_SN.getName(),
						 PrimaryKeyValue.fromString(fjVo.getS_fee_sn()));
			}
			inclusiveStartKey.addPrimaryKeyColumn(
					OTSConstant.FenrunJourIndexEnum.OCCUR_DATE.getName(),
					PrimaryKeyValue.fromString(fjVo.getS_occur_date()));
			inclusiveStartKey.addPrimaryKeyColumn(
					OTSConstant.FenrunJourIndexEnum.ORG_CODE.getName(),
					PrimaryKeyValue.fromString(fjVo.getS_org_code()));
			//如果为空则指为无限小
			if(fjVo.getS_fee_category1().equals("")){
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY1.getName(),
						 PrimaryKeyValue.INF_MIN);
			}else{
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY1.getName(),
						 PrimaryKeyValue.fromString(fjVo.getS_fee_category1()));
			}
			//如果为空则指为无限小
			if(fjVo.getS_fee_category2().equals("")){
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY2.getName(),
						 PrimaryKeyValue.INF_MIN);
			}else{
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY2.getName(),
						 PrimaryKeyValue.fromString(fjVo.getS_fee_category2()));
			}

		    //设置EndPrimaryKey  ---------------------------------------------------  
		    RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();  
			if(fjVo.getE_fee_sn().equals("")){
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_SN.getName(),
						 PrimaryKeyValue.INF_MAX);
			}//后续被NextPrimaryKey替代后，则可以直接进行查询
			else{
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_SN.getName(),
						 PrimaryKeyValue.fromString(fjVo.getE_fee_sn()));
				}
			exclusiveEndKey.addPrimaryKeyColumn(
					OTSConstant.FenrunJourIndexEnum.OCCUR_DATE.getName(),
					PrimaryKeyValue.fromString(fjVo.getE_occur_date()));
			exclusiveEndKey.addPrimaryKeyColumn(
					OTSConstant.FenrunJourIndexEnum.ORG_CODE.getName(),
					PrimaryKeyValue.fromString(fjVo.getE_org_code()));
			//如果为空则指为无限小
			if(fjVo.getE_fee_category1().equals("")){
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY1.getName(),
						 PrimaryKeyValue.INF_MAX);
			}else{
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY1.getName(),
						 PrimaryKeyValue.fromString(fjVo.getE_fee_category1()));
			}
			//如果为空则指为无限小
			if(fjVo.getE_fee_category2().equals("")){
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY2.getName(),
						 PrimaryKeyValue.INF_MAX);
			}else{
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY2.getName(),
						 PrimaryKeyValue.fromString(fjVo.getE_fee_category2()));
			}
			
			//设置开始结束PK，设置查询方向，设置
			criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
	        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
	        criteria.setDirection(Direction.valueOf(fjVo.getDirection()));
	        criteria.setLimit(fjVo.getLimit());
	        criteria.addColumnsToGet(new String[]{
	        		OTSConstant.MColumns.FenrunJourIndex
	        });
	        
	        GetRangeRequest gr_request = new GetRangeRequest();
	        gr_request.setRangeRowQueryCriteria(criteria);
	        GetRangeResult gr_result = client.getRange(gr_request);
	        List<Row> rows = gr_result.getRows();
	        
	        
	        //如果索引查询结果为空，则说明没有数据设置返回值
	        if(rows.size()==0){
	        	List<ResponseResult> list_rr = new ArrayList<ResponseResult>();
	        	ResponseResult rr = new  ResponseResult();
	        	rr.setType(R.SystemConstant.RESTYPE_HANDLE);
	        	rr.setResult_code(QueryHandleConstant.ResultCode.QUERY_INDEXTB_ISNULL);
	        	rr.setField(QueryHandleConstant.Field.OTS_INDEX_TABLE);
	        	rr.setMessage(QueryHandleConstant.ResultMessage.QUERY_INDEXTB_ISNULL_ERRMSG);
	        	list_rr.add(rr);
	        	
	        	//设置返回对象
	        	FenrunJourResponse frjrVo = new FenrunJourResponse();
	        	frjrVo.setRequest_id(fjVo.getRequest_id());
	        	frjrVo.setSysid(fjVo.getSysid());
	        	frjrVo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
	        	frjrVo.setRequest_param(fjVo);
	        	frjrVo.setResponse_records(null);
	        	frjrVo.setReponse_result(list_rr);
	        	return frjrVo;
	        }
	        
	        //获得索引表的查询结果，明细表的pk list
	        List<String> list_index= new ArrayList<String>();
	        for (Row row : rows) {
	        	list_index.add(row.getColumns().get(
	        			OTSConstant.IndexColumn.FenrunJourIndex).asString());
	        }
			//------------------------------------范围查询  获取index列表-------------------------------------------------
	        
	    	//------------------------------------批量查询  获取最终数据列表-------------------------------------------------
	        //开始进行批量查询
	        BatchGetRowRequest bgr_request = new BatchGetRowRequest();
	        MultiRowQueryCriteria tableRows = new MultiRowQueryCriteria(detail_tableName);
	        String[] mcols = OTSConstant.MColumns.FenrunJourDetail.split(",");
	        //添加批量查询的主键
	        for (int i = 0; i < list_index.size(); i++) {
		        RowPrimaryKey primaryKey = new RowPrimaryKey();
		        primaryKey.addPrimaryKeyColumn(OTSConstant.FenrunJourDetailEnum.FEE_SN.getName(),
	                    PrimaryKeyValue.fromString(list_index.get(i)));
                tableRows.addRow(primaryKey);
			}
	        tableRows.addColumnsToGet(mcols);
	        bgr_request.addMultiRowQueryCriteria(tableRows);
	        BatchGetRowResult  bgr_result = client.batchGetRow(bgr_request);
	        
	        //设置查询失败数据的查询请求
	        BatchGetRowRequest failedOperations = null;
	        //成功的row list
	        List<Row> succeedRows = new ArrayList<Row>();
	        //所有的查询返回项
	        List<RecordRow> list_rows = new ArrayList<RecordRow>();
	    	List<ResponseResult> list_rr = new ArrayList<ResponseResult>();

	        //开始扫描查询结果
	        int retryCount = 0;
	        do {
	            failedOperations = new BatchGetRowRequest();
                //获得第一次查询出来的行结果状态
	            //String 指的是table_name
	            //List<RowStatus>指的是每行数据相关参数，包括tableName-error-row-consumedCapacity-idex
	           List<RowStatus> statuses = bgr_result.getBatchGetRowStatus(detail_tableName);
               tableRows = new MultiRowQueryCriteria(detail_tableName);
               for (int index = 0; index < statuses.size(); index++) {
                   RowStatus rowStatus = statuses.get(index);
                   if (!rowStatus.isSucceed()) {
                       // 操作失败， 需要放到重试列表中再次重试
                       // 需要重试的操作可以从request中获取参数
                       tableRows.addRow(bgr_request.getPrimaryKey(detail_tableName, index));
                   } else {
                	   //如果行状态为成功，则输出数据
                	   Map<String,String> map_cols =new HashMap<String, String>();
                	   RecordRow rrow = new RecordRow();
                	   
                	   Row row  = rowStatus.getRow();
                       succeedRows.add(row);
                       
                       System.out.println("第"+(index+1)+"数据开始输出----------------------------");
                       for (int i = 0; i < mcols.length; i++) {            	
                    	String  mcol_name = mcols[i];
                    	String  mcol_value = row.getColumns().get(mcol_name).asString();
                    	map_cols.put(mcol_name, mcol_value);
                    	System.out.println("mcol_name:"+mcol_name+"mcol_value"+mcol_value);
                       }
                       rrow.setRow(map_cols);
                       list_rows.add(rrow);
                       System.out.println("第"+(index+1)+"数据输出结束-----------------------------");
                   }
	                if (!tableRows.getRowKeys().isEmpty()) {
	                    tableRows.addColumnsToGet(new String[] { OTSConstant.MColumns.FenrunJourDetail });
	                    failedOperations.addMultiRowQueryCriteria(tableRows);
	                }
	            }
                //如果鄋操作都已经成功，则不需要重试
	            if (failedOperations.isEmpty() ){
	            	logger.info("Request_id:"+fjVo.getRequest_id()+"异常的批量查询处理完成！");
	                break;
	            }
	            //如果重试次数达到了上限，则不需要重试
	            if ( ++retryCount > QueryHandleConstant.Constant.BATCHGET_RETRY){
 	            	for (int i = 0; i <statuses.size(); i++) {
 	               	    ResponseResult rr = new  ResponseResult();
		            	rr.setType(R.SystemConstant.RESTYPE_HANDLE);
		            	rr.setResult_code(QueryHandleConstant.ResultCode.QUERY_BATCHGET_IDXFAIL);
		            	rr.setField(QueryHandleConstant.Field.OTS_DETAIL_TABLE);
		            	rr.setMessage(QueryHandleConstant.ResultMessage.QUERY_BATCHGET_IDXFAIL_ERRMSG+"("+ statuses.get(i).getRow().
							    getColumns().get(OTSConstant.FenrunJourDetailEnum.FEE_SN.
									    getName()).asString() + ","+ statuses.get(i).getError().getMessage() + ")");
		            	list_rr.add(rr);
					}
	            	logger.error("Request_id:"+fjVo.getRequest_id()+"异常的批量查询重试处理已达到3次，"
	            			+ "仍然没有成功，只返回查询成功的行数");
	            	
	            	//设置返回对象
	            	FenrunJourResponse frjrVo = new FenrunJourResponse();
	            	frjrVo.setRequest_id(fjVo.getRequest_id());
	            	frjrVo.setSysid(fjVo.getSysid());
	            	frjrVo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
	            	frjrVo.setRequest_param(fjVo);
	            	frjrVo.setResponse_records(list_rows);//设置已查询出来的结果
	            	frjrVo.setReponse_result(list_rr);
		        	return frjrVo;
	            }
	            // 如果有需要重试的操作， 则稍微等待一会后再次重试， 否则继续出错的概率很高。
	            try {
	            	// 100ms后继续重试
	                Thread.sleep(QueryHandleConstant.Constant.BATCHGET_RETRY_WAIT); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            bgr_request = failedOperations;
	            bgr_result = client.batchGetRow(bgr_request);
	        } 
	        while (true);
	        //
	        System.out.println(String.format("查询成功行数据。", succeedRows.size()));
	        
	        
	        //查询成功返回码
	        ResponseResult rr = new  ResponseResult();
        	rr.setType(R.SystemConstant.RESTYPE_HANDLE);
        	rr.setResult_code(QueryHandleConstant.ResultCode.QUERY_HANDLE_SUCCESS);
        	rr.setField(QueryHandleConstant.Field.OTS_ALL_TABLE);
        	rr.setMessage(QueryHandleConstant.ResultMessage.QUERY_HANDLE_SUCCESSMSG);
        	list_rr.add(rr);
        	
	        //拼装下一次查询条件
        	FenrunJourRequest next_fjrVo = new FenrunJourRequest();
	        //获取下一次的startkey
	        RowPrimaryKey  next_start_pk = gr_result.getNextStartPrimaryKey();
	        if(next_start_pk==null){
	        	next_fjrVo.setS_fee_sn(null);
	        	next_fjrVo.setS_occur_date(null);
	        	next_fjrVo.setS_org_code(null);
	        	next_fjrVo.setS_fee_category1(null);
	        	next_fjrVo.setS_fee_category2(null);
	        }
	        next_fjrVo.setS_fee_sn(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FenrunJourIndexEnum.FEE_SN.getName()).asString());
	        next_fjrVo.setS_occur_date(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FenrunJourIndexEnum.OCCUR_DATE.getName()).asString());
	        next_fjrVo.setS_org_code(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FenrunJourIndexEnum.ORG_CODE.getName()).asString());
	        next_fjrVo.setS_fee_category1(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY1.getName()).asString());
	        next_fjrVo.setS_fee_category2(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FenrunJourIndexEnum.FEE_CATEGORY2.getName()).asString());
	         //一下参数与上次提交的参数保证一致(NextPrimaryKey)
	        next_fjrVo.setE_fee_sn(fjVo.getE_fee_sn());
	        next_fjrVo.setE_occur_date(fjVo.getE_occur_date());
	        next_fjrVo.setE_org_code(fjVo.getE_org_code());
	        next_fjrVo.setE_fee_category1(fjVo.getE_fee_category1());
	        next_fjrVo.setE_fee_category2(fjVo.getE_fee_category2());
	        next_fjrVo.setDirection(fjVo.getDirection());
	        next_fjrVo.setLimit(fjVo.getLimit());
	        next_fjrVo.setRequest_id(fjVo.getRequest_id());
	        next_fjrVo.setRequest_ip(fjVo.getRequest_ip());
	        
	        //设置返回信息
	        FenrunJourResponse frjrVo = new FenrunJourResponse();
	        frjrVo.setRequest_id(fjVo.getRequest_id());
	        frjrVo.setSysid(fjVo.getSysid());
	        frjrVo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
	        frjrVo.setRequest_param(next_fjrVo);
	        frjrVo.setResponse_records(list_rows);//设置已查询出来的结果
	        frjrVo.setReponse_result(list_rr); 	        
	        

	        return frjrVo;
	    	//------------------------------------批量查询  获取最终数据列表-------------------------------------------------

	        
		} catch (ServiceException e) {
			//设置错误日志
        	logger.error("阿里云ots service 异常,查询请求id"+fjVo.getRequest_id()+",阿里云请求id:"+e.getRequestId()+
        			",traceid:"+e.getTraceId()+",hostid:"+e.getHostId()+"messageid:"+e.getMessage());
            //设置返回结果
		   	List<ResponseResult> list_rr = new ArrayList<ResponseResult>();
		   	ResponseResult rr = new  ResponseResult();
		   	rr.setType(R.SystemConstant.RESTYPE_HANDLE);
		   	rr.setResult_code(e.getErrorCode());
		   	rr.setField(QueryHandleConstant.Field.OTS_SERVICE_EX);
		   	rr.setMessage(e.getMessage());
	       	list_rr.add(rr);
		   	
	        //设置返回信息
	        FenrunJourResponse frjrVo = new FenrunJourResponse();
	        frjrVo.setRequest_id(fjVo.getRequest_id());
	        frjrVo.setSysid(fjVo.getSysid());
	        frjrVo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
	        frjrVo.setRequest_param(fjVo);
	        frjrVo.setResponse_records(null);
	        frjrVo.setReponse_result(list_rr); 	
	        
	        return frjrVo;
        	
        } catch (ClientException e) {
        	//设置错误日志
        	logger.error("阿里云ots client 异常,查询请求id"+fjVo.getRequest_id()+",traceid:"+e.getTraceId()+
        			",message:"+e.getMessage());
        	
             //设置返回结果
        	List<ResponseResult> list_rr = new ArrayList<ResponseResult>();
        	ResponseResult rr = new  ResponseResult();
        	rr.setType(R.SystemConstant.RESTYPE_HANDLE);
        	rr.setResult_code(e.getErrorCode());
        	rr.setField(QueryHandleConstant.Field.OTS_CLIENT_EX);
        	rr.setMessage(e.getMessage());
           	list_rr.add(rr);
           	
	        //设置返回信息
	        FenrunJourResponse frjrVo = new FenrunJourResponse();
	        frjrVo.setRequest_id(fjVo.getRequest_id());
	        frjrVo.setSysid(fjVo.getSysid());
	        frjrVo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
	        frjrVo.setRequest_param(fjVo);
	        frjrVo.setResponse_records(null);
	        frjrVo.setReponse_result(list_rr); 	  
            
            return frjrVo;
        }

		
	}

}
