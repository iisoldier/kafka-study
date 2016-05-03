
drop table if exists f_his_fee_settle_order;
create table if not exists f_his_fee_settle_order(
order_sn       	  string,
fee_gather_id    string,   
currency         string,                                                         
credit_org_code  string,                                                         
credit_account_type  string,                                                         
credit_account_no  string,                                                         
debit_org_code   string,                                                         
debit_account_type  string,                                                        
debit_account_no  string,                                                         
trade_amount     string,                                                        
success_amount   string,                                                       
order_type       string,                                                         
deal_state       string,                                                         
trans_type       string,                                                         
fail_count       string,                                                         
occur_date       string,                                                         
occur_time       string,                                                     
result_code      string,                                                       
result_info      string,                                                        
reserve1         string,                                                         
reserve2         string,                                                        
reserve3         string,
tohis_date       string) 
	partitioned by (dt string);