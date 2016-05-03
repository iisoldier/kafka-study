drop table if exists o_his_ccs_fenrun_jour_real_detail;
create table if not exists o_his_ccs_fenrun_jour_real_detail(

fenrun_fee_sn          string, 
fenrun_fee_id          string, 
fenrun_fee_name        string, 
fenrun_fee_type_flag   string, 
fenrun_serial_no       string, 
fenrun_fee_category1   string, 
fenrun_fee_category2   string, 
fenrun_occur_date      string, 
fenrun_org_code        string, 
fenrun_org_name        string, 
fenrun_currency        string, 
fenrun_fee_amount      decimal ,
fenrun_fee_cycle_id    string, 
fenrun_fee_detail_id   string, 
fenrun_create_type     string, 
fenrun_last_update_date string,
fenrun_last_update_time string,
fenrun_gather_flag     string, 
fenrun_need_gather     string, 
fenrun_channel_serial_no string,
fenrun_create_date     string, 
fenrun_result_code     string, 
fenrun_result_info     string,
fenrun_detail_no       string,

fee_sn           string   ,
fee_id           string   ,
fee_name         string   ,
fee_type_flag    string   ,
serial_no        string   ,
fee_category1    string   ,
fee_category2    string   ,
occur_date       string   ,
org_code         string   ,
org_name         string   ,
user_id          string   ,
user_name        string   ,
currency         string   ,
occur_amount     decimal  ,
standard_fare    decimal  ,
fee_amount       decimal  ,
favorable_amount  decimal ,
fee_cycle_id     string   ,
fee_detail_id    string   ,
create_type      string   ,
last_update_date  string  ,
last_update_time  string  ,
gather_flag      string   ,
need_gather      string   ,
result_code      string   ,
result_info      string   ,
channel_serial_no  string ,
create_date      string   ,
fees_base_type   string  ,
detail_no   	  string,


process_process_id      string,  
process_fee_trade_sn    string,  
process_fee_id          string,  
process_fee_category1   string,  
process_fee_category2   string,  
process_fee_org_code    string,  
process_taker_code      string,  
process_transferee_code string,  
process_transferor_code string,  
process_trade_amount    string, 
process_currency        string,  
process_trade_date      string,  
process_trade_time      string,  
process_compute_status  string,  
process_include_count_favorable string,
process_input_date      string,  
process_input_time      string,  
process_remark          string,  
process_reserve1        string,  
process_reserve2        string,    
process_trading_channel string,  
process_channel_serial_no string,
process_order_by_no     string,  
process_multitem_flag   string,
process_detail_no       string,


core_fee_trade_sn     string,   
core_fee_category1    string,   
core_fee_category2    string,   
core_busi_type        string,   
core_busi_mark        string,   
core_msg_no           string,   
core_biz_serial_no    string,   
core_channel_serial_no  string, 
core_currency         string,   
core_trade_amount     decimal , 
core_trade_date       string,   
core_trade_time       string,   
core_taker            string,   
core_taker_code       string,   
core_taker_sub        string,   
core_transferee       string,   
core_transferee_code  string,   
core_transferee_sub   string,   
core_transferor       string,   
core_transferor_code  string,   
core_transferor_sub   string,   
core_record_status    string,   
core_trade_type_status  string, 
core_compute_status   string,   
core_remark           string,   
core_reserve1         string,   
core_reserve2         string,   
core_merchants_type   string,   
core_trading_channel  string,   
core_collect_date     string,   
core_orig_busi_type   string,   
core_orig_busi_mark   string,   
core_orig_msg_no      string,   
core_orig_biz_serial_no  string,
core_orig_taker_code  string,   
core_collect_status   string,   
core_order_by_no      string,
core_detail_no        string,                                            
core_orig_detail_no   string


)
partitioned by (dt string);