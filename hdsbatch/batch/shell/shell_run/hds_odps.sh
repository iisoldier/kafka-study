#bin/bash
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
clean_dt=`date -d "$exec_dt -1 month" +"%Y%m%d"`

	[ ! -d run ] && mkdir -p run
	[ ! -d log/odps ] && mkdir -p log/odps
	[ ! -d log/odps/ok ] && mkdir -p log/odps/ok


	
	echo "insert_o_${table_name}.sql"
	if [ ! -f $o_sql_path/insert_o_${table_name}.sql ];then
	        echo "insert_o_${table_name}.sql does not exist..."
	        exit 1
	fi
	sed 's/$date_dt/'$exec_dt'/g' $o_sql_path/insert_o_${table_name}.sql >run/insert_o_${table_name}.sql
	odpscmd --project $hds_project -f run/insert_o_${table_name}.sql  >log/odps/insert_o_${table_name}_${exec_dt}.log 2>&1
	rm -r run/insert_o_${table_name}.sql 
	


	
	echo "insert_f_${table_name}.sql"
	if [ ! -f $f_sql_path/insert_f_${table_name}.sql ];then
	        echo "insert_f_${table_name}.sql does not exist..."
	        exit 1
	fi

	sed 's/$date_dt/'$exec_dt'/g'  $f_sql_path/insert_f_${table_name}.sql > run/insert_f_${table_name}.sql
	odpscmd --project $hds_project -f run/insert_f_${table_name}.sql > log/odps/insert_f_${table_name}_${exec_dt}.log 2>&1
	#### 删除30天前分区
	odpscmd --project $hds_project -e "alter table  f_${table_name} drop if     exists partition (dt = '$clean_dt');" > log/odps/insert_f_${table_name}_${exec_dt}.log 2>&1
	rm -r run/insert_f_${table_name}.sql
	

        mv log/odps/*.log  log/odps/ok
