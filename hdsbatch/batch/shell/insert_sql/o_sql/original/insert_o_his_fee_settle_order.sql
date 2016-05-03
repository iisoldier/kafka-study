
	alter table  o_his_fee_settle_order drop if     exists partition (dt = 'dtonly');
	alter table  o_his_fee_settle_order add  if not exists partition (dt = 'dtonly');

	insert into table  o_his_fee_settle_order partition( dt = 'dtonly' )
	select
		order_sn,
		fee_gather_id,   
		currency,                                                            
		credit_org_code,                                                      
		credit_account_type,                                                       
		credit_account_no,                                                      
		debit_org_code,                                                         
		debit_account_type,                                                       
		debit_account_no,                                                       
		trade_amount,                                                           
		success_amount,                                                         
		order_type,                                                             
		deal_state,                                                           
		trans_type,                                                            
		fail_count,                                                          
		occur_date,                                                           
		occur_time,                                                          
		result_code,                                                        
		result_info,                                                          
		reserve1,                                                           
		reserve2,                                                         
		reserve3,
		tohis_date
	from 
	 ect888_ods_uat1.f_ccs_his_fee_settle_order
	where dt = '$date_dt';
