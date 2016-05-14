#!bin/bash
now_date=`date +%Y%m%d%H%M%S`
cur_path=`pwd`
exec_dt=$1

source $cur_path/config.properties
cat ccs_tablelist.txt |while read line
do
	table_name=`echo $line`
	echo "开始向表${table_name} 进行cdp任务"
	if [ ! -f run/${table_name}.json ];then
	        echo "${table_name}.json does not exist..."
	        exit 1
	fi
	cdp job -start -p $pipeline_name -f run/${table_name}.json  >log/cdp/${table_name}_${exec_dt}_${now_date}.log 2>&1
	ret=$?
	if [ $ret -ne 0 ];then
		echo "cdp 任务失败!向表${table_name}中传输数据失败...详情请查看日志"
		exit 1
	fi



	indexNum=0
	while [ $indexNum -lt 10 ] 
	do
		indexNum=`expr $indexNum + 1`
		if [ ! -f run/${table_name}_idex0$indexNum.json ];then 
			echo "OK  $indexNum has already overflow "
			break
		fi
		echo "开始向表${table_name}_index0$indexNum 进行cdp任务"
		
		cdp job -start -p $pipeline_name -f run/${table_name}_index0$indexNum.json >log/cdp/${table_name}_index0${indexNum}_${exec_dt}_${now_date}.log 2>&1
		ret_index=$?
		if [ $ret_index -ne 0 ];then
			echo "cdp 任务失败!向表${table_name}_index0$indexNum中传输数据失败...详情请查看日志"
			exit 1
		fi
	done

done     
	echo "cdp job success!"