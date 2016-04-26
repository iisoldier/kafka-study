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
import com.aliyun.openservices.ots.OTSErrorCode;
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
import com.hds.api.ccs.vo.FeeSettleOrderRequest;
import com.hds.api.ccs.vo.FeeSettleOrderResponse;
import com.hds.api.common.constant.OTSConstant;
import com.hds.api.common.constant.QueryHandle;
import com.hds.api.common.constant.R;
import com.hds.api.common.util.DateUtils;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.OTSConfig;
import com.hds.api.sys.vo.RecordRow;
import com.hds.api.sys.vo.ResponseResult;

/** 
 * @ClassName:com.hds.api.ccs.dao.CCSDaoImpl
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月23日 下午4:41:37
 */
@Repository("CCSFeeSettleOrderDao")
public class CCSFeeSettleOrderDaoImpl implements CCSFeeSettleOrderDao{

	
	private static Logger logger = Logger.getLogger(CCSFeeSettleOrderDaoImpl.class);
	
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
	public FeeSettleOrderResponse getFeeSettleOrder(FeeSettleOrderRequest fsoVo, OTSConfig ots_config) {
		// TODO Auto-generated method stub

		OTSClient client = new OTSClient( ots_config.getAliyun_ots_endpoint(), ots_config.getAliyun_accesskey_id(), 
				ots_config.getAliyun_accesskey_secret() ,ots_config.getAliyun_ots_instance()) ;
		String index_tableName =OTSConstant.CCSTables.FeeSettleOrderIndex;
		String detail_tableName =OTSConstant.CCSTables.FeeSettleOrderDetail;

		
		//抓取OTS异常
		try {
			//------------------------------------范围查询  获取index列表-------------------------------------------------
			//索引查询条件
			RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(index_tableName);
		    
			//设置StartPrimaryKey  ---------------------------------------------------  
			RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
			//如果为空则指为无限小(初次查询)
			if(fsoVo.getS_order_sn().equals("")){
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.ORDER_SN.getName(),
						 PrimaryKeyValue.INF_MIN);
			}//后续被NextPrimaryKey替代后，则可以直接进行查询
			else{
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.ORDER_SN.getName(),
						 PrimaryKeyValue.fromString(fsoVo.getE_order_sn()));
			}
			inclusiveStartKey.addPrimaryKeyColumn(
					OTSConstant.FeeSettleOrderIndexEnum.OCCUR_DATE.getName(),
					PrimaryKeyValue.fromString(fsoVo.getS_occur_date()));
			inclusiveStartKey.addPrimaryKeyColumn(
					OTSConstant.FeeSettleOrderIndexEnum.DEAL_STATE.getName(),
					PrimaryKeyValue.fromString(fsoVo.getS_deal_state()));
			//如果为空则指为无限小
			if(fsoVo.getS_credit_org_code().equals("")){
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.CREDIT_ORG_CODE.getName(),
						 PrimaryKeyValue.INF_MIN);
			}else{
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.CREDIT_ORG_CODE.getName(),
						 PrimaryKeyValue.fromString(fsoVo.getS_credit_org_code()));
			}
			//如果为空则指为无限小
			if(fsoVo.getS_debit_org_code().equals("")){
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.DEBIT_ORG_CODE.getName(),
						 PrimaryKeyValue.INF_MIN);
			}else{
				 inclusiveStartKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.DEBIT_ORG_CODE.getName(),
						 PrimaryKeyValue.fromString(fsoVo.getS_debit_org_code()));
			}

		    //设置EndPrimaryKey  ---------------------------------------------------  
		    RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();  
			if(fsoVo.getE_order_sn().equals("")){
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.ORDER_SN.getName(),
						 PrimaryKeyValue.INF_MAX);
			}//后续被NextPrimaryKey替代后，则可以直接进行查询
			else{
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.ORDER_SN.getName(),
						 PrimaryKeyValue.fromString(fsoVo.getE_order_sn()));
				}
			exclusiveEndKey.addPrimaryKeyColumn(
					OTSConstant.FeeSettleOrderIndexEnum.OCCUR_DATE.getName(),
					PrimaryKeyValue.fromString(fsoVo.getE_occur_date()));
			exclusiveEndKey.addPrimaryKeyColumn(
					OTSConstant.FeeSettleOrderIndexEnum.DEAL_STATE.getName(),
					PrimaryKeyValue.fromString(fsoVo.getE_deal_state()));
			//如果为空则指为无限小
			if(fsoVo.getE_credit_org_code().equals("")){
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.CREDIT_ORG_CODE.getName(),
						 PrimaryKeyValue.INF_MAX);
			}else{
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.CREDIT_ORG_CODE.getName(),
						 PrimaryKeyValue.fromString(fsoVo.getE_credit_org_code()));
			}
			//如果为空则指为无限小
			if(fsoVo.getE_debit_org_code().equals("")){
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.DEBIT_ORG_CODE.getName(),
						 PrimaryKeyValue.INF_MAX);
			}else{
				exclusiveEndKey.addPrimaryKeyColumn(
						 OTSConstant.FeeSettleOrderIndexEnum.DEBIT_ORG_CODE.getName(),
						 PrimaryKeyValue.fromString(fsoVo.getE_debit_org_code()));
			}
			
			//设置开始结束PK，设置查询方向，设置
			criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
	        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
	        criteria.setDirection(Direction.valueOf(fsoVo.getDirection()));
	        criteria.setLimit(fsoVo.getLimit());
	        criteria.addColumnsToGet(new String[]{
	        		OTSConstant.MColumns.FeeSettleOrderIndex
	        });
	        
	        GetRangeRequest gr_request = new GetRangeRequest();
	        gr_request.setRangeRowQueryCriteria(criteria);
	        GetRangeResult gr_result = client.getRange(gr_request);
	        List<Row> rows = gr_result.getRows();
	        
	        
	        //如果索引查询结果为空，则说明没有数据设置返回值
	        if(rows.size()==0){
	        	List<ResponseResult> list_rr = new ArrayList<ResponseResult>();
	        	ResponseResult rr = new  ResponseResult();
	        	rr.setType(R.SystemConstant.REQUEST_HANDLE);
	        	rr.setResult_code(QueryHandle.ResultCode.QUERY_INDEXTB_ISNULL);
	        	rr.setField(QueryHandle.Field.OTS_INDEX_TABLE);
	        	rr.setField(QueryHandle.ResultMessage.QUERY_INDEXTB_ISNULL_ERRMSG);
	        	
	        	//设置返回对象
	        	FeeSettleOrderResponse fsorVo = new FeeSettleOrderResponse();
	        	fsorVo.setRequest_id(fsoVo.getRequest_id());
	        	fsorVo.setSysid(fsoVo.getSysid());
	        	fsorVo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
	        	fsorVo.setRequest_param(fsoVo);
	        	fsorVo.setResponse_records(null);
	        	fsorVo.setReponse_result(list_rr);
	        	return fsorVo;
	        }
	        
	        //获得索引表的查询结果，明细表的pk list
	        List<String> list_index= new ArrayList<String>();
	        for (Row row : rows) {
	        	list_index.add(row.getColumns().get(
	        			OTSConstant.IndexColumn.FeeSettleOrderIndex).asString());
	        }
			//------------------------------------范围查询  获取index列表-------------------------------------------------
	        
	    	//------------------------------------批量查询  获取最终数据列表-------------------------------------------------
	        //开始进行批量查询
	        BatchGetRowRequest bgr_request = new BatchGetRowRequest();
	        MultiRowQueryCriteria tableRows = new MultiRowQueryCriteria(detail_tableName);
	        String[] mcols = OTSConstant.MColumns.FeeSettleOrderDetail.split(",");
	        //添加批量查询的主键
	        for (int i = 0; i < list_index.size(); i++) {
		        RowPrimaryKey primaryKey = new RowPrimaryKey();
		        primaryKey.addPrimaryKeyColumn(OTSConstant.FeeSettleOrderDetailEnum.ORDER_SN.getName(),
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
	                    tableRows.addColumnsToGet(new String[] { OTSConstant.MColumns.FeeSettleOrderDetail });
	                    failedOperations.addMultiRowQueryCriteria(tableRows);
	                }
	            }
                //如果鄋操作都已经成功，则不需要重试
	            if (failedOperations.isEmpty() ){
	            	logger.info("Request_id:"+fsoVo.getRequest_id()+"异常的批量查询处理完成！");
	                break;
	            }
	            //如果重试次数达到了上限，则不需要重试
	            if ( ++retryCount > QueryHandle.OTSConstant.BATCHGET_RETRY){
 	            	for (int i = 0; i <statuses.size(); i++) {
 	               	    ResponseResult rr = new  ResponseResult();
		            	rr.setType(R.SystemConstant.REQUEST_HANDLE);
		            	rr.setResult_code(QueryHandle.ResultCode.QUERY_BATCHGET_IDXFAIL);
		            	rr.setField(QueryHandle.ResultMessage.QUERY_BATCHGET_IDXFAIL_ERRMSG+"("+ statuses.get(i).getRow().
							    getColumns().get(OTSConstant.FeeSettleOrderDetailEnum.ORDER_SN.
									    getName()).asString() + ","+ statuses.get(i).getError().getMessage() + ")");
		            	list_rr.add(rr);
					}
	            	logger.error("Request_id:"+fsoVo.getRequest_id()+"异常的批量查询重试处理已达到3次，"
	            			+ "仍然没有成功，只返回查询成功的行数");
	            	
	            	//设置返回对象
		        	FeeSettleOrderResponse fsorVo = new FeeSettleOrderResponse();
		        	fsorVo.setRequest_id(fsoVo.getRequest_id());
		        	fsorVo.setSysid(fsoVo.getSysid());
		        	fsorVo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
		        	fsorVo.setRequest_param(fsoVo);
		        	fsorVo.setResponse_records(list_rows);//设置已查询出来的结果
		        	fsorVo.setReponse_result(list_rr);
		        	return fsorVo;
	            }
	            // 如果有需要重试的操作， 则稍微等待一会后再次重试， 否则继续出错的概率很高。
	            try {
	            	// 100ms后继续重试
	                Thread.sleep(QueryHandle.OTSConstant.BATCHGET_RETRY_WAIT); 
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
        	rr.setType(R.SystemConstant.REQUEST_HANDLE);
        	rr.setResult_code(QueryHandle.ResultCode.QUERY_HANDLE_SUCCESS);
        	rr.setField(QueryHandle.ResultMessage.QUERY_HANDLE_SUCCESSMSG);
        	list_rr.add(rr);
        	
	        //拼装下一次查询条件
	        FeeSettleOrderRequest next_fsoVo = new FeeSettleOrderRequest();
	        //获取下一次的startkey
	        RowPrimaryKey  next_start_pk = gr_result.getNextStartPrimaryKey();
	        next_fsoVo.setE_order_sn(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FeeSettleOrderIndexEnum.ORDER_SN.getName()).asString());
	        next_fsoVo.setE_occur_date(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FeeSettleOrderIndexEnum.OCCUR_DATE.getName()).asString());
	        next_fsoVo.setE_deal_state(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FeeSettleOrderIndexEnum.DEAL_STATE.getName()).asString());
	        next_fsoVo.setE_debit_org_code(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FeeSettleOrderIndexEnum.CREDIT_ORG_CODE.getName()).asString());
	        next_fsoVo.setE_credit_org_code(next_start_pk.getPrimaryKey().
	        		get(OTSConstant.FeeSettleOrderIndexEnum.DEBIT_ORG_CODE.getName()).asString());
	         //一下参数与上次提交的参数保证一致(NextPrimaryKey)
	        next_fsoVo.setE_order_sn(fsoVo.getE_order_sn());
	        next_fsoVo.setE_occur_date(fsoVo.getE_occur_date());
	        next_fsoVo.setE_deal_state(fsoVo.getE_deal_state());
	        next_fsoVo.setE_debit_org_code(fsoVo.getE_debit_org_code());
	        next_fsoVo.setE_credit_org_code(fsoVo.getE_credit_org_code());
	        next_fsoVo.setDirection(fsoVo.getDirection());
	        next_fsoVo.setLimit(fsoVo.getLimit());
	        next_fsoVo.setRequest_id(fsoVo.getRequest_id());
	        next_fsoVo.setRequest_ip(fsoVo.getRequest_ip());
	        
	        //设置返回信息
        	FeeSettleOrderResponse fsorVo = new FeeSettleOrderResponse();
        	fsorVo.setRequest_id(fsoVo.getRequest_id());
        	fsorVo.setSysid(fsoVo.getSysid());
        	fsorVo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
        	fsorVo.setRequest_param(next_fsoVo);
        	fsorVo.setResponse_records(list_rows);//设置已查询出来的结果
        	fsorVo.setReponse_result(list_rr); 	        
	        

	        return fsorVo;
	    	//------------------------------------批量查询  获取最终数据列表-------------------------------------------------

	        
		} catch (ServiceException e) {
            e.printStackTrace();
            System.err.println("操作失败，详情：" + e.getMessage());
            // 可以根据错误代码做出处理， OTS的ErrorCode定义在OTSErrorCode中。
            if (OTSErrorCode.QUOTA_EXHAUSTED.equals(e.getErrorCode())) {
                System.err.println("超出存储配额。");
            }
            // Request ID可以用于有问题时联系客服诊断异常。
            System.err.println("Request ID:" + e.getRequestId());
            return null;
        } catch (ClientException e) {
            // 可能是网络不好或者是返回结果有问题
            System.err.println("请求失败，详情：" + e.getMessage());
            return null;
        }

		
	}

}
