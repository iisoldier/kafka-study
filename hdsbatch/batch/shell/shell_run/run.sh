#!bin/bash
# �������� 1
#
if [ $# -ne 1 ];
	echo "������������������һ������ ����ΪҪִ������ı���"
	exit 1
fi

table_name= $1
exec_date=`sh get_exec_dt.sh`
sh hds_odps.sh $table_name $exec_date
sh hds_cdp.sh $table_name $exec_date
