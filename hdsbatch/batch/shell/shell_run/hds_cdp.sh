#!bin/bash
now_date=`date +%Y%m%d%k%M%S`
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



	echo "$��ʼ���{table_name} ����cdp����"
	sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}.json >run/${table_name}.json
	cdp job -start -p $pipeline_name -f run/${table_name}.json 2>&1 >log/cdp/${table_name}_${exec_dt}_${now_date}.log 
	ret=$?
	if [ ret -ne 0 ];then
		echo "cdp ����ʧ��!���{table_name}�д�������ʧ��...������鿴��־"
		exit 1
	fi
	rm -r run/${table_name}.json


	echo "$��ʼ���{table_name}_index ����cdp����"
	sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}_index.json >run/${table_name}_index.json
	cdp job -start -p $pipeline_name -f run/${table_name}_index.json 2>&1 >log/cdp/${table_name}_index_${exec_dt}_${now_date}.log 
	ret_index=$?
	if [ ret_index -ne 0 ];then
		echo "cdp ����ʧ��!���{table_name}_index�д�������ʧ��...������鿴��־"
		exit 1
	fi
	rm -r run/${table_name}_index.json

	
mv log/cdp/*.log log/cdp/ok
echo "cdp job success!!!"
