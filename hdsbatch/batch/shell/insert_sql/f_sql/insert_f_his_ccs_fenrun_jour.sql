
	alter table  f_his_ccs_fenrun_jour drop if     exists partition (dt = '$date_dt');
	alter table  f_his_ccs_fenrun_jour add  if not exists partition (dt = '$date_dt');

	insert into table  f_his_ccs_fenrun_jour partition( dt = '$date_dt' )
	select
		
				
		fee_sn         , 
		fee_id         , 
		fee_name       , 
		fee_type_flag  , 
		serial_no      ,  			
		case when fee_category1 is null then '' else fee_category1 end,
		case when fee_category2 is null then '' else fee_category2 end,
		case when length(trim(occur_date))==10 then concat(concat(substr(occur_date,1,4),substr(occur_date,6,2)),substr(occur_date,9,2)) when occur_date is null then '' else occur_date end,
		case when org_code is null then '' else org_code end,				
		org_name        , 
		currency        , 
		fee_amount       ,
		fee_cycle_id    , 
		fee_detail_id   , 
		create_type     , 
		last_update_date ,
		last_update_time ,
		gather_flag     , 
		need_gather     , 
		tohis_date		,
		channel_serial_no ,
		create_date     , 
		result_code     , 
		result_info     ,
		detail_no       
	from 
	 o_his_ccs_fenrun_jour
	where dt = 'dtonly';
