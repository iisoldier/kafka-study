package com.hds.api.common.constant;

import com.aliyun.openservices.ots.model.PrimaryKeyType;


public class OTSConstant {
       
	/**
	* @ClassName:com.hds.api.common.constant.MColumns
	* @Description:返回的数据列表  (切记不要有空格!!!!!!!!!!!!!)
	* @author zengli
	* @date 创建时间：2016年4月25日 上午9:29:24
	 */
	public interface MColumns{
		
		public static String FeeSettleOrderDetail ="order_sn,fee_gather_id,currency,credit_org_code,credit_account_type,"
				+ "credit_account_no,debit_org_code,debit_account_type,debit_account_no,"
				+ "trade_amount,success_amount,order_type,deal_state,trans_type,fail_count,"
				+ "occur_date,occur_time,result_code,result_info,reserve1,reserve2,reserve3";
		
		public static String FeeSettleOrderIndex="index_order_sn";
		
		
	}
	
	
	/**
	* @ClassName:com.hds.api.common.constant.IndexColumns
	* @Description:
	* @author zengli
	* @date 创建时间：2016年4月25日 下午3:58:24
	 */
	public interface IndexColumn{
		public static String FeeSettleOrderIndex="index_order_sn";
	}
	
	
	
	
	/**
	* @ClassName:com.hds.api.common.constant.CCSTables
	* @Description: CCSTable名称
	* @author zengli
	* @date 创建时间：2016年4月24日 下午7:44:22
	 */
	    public interface CCSTables{
	    	public final String FeeSettleOrderIndex ="his_ccs_fee_settle_order_index";
	    	public final String FeeSettleOrderDetail ="his_ccs_fee_settle_order";
	    }

	    
	/**
	* @ClassName:com.hds.api.common.constant.FeeSettleOrderIndexEnum
	* @Description: 指令划付费用索引表
	* @author zengli
	* @date 创建时间：2016年4月24日 下午7:44:47
	 */
	    // FeeSettleOrderIndexEnum
		public enum FeeSettleOrderIndexEnum {
			
			ORDER_SN("order_sn", PrimaryKeyType.STRING.name()),
			OCCUR_DATE("occur_date", PrimaryKeyType.STRING.name()),
			DEAL_STATE("deal_state", PrimaryKeyType.STRING.name()),
			CREDIT_ORG_CODE("credit_org_code", PrimaryKeyType.STRING.name()),
			DEBIT_ORG_CODE("debit_org_code", PrimaryKeyType.STRING.name());

			private String name;
			private String type;
			private FeeSettleOrderIndexEnum(String name, String type) {
				this.name = name;
				this.type = type;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			
		}
		
	/**	
	* @ClassName:com.hds.api.common.constant.FeeSettleOrderDetailEnum
	* @Description:指令划付费用流水表
	* @author zengli
	* @date 创建时间：2016年4月25日 上午9:32:13
	 */
	public enum FeeSettleOrderDetailEnum {

		ORDER_SN("order_sn", PrimaryKeyType.STRING.name());

		private String name;
		private String type;

		private FeeSettleOrderDetailEnum(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}
		
		
}

