
	alter table  f_his_ccs_fee_settle_order drop if     exists partition (dt = '$date_dt');
	alter table  f_his_ccs_fee_settle_order add  if not exists partition (dt = '$date_dt');

	insert into table  f_his_ccs_fee_settle_order partition( dt = '$date_dt' )
	select

		case when order_sn is null then  '' else order_sn end,
		case when fee_gather_id is null then  '' else fee_gather_id end,
		case when currency is null then  '' else currency end,
		case when credit_org_code is null then  '' else credit_org_code end,
		case when credit_account_type is null then  '' else credit_account_type end,
		case when credit_account_no is null then  '' else credit_account_no end,
		case when debit_org_code is null then  '' else debit_org_code end,
		case when debit_account_type is null then  '' else debit_account_type end,
		case when debit_account_no is null then  '' else debit_account_no end,
		case when trade_amount is null then  '' else cast (trade_amount as string) end,
		case when success_amount is null then  '' else cast (success_amount as string) end,
		case when order_type is null then  '' else order_type end,
		case when deal_state is null then  '' else deal_state end,
		case when trans_type is null then  '' else trans_type end,
		case when fail_count is null then  '' else fail_count end,
		case when occur_date is null then  '' else occur_date end,
		case when length(trim(occur_date))==10 then concat(concat(substr(occur_date,1,4),substr(occur_date,6,2)),substr(occur_date,9,2)) 
					when occur_date  is null then '' else occur_date  end,
		case when result_code is null then  '' else result_code end,
		case when result_info is null then  '' else result_info end,
		case when reserve1 is null then  '' else reserve1 end,
		case when reserve2 is null then  '' else reserve2 end,
		case when reserve3 is null then  '' else reserve3 end,
		case when tohis_date is null then  '' else tohssis_date end
		
	from 
	 o_his_ccs_fee_settle_order
	where dt = 'dtonly';
