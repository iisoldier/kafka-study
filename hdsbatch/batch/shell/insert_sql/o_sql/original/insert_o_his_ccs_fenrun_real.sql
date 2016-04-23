
	alter table  o_his_ccs_fenrun_real drop if     exists partition (dt = 'dtonly');
	alter table  o_his_ccs_fenrun_real add  if not exists partition (dt = 'dtonly');

	insert into table  o_his_ccs_fenrun_real partition( dt = 'dtonly' )
	select

		fee_sn          , 
		fee_id          , 
		fee_name        , 
		fee_type_flag   , 
		serial_no       , 
		fee_category1   , 
		fee_category2   , 
		occur_date      , 
		org_code        , 
		org_name        , 
		currency        , 
		fee_amount     ,
		fee_cycle_id    , 
		fee_detail_id   , 
		create_type     , 
		last_update_date ,
		last_update_time ,
		gather_flag     , 
		need_gather     , 
		channel_serial_no ,
		create_date     , 
		result_code     , 
		result_info     ,
		detail_no       
		
		
	from 
	 ect888_ods_uat1.f_ccs_fenrun_jour_real 
	where a.dt = '$date_dt' ;
