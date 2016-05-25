#bin/bash
now_date=`date +%Y%m%d%k%M%S`
cur_path="/ztapp/usr/hdsapp/deploy/batch/shell/ccs"
base_path="/ztapp/usr/hdsapp/deploy/batch"

date_dt=$1
load_sql_path="$base_path/tmp/ccs/$date_dt/load_sql"

source $base_path/shell/sys/hds_env.sh

cat $cur_path/ccs_odps_tablelist.txt |while read line
do
    table_name=`echo $line`
	
	echo "执行 insert_o_${table_name}.sql"

	if [ ! -f $load_sql_path/insert_o_${table_name}.sql ];then
		echo "RC=129"
		echo "message=insert_o_${table_name}.sql文件不存在"
		exit 129
	fi
	odpscmd --project $ods_odps_project -f $load_sql_path/insert_o_${table_name}.sql >> $base_path/log/$date_dt/ccs/odps/${date_dt}.log 2>&1 
	ret=$?
	if [ $ret -ne 0 ];then
		echo "RC=131"
		echo "ODPS任务执行失败，请检查"
		exit 131
	fi

done

