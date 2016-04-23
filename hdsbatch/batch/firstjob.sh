#!bin/bash

if [ $# -ne 1 ];then 
	echo "请传入需要执行的日期参数，格式为yyyymmdd"
	exit 1
fi

run_dt=$1
exec_dt=$(mysql -N -e "select Min(exec_dt) from dtPara where state=0")

if [ $run_dt = $exec_dt ];then
	echo "OK, Let's go!"
else
	echo "Error!!!  Please check it out!!!需要执行的日期参数，格式为yyyymmdd"	
fi
