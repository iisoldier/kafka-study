

drop table if exists o_his_ccs_fee_trade_core_process;
create table if not exists o_his_ccs_fee_trade_core_process(
process_id      string,  
fee_trade_sn    string,  
fee_id          string,  
fee_category1   string,  
fee_category2   string,  
fee_org_code    string,  
taker_code      string,  
transferee_code string,  
transferor_code string,  
trade_amount    string, 
currency        string,  
trade_date      string,  
trade_time      string,  
compute_status  string,  
include_count_favorable string,
input_date      string,  
input_time      string,  
remark          string,  
reserve1        string,  
reserve2        string,
tohis_date		string,    
trading_channel string,  
channel_serial_no string,
order_by_no     string,  
multitem_flag   string,
detail_no       string )
	partitioned by (dt string);