#bin/bash


cur_path="/ztapp/usr/hdsapp/deploy/batch/shell_manual/ccs"
base_path="/ztapp/usr/hdsapp/deploy/batch"
date_dt=$1
clean_dt=`date -d "$date_dt -1 month" +"%Y%m%d"`
source $base_path/shell_manual/sys/hds_env.sh


cat $cur_path/ccs_odps_tablelist.txt |while read line
do
	table_name=`echo $line`
	
	echo "F层 删除f_${table_name} 30天前分区"
	odpscmd --project $hds_odps_project -e "alter table f_${table_name} drop if exists partition(dt = '$clean_dt');"  >>$base_path/log/$date_dt/ccs/odps/${date_dt}.log 2>&1
	ret=$?
	if [ $ret -ne 0 ];then
		echo "RC=131"
		echo "ODPS任务执行失败，请检查"
		exit 131
	fi
done
	echo "hds ccs clean job success!!!"
