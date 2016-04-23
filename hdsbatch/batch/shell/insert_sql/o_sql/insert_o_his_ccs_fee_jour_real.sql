
	alter table  o_his_ccs_fee_jour_real drop if     exists partition (dt = 'dtonly');
	alter table  o_his_ccs_fee_jour_real add  if not exists partition (dt = 'dtonly');

	insert into table  o_his_ccs_fee_jour_real partition( dt = '$dtonly' )
	select
		fee_sn              ,
		fee_id              ,
		fee_name            ,
		fee_type_flag       ,
		serial_no           ,
		fee_category1       ,
		fee_category2       ,
		occur_date          ,
		org_code            ,
		org_name            ,
		user_id             ,
		user_name           ,
		currency            ,
		occur_amount       ,
		standard_fare      ,
		fee_amount         ,
		favorable_amount   ,
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
	 ect888_ods_uat1.f_ccs_fee_jour_real  
	where dt = '$date_dt' ;
