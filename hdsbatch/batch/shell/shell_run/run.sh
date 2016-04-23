#!bin/bash
# 参数个数 1
#
if [ $# -ne 1 ];
	echo "参数个数错误，请输入一个参数 参数为要执行任务的表名"
	exit 1
fi

table_name= $1
exec_date=`sh get_exec_dt.sh`
sh hds_odps.sh $table_name $exec_date
sh hds_cdp.sh $table_name $exec_date
