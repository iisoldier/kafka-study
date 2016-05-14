

drop table if exists hds_log;
create table if not exists hds_log(
log_date       	  	string,
api_id    			string,   
request_time        string,                                                         
request_id  		string,
sys_id				string,
request_ip			string,
response_time		string,	
request_param		string,
request_result		string
) 
	partitioned by (dt string);
