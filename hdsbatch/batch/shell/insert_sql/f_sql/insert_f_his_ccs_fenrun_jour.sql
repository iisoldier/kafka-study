
	alter table  f_his_ccs_fenrun_jour drop if     exists partition (dt = '$date_dt');
	alter table  f_his_ccs_fenrun_jour add  if not exists partition (dt = '$date_dt');

	insert into table  f_his_ccs_fenrun_jour partition( dt = '$date_dt' )
	select
		
		case when fee_sn is null then '' else fee_sn end,
		case when fee_id is null then '' else fee_id end,
		case when fee_name is null then '' else fee_name end,
		case when fee_type_flag is null then '' else fee_type_flag end,
		case when serial_no is null then '' else serial_no end,
		case when fee_category1 is null then '' else fee_category1 end,
		case when fee_category2 is null then '' else fee_category2 end,
		case when length(trim(occur_date))==10 then concat(concat(substr(occur_date,1,4),substr(occur_date,6,2)),substr(occur_date,9,2))
					when occur_date is null then '' else occur_date end,
		case when org_code is null then '' else org_code end,
		case when org_name is null then '' else org_name end,
		case when currency is null then '' else currency end,
		case when fee_amount is null then '' else fee_amount end,
		case when fee_cycle_id is null then '' else fee_cycle_id end,
		case when fee_detail_id is null then '' else fee_detail_id end,
		case when create_type is null then '' else create_type end,
		case when last_update_date is null then '' else last_update_date end,
		case when last_update_time is null then '' else last_update_time end,
		case when gather_flag is null then '' else gather_flag end,
		case when need_gather is null then '' else need_gather end,
		case when tohis_date is null then '' else tohis_date end,
		case when channel_serial_no is null then '' else channel_serial_no end,
		case when create_date is null then '' else create_date end,
		case when result_code is null then '' else result_code end,
		case when result_info is null then '' else result_info end,
		case when detail_no is null then '' else detail_no end
		
	from 
	 o_his_ccs_fenrun_jour
	where dt = 'dtonly';
