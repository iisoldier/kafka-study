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
		//指令划付流水明细字段
		public final static String FeeSettleOrderDetail ="order_sn,fee_gather_id,currency,credit_org_code,credit_account_type,"
				+ "credit_account_no,debit_org_code,debit_account_type,debit_account_no,"
				+ "trade_amount,success_amount,order_type,deal_state,trans_type,fail_count,"
				+ "occur_date,occur_time,result_code,result_info,reserve1,reserve2,reserve3";
		//分润流水明细字段
		public final static String FenrunJourDetail ="fee_sn,fee_id,fee_name,fee_type_flag,serial_no,"
				+ "fee_category1,fee_category2,occur_date,org_code,org_name,currency,fee_amount,"
				+ "fee_cycle_id,fee_detail_id,create_type,last_update_date,last_update_time,gather_flag,"
				+ "need_gather,channel_serial_no,create_date,result_code,result_info,detail_no";
		//费用流水明细数据查询 明细字段
		public final static String FeeJourDetailDetail="core_busi_type,core_busi_mark,core_msg_no,core_biz_serial_no"
				+ ",core_channel_serial_no,fee_category1,fee_category2,core_detail_no,core_trade_date,core_taker_code"
				+ ",core_transferee_code,core_transferor_code,currency,core_orig_busi_type,core_orig_busi_mark,"
				+ "core_orig_msg_no,core_orig_biz_serial_no,core_orig_taker_code,org_code,org_name"
				+ ",occur_amount,result_code,occur_date";
		
		//费用流水明细字段 jinmeng
		public static String FeeJourDetail ="fee_sn,fee_id,fee_name,fee_type_flag,serial_no,"
				+ "fee_category1,fee_category2,occur_date,org_code,org_name,user_id,user_name,currency,occur_amount,"
				+ "standard_fare,fee_amount,favorable_amount,fee_cycle_id,fee_detail_id,create_type,last_update_date,last_update_time,gather_flag,"
				+ "need_gather,result_code,result_info,tohis_date,channel_serial_no,create_date,fees_base_type,detail_no";
	
		//分润明细明细字段 jinmeng
				public static String FenrunJourRealDetailDetail ="fenrun_fee_sn,fenrun_fee_id,fenrun_fee_name,"
		+"fenrun_fee_type_flag,fenrun_serial_no,fenrun_fee_category1,fenrun_fee_category2,"
		+"fenrun_occur_date,fenrun_org_code,fenrun_org_name,fenrun_currency,fenrun_fee_amount,fenrun_fee_cycle_id,fenrun_fee_detail_id,"
		+"fenrun_create_type,fenrun_last_update_date,fenrun_last_update_time,fenrun_gather_flag,fenrun_need_gather,fenrun_channel_serial_no,"
		+"fenrun_create_date,fenrun_result_code,fenrun_result_info,fenrun_detail_no,fee_sn,fee_id,fee_name,fee_type_flag,serial_no,fee_category1,"
		+"fee_category2,occur_date,org_code,org_name,user_id,user_name,currency,occur_amount,standard_fare,fee_amount,favorable_amount,fee_cycle_id,"
		+"fee_detail_id,create_type,last_update_date,last_update_time,gather_flag,need_gather,result_code,result_info,channel_serial_no,create_date,"
		+"fees_base_type,detail_no,process_process_id,process_fee_trade_sn,process_fee_id,process_fee_category1,process_fee_category2,process_fee_org_code,"
		+"process_taker_code,process_transferee_code,process_transferor_code,process_trade_amount,process_currency,process_trade_date,process_trade_time,"
		+"process_compute_status,process_include_count_favorable,process_input_date,process_input_time,process_remark,process_reserve1,process_reserve2,"
		+"process_trading_channel,process_channel_serial_no,process_order_by_no,process_multitem_flag,process_detail_no,core_fee_trade_sn,core_fee_category1,"
		+"core_fee_category2,core_busi_type,core_busi_mark,core_msg_no,core_biz_serial_no,core_channel_serial_no,core_currency,core_trade_amount,"
		+"core_trade_date,core_trade_time,core_taker,core_taker_code,core_taker_sub,core_transferee,core_transferee_code,core_transferee_sub,"
		+"core_transferor,core_transferor_code,core_transferor_sub,core_record_status,core_trade_type_status,core_compute_status,core_remark,core_reserve1,"
		+"core_reserve2,core_merchants_type,core_trading_channel,core_collect_date,core_orig_busi_type,core_orig_busi_mark,core_orig_msg_no,core_orig_biz_serial_no,"
		+"core_orig_taker_code,core_collect_status,core_order_by_no,core_detail_no,core_orig_detail_no";
		//指令划付流水索引字段
		public static String FeeSettleOrderIndex="index_order_sn";
		//分润流水索引字段
		public final static String FenrunJourIndex="index_fee_sn";
		//费用流水明细数据查询 索引字段
    	public final String FeeJourDetailIndex ="index_fee_sn";
    	//费用流水索引字段 jinmeng
		public static String FeeJourIndex="index_fee_sn";
		//
		//分润流水索引字段 jinmeng
		public static String FenrunJourRealDetailIndex="index_fenrun_fee_sn";


		
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.IndexColumns
	* @Description:
	* @author zengli
	* @date 创建时间：2016年4月25日 下午3:58:24
	 */
	public interface IndexColumn{
		//指令划付流水索引字段
		public static String FeeSettleOrderIndex="index_order_sn";
		//分润流水索引字段
		public static String FenrunJourIndex="index_fee_sn";
		//费用流水明细查询字段
		public static String FeeJourDetailIndex="index_fee_sn";
		//费用流水索引字段 jinmeng
		public static String FeeJourIndex="index_fee_sn";
		//分润流水索引字段 jinmeng
		public static String FenrunJourRealDetailIndex="index_fenrun_fee_sn";
	}

	/**
	* @ClassName:com.hds.api.common.constant.CCSTables
	* @Description: CCSTable名称
	* @author zengli
	* @date 创建时间：2016年4月24日 下午7:44:22
	 */
	 public interface OTSTableName{
	    	//费用划付指令查询索引表/流水表
	    	public final String FeeSettleOrderIndex ="his_ccs_fee_settle_order_index";
	    	public final String FeeSettleOrderDetail ="his_ccs_fee_settle_order";
	    	
	    	//分润流水查询索引表/流水表
	    	public final String FenrunJourIndex ="his_ccs_fenrun_jour_index";
	    	public final String FenrunJourDetail ="his_ccs_fenrun_jour";
	    
	    	//费用流水明细数据查询索引表/流水表
	    	public final String FeeJourDetailIndex ="his_ccs_fee_jour_detail_index";
	    	public final String FeeJourDetailDetail ="his_ccs_fee_jour_detail";
	    	
	    	//费用流水查询索引表/流水表 jinmeng
	    	public final String FeeJourIndex ="his_ccs_fee_jour_index";
	    	public final String FeeJourDetail ="his_ccs_fee_jour";
	    	
	    	//分润明细查询索引表/流水表  jinmeng
	    	public final String FenrunJourRealDetailIndex ="his_ccs_fenrun_jour_real_detail_index";
	    	public final String FenrunJourRealDetailDetail ="his_ccs_fenrun_jour_real_detail";
	 }

	/**
	* @ClassName:com.hds.api.common.constant.FeeSettleOrderIndexEnum
	* @Description: 指令划付费用索引表    (主键枚举)
	* @author zengli
	* @date 创建时间：2016年4月24日 下午7:44:47
	 */
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
	* @Description:指令划付费用流水表   (主键枚举)
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
		
	/**
	* @ClassName:com.hds.api.common.constant.FenrunJourIndexEnum
	* @Description:   分润流水划付索引表  (主键枚举)
	* @author zengli
	* @date 创建时间：2016年4月26日 下午5:14:14
	 */	
	public enum FenrunJourIndexEnum {
			FEE_SN("fee_sn", PrimaryKeyType.STRING.name()),
			OCCUR_DATE("occur_date", PrimaryKeyType.STRING.name()),
			ORG_CODE("org_code", PrimaryKeyType.STRING.name()),
			FEE_CATEGORY1("fee_category1", PrimaryKeyType.STRING.name()),
			FEE_CATEGORY2("fee_category2", PrimaryKeyType.STRING.name());

			private String name;
			private String type;
			private FenrunJourIndexEnum(String name, String type) {
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
	* @ClassName:com.hds.api.common.constant.FenrunJourDetailEnum
	* @Description: 分润流水划付流水表  (主键枚举)
	* @author zengli
	* @date 创建时间：2016年4月26日 下午5:13:27
	*/
	public enum FenrunJourDetailEnum {
		FEE_SN("fee_sn", PrimaryKeyType.STRING.name());
		private String name;
		private String type;
		private FenrunJourDetailEnum(String name, String type) {
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
    * @ClassName:com.hds.api.common.constant.FeeJourDetailIndexEnum
    * @Description:  费用流水明细查询索引表  (主键枚举)
    * @author zengli
    * @date 创建时间：2016年4月28日 上午9:45:35
     */	
	public enum FeeJourDetailIndexEnum {
		FEE_SN("fee_sn", PrimaryKeyType.STRING.name()),
		CORE_TRADE_DATE("core_trade_date", PrimaryKeyType.STRING.name()),
		CORE_TAKER_CODE("core_taker_code", PrimaryKeyType.STRING.name()),
		FEE_CATEGORY1("fee_category1", PrimaryKeyType.STRING.name()),
		FEE_CATEGORY2("fee_category2", PrimaryKeyType.STRING.name());

		private String name;
		private String type;
		private FeeJourDetailIndexEnum(String name, String type) {
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
	* @ClassName:com.hds.api.common.constant.FeeJourDetailDetailEnum
	* @Description:费用流水明细查询明细表  (主键枚举)
	* @author zengli
	* @date 创建时间：2016年4月28日 上午9:54:08
	 */
	public enum FeeJourDetailDetailEnum {
		FEE_SN("fee_sn", PrimaryKeyType.STRING.name());
		private String name;
		private String type;
		private FeeJourDetailDetailEnum(String name, String type) {
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
	* @ClassName:com.hds.api.common.constant.FeeJourIndexEnum
	* @Description:   费用流水索引表  (主键枚举)
	* @author jinmeng
	* @date 创建时间：2016年4月27日 下午5:14:14
	 */	
	public enum FeeJourIndexEnum {
			FEE_SN("fee_sn", PrimaryKeyType.STRING.name()),
			OCCUR_DATE("occur_date", PrimaryKeyType.STRING.name()),
			ORG_CODE("org_code", PrimaryKeyType.STRING.name()),
			FEE_CATEGORY1("fee_category1", PrimaryKeyType.STRING.name()),
			FEE_CATEGORY2("fee_category2", PrimaryKeyType.STRING.name());

			private String name;
			private String type;
			private FeeJourIndexEnum(String name, String type) {
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
	* @ClassName:com.hds.api.common.constant.FeeJourDetailEnum
	* @Description: 费用流水明细 (主键枚举)
	* @author jinmeng
	* @date 创建时间：2016年4月27日 下午5:13:27
	*/
	public enum FeeJourDetailEnum {
		FEE_SN("fee_sn", PrimaryKeyType.STRING.name());
		private String name;
		private String type;
		private FeeJourDetailEnum(String name, String type) {
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
	* @ClassName:com.hds.api.common.constant.FenrunJourRealDetailIndexEnum
	* @Description:   分润mingx划付索引表  (主键枚举)
	* @author jinmeng
	* @date 创建时间：2016年4月28日 下午5:14:14
	 */	
	public enum FenrunJourRealDetailIndexEnum {
			FENRUN_FEE_SN("fenrun_fee_sn", PrimaryKeyType.STRING.name()),
			CORE_TRADE_DATE("core_trade_date", PrimaryKeyType.STRING.name()),
			CORE_TAKER_CODE("core_taker_code", PrimaryKeyType.STRING.name()),
			FEE_CATEGORY1("fee_category1", PrimaryKeyType.STRING.name()),
			FEE_CATEGORY2("fee_category2", PrimaryKeyType.STRING.name());

			private String name;
			private String type;
			private FenrunJourRealDetailIndexEnum(String name, String type) {
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
	* @ClassName:com.hds.api.common.constant.FenrunJourRealDetailDetailEnum
	* @Description: 分润明细划付流水表  (主键枚举)
	* @author jinmeng
	* @date 创建时间：2016年4月28日 下午5:13:27
	*/
	public enum FenrunJourRealDetailDetailEnum {
		FENRUN_FEE_SN("fenrun_fee_sn", PrimaryKeyType.STRING.name());
		private String name;
		private String type;
		private FenrunJourRealDetailDetailEnum(String name, String type) {
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

