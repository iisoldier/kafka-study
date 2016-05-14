#bin/bash
now_date=`date +%Y%m%d%k%M%S`
cur_path=`pwd`
table_name=$1
exec_dt=$2
clean_dt=`date -d "$exec_dt -1 month" +"%Y%m%d"`

source $cur_path/config.properties



	echo "执行 insert_o_${table_name}.sql"
	if [ ! -f run/insert_o_${table_name}.sql ];then
	        echo "insert_o_${table_name}.sql does not exist..."
	        exit 1
	fi
	odpscmd --project $hds_project -f run/insert_o_${table_name}.sql  >log/odps/insert_o_${table_name}_${exec_dt}_${now_date}.log 2>&1 
	reto=$?
	if [ reto -ne 0 ];then
		echo "odps 执行 insert_o_${table_name}.sql任务失败！详情请查看日志"
		exit 1
	fi


	echo "执行insert_f_${table_name}.sql"
	if [ ! -f run/insert_f_${table_name}.sql ];then
	        echo "insert_f_${table_name}.sql does not exist..."
	        exit 1
	fi
	odpscmd --project $hds_project -f run/insert_f_${table_name}.sql > log/odps/insert_f_${table_name}_${exec_dt}_${now_date}.log  2>&1 
	retf=$?
	if [ retf -ne 0 ];then
		echo "odps 执行 insert_o_${table_name}.sql任务失败！详情请查看日志"
		exit 1
	fi	
	
	echo "odps job success!!!"