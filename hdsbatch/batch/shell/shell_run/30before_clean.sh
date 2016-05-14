#bin/bash
now_date=`date +%Y%m%d%k%M%S`
hds_project=ydt_dat_hds
table_name=$1
exec_dt=$2
clean_dt=`date -d "$exec_dt -1 month" +"%Y%m%d"`


	#### 删除30天前分区
	echo "F层 删除30天前分区"
	odpscmd --project $hds_project -e "alter table  f_${table_name} drop if     exists partition (dt = '$clean_dt');" 2>&1 > log/odps/delete_f_${table_name}_${exec_dt}_${now_date}.log
	ret=$?
	if [ ret -ne 0 ];then
		echo "删除30天前分区任务失败！详情请查看日志"
		exit 1
	fi

	echo "odps clean job success!!!"