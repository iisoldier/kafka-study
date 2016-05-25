#!bin/bash
date_dt=`date -d -1day +%Y%m%d`
cur_path="/ztapp/usr/hdsapp/deploy/batch/shell/sys"
base_path="/ztapp/usr/hdsapp/deploy/batch"


[ ! -d $base_path/tmp/sys/$date_dt/json/ ] && mkdir -p $base_path/tmp/sys/$date_dt/json
[ ! -d $base_path/log/$date_dt/ccs/cdp/ ] && mkdir -p $base_path/log/$date_dt/ccs/cdp
sys_json_path=$base_path/tmp/sys/$date_dt/json
source $cur_path/hds_env.sh
source $cur_path/getFunction.sh

transSqlTemplate $base_path/json/sys/hds_log2odps.json  $sys_json_path  hds_log2odps.json


table_name=hds_log2odps
source $base_path/shell/sys/hds_env.sh
  
	echo "开始向表${table_name} 进行cdp任务"
	if [ ! -f $sys_json_path/${table_name}.json ];then
	        echo "RC=129"
		echo "message=${table_name}.json文件不存在"
		exit 129
	fi
	cdp job -start -p $pipeline_name -f $sys_json_path/$table_name.json  >>$base_path/log/$date_dt/ccs/cdp/${date_dt}.log 2>&1
	ret=$?
	if [ $ret -ne 0 ];then
		echo "RC=130"
		echo "CDP任务执行失败，请检查"
		exit 130
	fi

	echo "RC=0"
	echo "message=JOB sys_ots2odpslog FINISEHD SUCCESSFULLY"
