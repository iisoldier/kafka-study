#!bin/bash
now_date=`date +%Y%m%d`
cur_path=`pwd`
base_path=$(dirname $cur_path)
o_sql_path="$base_path/insert_sql/o_sql"
f_sql_path="$base_path/insert_sql/f_sql"
json_path="$base_path/json"
hds_project=ydt_dat_hds
pipeline_name=odps_ots_test
table_name=$1
exec_dt=$2


[ ! -d log/cdp ] && mkdir -p log/cdp
[ ! -d log/cdp/ok ] && mkdir -p log/cdp/ok



	echo "${table_name}.json"
	sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}.json >run/${table_name}.json
	cdp job -start -p $pipeline_name -f run/${table_name}.json >log/cdp/${table_name}_${exec_dt}.log 2>&1
	rm -r run/${table_name}.json


if [ $table_name != "his_fee_jour_union" ];then	

	echo "${table_name}_index.json"
	sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}_index.json >run/${table_name}_index.json
	cdp job -start -p $pipeline_name -f run/${table_name}_index.json >log/cdp/${table_name}_index_${exec_dt}.log 2>&1
	rm -r run/${table_name}_index.json
else			
	echo "${table_name}_occur_index.json"
	sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}_occur_index.json >run/${table_name}_occur_index.json
	cdp job -start -p $pipeline_name -f run/${table_name}_occur_index.json >log/cdp/${table_name}_occur_index_${exec_dt}.log 2>&1
	rm -r run/${table_name}_occur_index.json

	echo "${table_name}_trade_index.json"
	sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}_trade_index.json >run/${table_name}_trade_index.json
	cdp job -start -p $pipeline_name -f run/${table_name}_trade_index.json >log/cdp/${table_name}_trade_index_${exec_dt}.log 2>&1
	rm -r run/${table_name}_trade_index.json

fi
mv log/cdp/*.log log/cdp/ok
echo "success!!!"
