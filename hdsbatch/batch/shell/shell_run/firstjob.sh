#!bin/bash

cur_path=`pwd`
base_path=$(dirname $cur_path)
o_sql_path="$base_path/insert_sql/o_sql"
f_sql_path="$base_path/insert_sql/f_sql"
json_path="$base_path/json"

source $cur_path/config.properties

	if [ $# -ne 1 ];then 
		echo "请传入需要执行的日期参数，格式为yyyymmdd"
		exit 1
	fi

	run_dt=$1

	[ ! -d run ] && mkdir -p run
	[ ! -d log/odps ] && mkdir -p log/odps
	[ ! -d log/odps/ok ] && mkdir -p log/odps/ok
	[ ! -d log/cdp ] && mkdir -p log/cdp
	[ ! -d log/cdp/ok ] && mkdir -p log/cdp/ok
	
	cp $o_sql_path/*.sql run/
	cp $f_sql_path/*.sql run/
	cp $json_path/*.json run/
	
	sed -i 's#$date_dt#'$run_dt'#g' run/*.sql
	sed -i 's#$date_dt#'$run_dt'#g' run/*.json
	
	sed -i 's#odps_accessId#'$ODPS_accessId'#g' run/*.json
	sed -i 's#odps_accesskey#'$ODPS_accesskey'#g'run/*.json
	sed -i 's#odps_project#'$ODPS_project'#g' run/*.json
	sed -i 's#ots_endpoint#'$OTS_endpoint'#g' run/*.json
	sed -i 's#ots_accessId#'$OTS_accessId'#g' run/*.json
	sed -i 's#ots_accessKey#'$OTS_accessKey'#g' run/*.json
	sed -i 's#ots_instanceName#'$OTS_instanceName'#g' run/*.json
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	