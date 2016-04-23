

drop table if exists f_his_ccs_fee_jour;
create table if not exists f_his_ccs_fee_jour(
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
occur_amount     string   ,
standard_fare    string  ,
fee_amount       string  ,
favorable_amount  string ,
fee_cycle_id     string   ,
fee_detail_id    string   ,
create_type      string   ,
last_update_date  string  ,
last_update_time  string  ,
gather_flag      string   ,
need_gather      string   ,
result_code      string   ,
result_info      string   ,
tohis_date       string   ,
channel_serial_no  string ,
create_date      string   ,
fees_base_type   string  ,
detail_no   	  string)
	partitioned by (dt string);