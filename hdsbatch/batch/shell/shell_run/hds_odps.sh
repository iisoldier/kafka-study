#bin/bash
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
clean_dt=`date -d "$exec_dt -1 month" +"%Y%m%d"`

	[ ! -d run ] && mkdir -p run
	[ ! -d log/odps ] && mkdir -p log/odps
	[ ! -d log/odps/ok ] && mkdir -p log/odps/ok


	
	echo "执行 insert_o_${table_name}.sql"
	if [ ! -f $o_sql_path/insert_o_${table_name}.sql ];then
	        echo "insert_o_${table_name}.sql does not exist..."
	        exit 1
	fi
	sed 's/$date_dt/'$exec_dt'/g' $o_sql_path/insert_o_${table_name}.sql >run/insert_o_${table_name}.sql
	odpscmd --project $hds_project -f run/insert_o_${table_name}.sql 2>&1  >log/odps/insert_o_${table_name}_${exec_dt}_${now_date}.log
	reto=$?
	if [ reto -ne 0 ];then
		echo "odps 执行 insert_o_${table_name}.sql任务失败！详情请查看日志"
		exit 1
	fi
	rm -r run/insert_o_${table_name}.sql 
	


	
	echo "执行insert_f_${table_name}.sql"
	if [ ! -f $f_sql_path/insert_f_${table_name}.sql ];then
	        echo "insert_f_${table_name}.sql does not exist..."
	        exit 1
	fi

	sed 's/$date_dt/'$exec_dt'/g'  $f_sql_path/insert_f_${table_name}.sql > run/insert_f_${table_name}.sql
	odpscmd --project $hds_project -f run/insert_f_${table_name}.sql 2>&1 > log/odps/insert_f_${table_name}_${exec_dt}_${now_date}.log
	retf=$?
	if [ retf -ne 0 ];then
		echo "odps 执行 insert_o_${table_name}.sql任务失败！详情请查看日志"
		exit 1
	fi
	#### 删除30天前分区
	echo "F层 删除30天前分区"
	odpscmd --project $hds_project -e "alter table  f_${table_name} drop if     exists partition (dt = '$clean_dt');" 2>&1 > log/odps/delete_f_${table_name}_${exec_dt}_${now_date}.log 
	ret=$?
	if [ ret -ne 0 ];then
		echo "删除30天前分区任务失败！详情请查看日志"
		exit 1
	fi
	rm -r run/insert_f_${table_name}.sql
	

    mv log/odps/*.log  log/odps/ok
	
	echo "odps job success!!!"