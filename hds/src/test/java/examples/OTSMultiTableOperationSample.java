/**
 * Copyright (C) Alibaba Cloud Computing
 * All rights reserved.
 * 
 * 版权所有 （C）阿里云计算有限公司
 */

package examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.aliyun.openservices.ots.ClientException;
import com.aliyun.openservices.ots.ServiceException;
import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.OTSErrorCode;
import com.aliyun.openservices.ots.OTSException;
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
import com.hds.api.common.constant.OTSConstant;
import com.hds.api.common.constant.QueryHandleConstant;
import com.hds.api.common.constant.R;
import com.hds.api.sys.vo.RecordRow;
import com.hds.api.sys.vo.ResponseResult;

public class OTSMultiTableOperationSample {
	
	private static Logger logger = Logger.getLogger(OTSMultiTableOperationSample.class);


    public static void main(String args[]) {
        final String endPoint = "http://YDT-DAT-HDS-OTS.cn-shanghai-zty-d01.ots.dcp.dev.ect-it.com"; 
        final String accessId = "q6iC329hNhDnxQuW";
        final String accessKey = "2TEP4W1b0ASWqdg7TRDdXjqzV4SE5S";
        final String instanceName = "YDT-DAT-HDS-OTS";
        
        OTSClient client = new OTSClient(endPoint, accessId, accessKey,
                instanceName);

        try {
            batchGetRow(client);
        } catch (ServiceException e) {
            e.printStackTrace();
            System.err.println("操作失败，详情：" + e.getMessage());
            // 可以根据错误代码做出处理， OTS的ErrorCode定义在OTSErrorCode中。
            if (OTSErrorCode.QUOTA_EXHAUSTED.equals(e.getErrorCode())) {
                System.err.println("超出存储配额。");
            }
            // Request ID可以用于有问题时联系客服诊断异常。
            System.err.println("Request ID:" + e.getRequestId());
        } catch (ClientException e) {
            // 可能是网络不好或者是返回结果有问题
            System.err.println("请求失败，详情：" + e.getMessage());
        } 
           client.shutdown();
  }


    private static void batchGetRow(OTSClient client)
            throws OTSException, ClientException {
    	System.out.println("############# 开始GETRANGE操作 #############");
    	RangeRowQueryCriteria criteria = new RangeRowQueryCriteria("his_ccs_fee_settle_order_index");
    	//设置StartPrimaryKey  ---------------------------------------------------  
		RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
		//如果为空则指为无限小(初次查询)
		 inclusiveStartKey.addPrimaryKeyColumn(
				 OTSConstant.FeeSettleOrderIndexEnum.ORDER_SN.getName(),
				 PrimaryKeyValue.fromString("201604060000010222"));	
		inclusiveStartKey.addPrimaryKeyColumn(
				OTSConstant.FeeSettleOrderIndexEnum.OCCUR_DATE.getName(),
				PrimaryKeyValue.fromString("20160406"));
		inclusiveStartKey.addPrimaryKeyColumn(
				OTSConstant.FeeSettleOrderIndexEnum.DEAL_STATE.getName(),
				 PrimaryKeyValue.INF_MIN);
		//如果为空则指为无限小
		 inclusiveStartKey.addPrimaryKeyColumn(
				 OTSConstant.FeeSettleOrderIndexEnum.CREDIT_ORG_CODE.getName(),
				 PrimaryKeyValue.INF_MIN);
		//如果为空则指为无限小
		 inclusiveStartKey.addPrimaryKeyColumn(
				 OTSConstant.FeeSettleOrderIndexEnum.DEBIT_ORG_CODE.getName(),
				 PrimaryKeyValue.INF_MIN);
		

	    //设置EndPrimaryKey  ---------------------------------------------------  
	    RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();  
	    exclusiveEndKey.addPrimaryKeyColumn(
				 OTSConstant.FeeSettleOrderIndexEnum.ORDER_SN.getName(),
				 PrimaryKeyValue.INF_MAX);
	    exclusiveEndKey.addPrimaryKeyColumn(
				OTSConstant.FeeSettleOrderIndexEnum.OCCUR_DATE.getName(),
				PrimaryKeyValue.fromString("20160421"));
	    exclusiveEndKey.addPrimaryKeyColumn(
				OTSConstant.FeeSettleOrderIndexEnum.DEAL_STATE.getName(),
				 PrimaryKeyValue.INF_MAX);
	    exclusiveEndKey.addPrimaryKeyColumn(
			 OTSConstant.FeeSettleOrderIndexEnum.CREDIT_ORG_CODE.getName(),
			 PrimaryKeyValue.INF_MAX);
        //如果为空则指为无限小
	    exclusiveEndKey.addPrimaryKeyColumn(
			 OTSConstant.FeeSettleOrderIndexEnum.DEBIT_ORG_CODE.getName(),
			 PrimaryKeyValue.INF_MAX);
		
		
		//设置开始结束PK，设置查询方向，设置
		criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
        criteria.setDirection(Direction.valueOf("FORWARD"));
        criteria.setLimit(100);
        criteria.addColumnsToGet(new String[]{
        		OTSConstant.MColumns.FeeSettleOrderIndex
        });
        
        GetRangeRequest gr_request = new GetRangeRequest();
        gr_request.setRangeRowQueryCriteria(criteria);
        GetRangeResult gr_result = client.getRange(gr_request);
        List<Row> rows = gr_result.getRows();
        
        
        List<String> list_index= new ArrayList<String>();
        for (Row row : rows) {
        	list_index.add(row.getColumns().get(OTSConstant.IndexColumn.FeeSettleOrderIndex).asString());
        	System.out.println(row.getColumns().get(OTSConstant.IndexColumn.FeeSettleOrderIndex).asString());
        }
        
        int consumedReadCU = gr_result.getConsumedCapacity().getCapacityUnit()
                .getReadCapacityUnit();
        System.out.println("本次查询结果的行数为：" + rows.size());
        System.out.println("本次读操作消耗的读CapacityUnit为：" + consumedReadCU);
        
    	System.out.println("############# 结束GETRANGE操作 #############");
    	
    	
    	
    System.out.println("\n############# 开始BatchGetRow操作 #############");
  
    //开始进行批量查询
    BatchGetRowRequest bgr_request = new BatchGetRowRequest();
    MultiRowQueryCriteria tableRows = new MultiRowQueryCriteria("his_ccs_fee_settle_order");
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
    //成功的列
    List<Row> succeedRows = new ArrayList<Row>();
    

    List<RecordRow> list_rows = new ArrayList<RecordRow>();
	List<ResponseResult> list_rr = new ArrayList<ResponseResult>();
	

    //开始扫描查询结果
    int retryCount = 0;
    do {
        failedOperations = new BatchGetRowRequest();
        //获得第一次查询出来的行结果状态
        //String 指的是table_name
        //List<RowStatus>指的是每行数据相关参数，包括tableName-error-row-consumedCapacity-idex
       List<RowStatus> statuses = bgr_result.getBatchGetRowStatus("his_ccs_fee_settle_order");
       tableRows = new MultiRowQueryCriteria("his_ccs_fee_settle_order");
       for (int index = 0; index < statuses.size(); index++) {
           RowStatus rowStatus = statuses.get(index);
           if (!rowStatus.isSucceed()) {
               // 操作失败， 需要放到重试列表中再次重试
               // 需要重试的操作可以从request中获取参数
               tableRows.addRow(bgr_request.getPrimaryKey("his_ccs_fee_settle_order", index));
           } else {

        	   Map<String,String> map_cols =new HashMap<String, String>();
        	   RecordRow rrow = new RecordRow();
        	   
        	   Row row  = rowStatus.getRow();
               succeedRows.add(row);
             	//debug日志可删除
               System.out.println("第"+(index+1)+"数据开始输出----------------------------");
               
               for (int i = 0; i < mcols.length; i++) {            	
            	String  mcol_name = mcols[i];
            	String  mcol_value = row.getColumns().get(mcol_name).asString();
            	map_cols.put(mcol_name, mcol_value);
             	//debug日志可删除
            	System.out.println("mcol_name:"+mcol_name+"mcol_value"+mcol_value);
			}
              rrow.setRow(map_cols);
              list_rows.add(rrow);
           	//debug日志可删除
              System.out.println("第"+(index+1)+"数据输出结束-----------------------------");
           }
            if (!tableRows.getRowKeys().isEmpty()) {
                tableRows.addColumnsToGet(new String[] { OTSConstant.MColumns.FeeSettleOrderDetail });
                failedOperations.addMultiRowQueryCriteria(tableRows);
            }
        }
        //如果鄋操作都已经成功，则不需要重试
        if (failedOperations.isEmpty() ){
        	logger.info("异常批量批量查询处理完成！");
            break;
        }
        //如果重试次数达到了上限，则不需要重试
        if ( ++retryCount > QueryHandleConstant.Constant.BATCHGET_RETRY){
         	for (int j= 0; j <statuses.size(); j++) {
         		if(!statuses.get(j).isSucceed()){
            	ResponseResult rr = new  ResponseResult();
            	rr.setType(R.SystemConstant.RESTYPE_HANDLE);
            	rr.setResult_code(QueryHandleConstant.ResultCode.QUERY_BATCHGET_IDXFAIL);
            	rr.setField(QueryHandleConstant.ResultMessage.QUERY_BATCHGET_IDXFAIL_ERRMSG+"("+ statuses.get(j).getRow().
					    getColumns().get(OTSConstant.FeeSettleOrderDetailEnum.ORDER_SN.
							    getName()).asString() + ","+ statuses.get(j).getError().getMessage() + ")");
            	list_rr.add(rr);
         		}

			}
        	System.out.println("批量查询索引表获得索引，部分索引批量查询超过重试次数，请发送查询请求");
            break; 
        }
        // 如果有需要重试的操作， 则稍微等待一会后再次重试， 否则继续出错的概率很高。
        try {
            Thread.sleep(200); // 100ms后继续重试
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bgr_request = failedOperations;
        bgr_result = client.batchGetRow(bgr_request);
    
    } 
    while (true);
    System.out.println("批量查询处理完成！");

        System.out.println("查询成功行数据。"+ succeedRows.size());
        
        System.out.println("查询的总行数据。"+list_rows.size());
        
        
    }
}
