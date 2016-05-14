#bin/bash
now_date=`date +%Y%m%d%k%M%S`
cur_path=`pwd`
exec_dt=$1
clean_dt=`date -d "$exec_dt -1 month" +"%Y%m%d"`

source $cur_path/config.properties

cat ccs_tablelist.txt |while read line
do
    table_name=`echo $line`

	echo "执行insert_f_${table_name}.sql"
	if [ ! -f run/insert_f_${table_name}.sql ];then
	        echo "insert_f_${table_name}.sql does not exist..."
	        exit 1
	fi
	odpscmd --project $ODPS_project -f run/insert_f_${table_name}.sql > log/odps/insert_f_${table_name}_${exec_dt}_${now_date}.log  2>&1 
	retf=$?
	if [ retf -ne 0 ];then
		echo "odps 执行 insert_o_${table_name}.sql任务失败！详情请查看日志"
		exit 1
	fi	
done	
	echo "odps job success!!!"