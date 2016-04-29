/**
 * Copyright (C) Alibaba Cloud Computing
 * All rights reserved.
 * 
 * 版权所有 （C）阿里云计算有限公司
 */

package examples;

import java.util.List;

import com.aliyun.openservices.ots.ClientException;
import com.aliyun.openservices.ots.ServiceException;
import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.OTSErrorCode;
import com.aliyun.openservices.ots.OTSException;
import com.aliyun.openservices.ots.model.GetRangeRequest;
import com.aliyun.openservices.ots.model.GetRangeResult;
import com.aliyun.openservices.ots.model.PrimaryKeyValue;
import com.aliyun.openservices.ots.model.RangeRowQueryCriteria;
import com.aliyun.openservices.ots.model.Row;
import com.aliyun.openservices.ots.model.RowPrimaryKey;
import com.hds.api.common.constant.OTSConstant;

public class OTSMultiDataSample {


    public static void main(String args[]) {
        final String endPoint = "http://YDT-DAT-HDS-OTS.cn-shanghai-zty-d01.ots.dcp.dev.ect-it.com"; 
        final String accessId = "q6iC329hNhDnxQuW";
        final String accessKey = "2TEP4W1b0ASWqdg7TRDdXjqzV4SE5S";
        final String instanceName = "YDT-DAT-HDS-OTS";
        
        OTSClient client = new OTSClient(endPoint, accessId, accessKey,
                instanceName);

        try {
        	//listTable(client);

          // describeTable(client,"his_ccs_fee_settle_order");
         // describeTable(client,"his_ccs_fee_settle_order_index");
           
        	getRange(client,"his_ccs_fee_settle_order_index");
             
            // 再取回来看看。
            //getRange(client, tableName);
        } catch (ServiceException e) {
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

    private static void getRange(OTSClient client, String tableName)
            throws OTSException, ClientException {
        // 演示一下如何按主键范围查找，这里查找uid从1-4（左开右闭）的数据
        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
//        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
//        inclusiveStartKey.addPrimaryKeyColumn("order_sn",
//                PrimaryKeyValue.INF_MIN); 
//        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
//
//        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();
//        exclusiveEndKey.addPrimaryKeyColumn("order_sn",
//                PrimaryKeyValue.INF_MAX); 
//        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小

        
    	RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
		//如果为空则指为无限小(初次查询)
    	inclusiveStartKey.addPrimaryKeyColumn(
				 OTSConstant.FeeSettleOrderIndexEnum.ORDER_SN.getName(),
				 PrimaryKeyValue.fromString("201604060000010222"));
    	inclusiveStartKey.addPrimaryKeyColumn(
				OTSConstant.FeeSettleOrderIndexEnum.OCCUR_DATE.getName(),
				PrimaryKeyValue.fromString("20160407"));
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
        
        
        criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
        criteria.setLimit(200);

        GetRangeRequest request = new GetRangeRequest();
        request.setRangeRowQueryCriteria(criteria);
        GetRangeResult result = client.getRange(request);
        List<Row> rows = result.getRows();
      //  System.out.println(  result.getNextStartPrimaryKey().toString());
        for (Row row : rows) {
        // System.out.println( row.toString()); 
         System.out.println( row.getColumns().get("index_order_sn").asString()); 
         
        }

        int consumedReadCU = result.getConsumedCapacity().getCapacityUnit()
                .getReadCapacityUnit();
        
        
        System.out.println("本次查询结果的行数为：" + rows.size());
        System.out.println("本次读操作消耗的读CapacityUnit为：" + consumedReadCU);
    }
}
