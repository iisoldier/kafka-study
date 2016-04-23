
	alter table  f_his_ccs_fee_settle_order drop if     exists partition (dt = '$date_dt');
	alter table  f_his_ccs_fee_settle_order add  if not exists partition (dt = '$date_dt');

	insert into table  f_his_ccs_fee_settle_order partition( dt = '$date_dt' )
	select
		
	
		case when order_sn is null then '' else order_sn end,
		fee_gather_id,   
		currency,                                                            
		    
		case when credit_org_code is null then '' else credit_org_code end,		
		credit_account_type,                                                       
		credit_account_no,                                                      
		
		case when debit_org_code is null then '' else debit_org_code end,					
		debit_account_type,                                                       
		debit_account_no,                                                       
		 
		cast (trade_amount as string),
		cast (success_amount as string),		
		order_type,                                                             
		
		case when deal_state is null then '' else deal_state end,		
		trans_type,                                                            
		fail_count,                                                          
		
		case when length(trim(occur_date))==10 then concat(concat(substr(occur_date,1,4),substr(occur_date,6,2)),substr(occur_date,9,2)) else occur_date end,		
		occur_time,                                                          
		result_code,                                                        
		result_info,                                                          
		reserve1,                                                           
		reserve2,                                                         
		reserve3,
		tohis_date
		
		
	from 
	 o_his_ccs_fee_settle_order
	where dt = '$date_dt';
