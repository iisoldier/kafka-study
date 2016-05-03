#!bin/bash
now_date=`date +%Y%m%d%H%M%S`
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



	echo "开始向表${table_name} 进行cdp任务"
	sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}.json >run/${table_name}.json
	cdp job -start -p $pipeline_name -f run/${table_name}.json 2>&1 >log/cdp/${table_name}_${exec_dt}_${now_date}.log 
	ret=$?
	if [ $ret -ne 0 ];then
		echo "cdp 任务失败!向表{table_name}中传输数据失败...详情请查看日志"
		exit 1
	fi
	rm -r run/${table_name}.json


	echo "开始向表${table_name}_index01 进行cdp任务"
	sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}_index01.json >run/${table_name}_index01.json
	cdp job -start -p $pipeline_name -f run/${table_name}_index01.json 2>&1 >log/cdp/${table_name}_index01_${exec_dt}_${now_date}.log 
	ret_index=$?
	if [ $ret_index -ne 0 ];then
		echo "cdp 任务失败!向表{table_name}_index01中传输数据失败...详情请查看日志"
		exit 1
	fi
	rm -r run/${table_name}_index01.json

	echo "开始向表${table_name}_index02 进行cdp任务"
        sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}_index02.json >run/${table_name}_index02.json
        cdp job -start -p $pipeline_name -f run/${table_name}_index02.json 2>&1 >log/cdp/${table_name}_index02_${exec_dt}_${now_date}.log
        ret_index=$?
        if [ $ret_index -ne 0 ];then
                echo "cdp 任务失败!向表{table_name}_index02中传输数据失败...详情请查看日志"
                exit 1
        fi
        rm -r run/${table_name}_index02.json
        
        echo "开始向表${table_name}_index03 进行cdp任务"
        sed 's/$date_dt/'$exec_dt'/g' $json_path/${table_name}_index03.json >run/${table_name}_index03.json
        cdp job -start -p $pipeline_name -f run/${table_name}_index03.json 2>&1 >log/cdp/${table_name}_index03_${exec_dt}_${now_date}.log
        ret_index=$?
        if [ $ret_index -ne 0 ];then
                echo "cdp 任务失败!向表{table_name}_index03中传输数据失败...详情请查看日志"
                exit 1
        fi
        rm -r run/${table_name}_index03.json
      
	mv log/cdp/*.log log/cdp/ok
