
	alter table  f_his_ccs_fee_real_union drop if     exists partition (dt = '$date_dt');
	alter table  f_his_ccs_fee_real_union add  if not exists partition (dt = '$date_dt');

	insert into table  f_his_ccs_fee_real_union partition( dt = '$date_dt' )
	select
		
		case when fee_sn is null then '' else fee_sn end,
		fee_id              ,
		fee_name            ,
		fee_type_flag       ,
		serial_no           ,
		case when fee_category1 is null then '' else fee_category1 end,
		case when fee_category2 is null then '' else fee_category2 end,		
		case when length(trim(occur_date))==10 then concat(concat(substr(occur_date,1,4),substr(occur_date,6,2)),substr(occur_date,9,2)) when occur_date is null then '' else occur_date end,
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
		detail_no   	  ,
		
		process_process_id      ,  
		process_fee_trade_sn    ,  
		process_fee_id          ,  
		case when process_fee_category1 is null then '' else process_fee_category1 end,
		case when process_fee_category2 is null then '' else process_fee_category2 end,
		case when process_fee_org_code is null then '' else process_fee_org_code end,
		case when process_taker_code is null then '' else process_taker_code end,				
		process_transferee_code ,  
		process_transferor_code ,  
		process_trade_amount    , 
		process_currency        ,  		
		case when length(trim(process_trade_date))==10 then concat(concat(substr(process_trade_date,1,4),substr(process_trade_date,6,2)),substr(process_trade_date,9,2)) when process_trade_date is null then '' else process_trade_date end,
		process_trade_time      ,  
		process_compute_status  ,  
		process_include_count_favorable ,
		process_input_date      ,  
		process_input_time      ,  
		process_remark          ,  
		process_reserve1        ,  
		process_reserve2        ,		   
		process_trading_channel ,  
		process_channel_serial_no ,
		process_order_by_no     ,  
		process_multitem_flag   ,
		process_detail_no       ,

		core_fee_trade_sn     ,                                                         
		case when core_fee_category1 is null then '' else core_fee_category1 end,
		case when core_fee_category2 is null then '' else core_fee_category2 end,
		core_busi_type        ,            
		core_busi_mark        ,                                                         
		core_msg_no           ,                                                         
		core_biz_serial_no    ,                                                         
		core_channel_serial_no  ,                                                         
		core_currency         ,                                                         
		cast (core_trade_amount as string)    ,                                                        		
		case when length(trim(core_trade_date))==10 then concat(concat(substr(core_trade_date,1,4),substr(core_trade_date,6,2)),substr(core_trade_date,9,2)) when core_trade_date is null then '' else core_trade_date end,
		core_trade_time       ,                                                         
		core_taker            ,                                                         		
		case when core_taker_code is null then '' else core_taker_code end,
		core_taker_sub        ,                                                         
		core_transferee       ,                                                         
		core_transferee_code  ,                                                         
		core_transferee_sub   ,                                                         
		core_transferor       ,                                                         
		core_transferor_code  ,                                                         
		core_transferor_sub   ,                                                         
		core_record_status    ,                                                         
		core_trade_type_status  ,                                                       
		core_compute_status   ,                                                         
		core_remark           ,                                                         
		core_reserve1         ,                                                        
		core_reserve2         ,                                                         
		core_merchants_type   ,                                                         
		core_trading_channel  ,                                                         
		core_collect_date     ,                                                         
		core_orig_busi_type   ,                                                         
		core_orig_busi_mark   ,                                                         
		core_orig_msg_no      ,                                                         
		core_orig_biz_serial_no  ,                                                         
		core_orig_taker_code  ,                                                        
		core_collect_status   ,                                                         		                                                      
		core_order_by_no      ,                                                        
		core_detail_no        ,                                                         
		core_orig_detail_no   ,			
		fenrun_fee_sn         , 
		fenrun_fee_id         , 
		fenrun_fee_name       , 
		fenrun_fee_type_flag  , 
		fenrun_serial_no      ,  			
		case when fenrun_fee_category1 is null then '' else fenrun_fee_category1 end,
		case when fenrun_fee_category2 is null then '' else fenrun_fee_category2 end,
		case when length(trim(fenrun_occur_date))==10 then concat(concat(substr(fenrun_occur_date,1,4),substr(fenrun_occur_date,6,2)),substr(fenrun_occur_date,9,2)) when fenrun_occur_date is null then '' else fenrun_occur_date end,
		case when fenrun_org_code is null then '' else fenrun_org_code end,				
		fenrun_org_name        , 
		fenrun_currency        , 
		fenrun_fee_amount       ,
		fenrun_fee_cycle_id    , 
		fenrun_fee_detail_id   , 
		fenrun_create_type     , 
		fenrun_last_update_date ,
		fenrun_last_update_time ,
		fenrun_gather_flag     , 
		fenrun_need_gather     , 
		fenrun_channel_serial_no ,
		fenrun_create_date     , 
		fenrun_result_code     , 
		fenrun_result_info     ,
		fenrun_detail_no       
	from 
	 o_his_ccs_fee_real_union
	where dt = 'dtonly';
