#!bin/bash
now_date=`date +%Y%m%d%H%M%S`
cur_path="/ztapp/usr/hdsapp/deploy/batch/shell_manual/ccs"
base_path="/ztapp/usr/hdsapp/deploy/batch"
date_dt=$1
ccs_json_path="$base_path/tmp/ccs/$date_dt/json"

source $base_path/shell_manual/sys/hds_env.sh

cat $cur_path/ccs_ots_tablelist.txt |while read line
do
	table_name=`echo $line`
	echo "开始向表${table_name} 进行cdp任务"
	if [ ! -f $ccs_json_path/${table_name}.json ];then
	        echo "RC=129"
			echo "message=${table_name}文件不存在"
			exit 129
	fi
	
	cdp job -start -p $pipeline_name -f $ccs_json_path/${table_name}.json  >>$base_path/log/$date_dt/ccs/cdp/${date_dt}.log 2>&1
	ret=$?
	if [ $ret -ne 0 ];then
		echo "RC=130"
		echo "CDP任务执行失败，请检查"
		exit 130
	fi

done 
    

