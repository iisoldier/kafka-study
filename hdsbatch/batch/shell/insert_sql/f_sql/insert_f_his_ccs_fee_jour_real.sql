
	alter table  f_his_ccs_fee_jour_real drop if     exists partition (dt = '$date_dt');
	alter table  f_his_ccs_fee_jour_real add  if not exists partition (dt = '$date_dt');

	insert into table  f_his_ccs_fee_jour_real partition( dt = '$date_dt' )
	select
		case when fee_sn is null then '' else fee_sn end,
		fee_id              ,
		fee_name            ,
		fee_type_flag       ,
		serial_no           ,
		case when fee_category1 is null then '' else fee_category1 end,
		case when fee_category2 is null then '' else fee_category2 end,		
		case when length(trim(occur_date))==10 then concat(concat(substr(occur_date,1,4),substr(occur_date,6,2)),substr(occur_date,9,2)) else occur_date end,
		case when org_code is null then '' else org_code end,		
		org_name            ,
		user_id             ,
		user_name           ,
		currency            ,
		cast(occur_amount as string)      ,
		cast (standard_fare as string)     ,
		cast(fee_amount as string)        ,
		cast (favorable_amount as string)  ,
		fee_cycle_id        ,
		fee_detail_id       ,
		create_type         ,
		last_update_date    ,
		last_update_time    ,
		gather_flag         ,
		need_gather         ,
		result_code         ,
		result_info         ,		
		channel_serial_no   ,
		create_date         ,
		fees_base_type     ,
		detail_no   	  


		
	from 
	 o_his_ccs_fee_jour_real  
	where dt = '$date_dt' ;
 	  