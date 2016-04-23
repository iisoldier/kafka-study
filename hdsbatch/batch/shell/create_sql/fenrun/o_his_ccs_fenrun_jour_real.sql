


drop table if exists o_his_ccs_fenrun_jour_real;
create table if not exists o_his_ccs_fenrun_jour_real(
fee_sn          string, 
fee_id          string, 
fee_name        string, 
fee_type_flag   string, 
serial_no       string, 
fee_category1   string, 
fee_category2   string, 
occur_date      string, 
org_code        string, 
org_name        string, 
currency        string, 
fee_amount      decimal ,
fee_cycle_id    string, 
fee_detail_id   string, 
create_type     string, 
last_update_date string,
last_update_time string,
gather_flag     string, 
need_gather     string, 
channel_serial_no string,
create_date     string, 
result_code     string, 
result_info     string,
detail_no       string )
	partitioned by (dt string);