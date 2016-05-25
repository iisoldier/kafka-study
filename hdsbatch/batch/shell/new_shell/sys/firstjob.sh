#!bin/bash

cur_path="/ztapp/usr/hdsapp/deploy/batch/shell/sys"
base_path="/ztapp/usr/hdsapp/deploy/batch"
o_sql_path="$base_path/sql/ccs/load_sql"
f_sql_path="$base_path/sql/ccs/model_sql"
json_path="$base_path/json"
tmp_path="$base_path/tmp"



source $cur_path/hds_env.sh
source $cur_path/getFunction.sh

export date_dt=`sh $cur_path/get_exec_dt.sh`

if [ $date_dt = "NULL"  ]
	then
	echo "RC=127"
	echo "RDS数据为空，请检查"
	exit 127
fi

echo "开始执行跑批，当前跑批日期为 $date_dt"
mysql -N -e "update t_dtpara set start_time=sysdate() where exec_dt=$date_dt"

[ ! -d $tmp_path/ccs/$date_dt/load_sql ] && mkdir -p $tmp_path/ccs/$date_dt/load_sql
[ ! -d $tmp_path/ccs/$date_dt/model_sql ] && mkdir -p $tmp_path/ccs/$date_dt/model_sql
[ ! -d $tmp_path/ccs/$date_dt/json/ ] && mkdir -p $tmp_path/ccs/$date_dt/json


[ ! -d $base_path/log/$date_dt/ccs/odps/ ] && mkdir -p $base_path/log/$date_dt/ccs/odps
[ ! -d $base_path/log/$date_dt/ccs/cdp/ ] && mkdir -p $base_path/log/$date_dt/ccs/cdp
	

cat $cur_path/list.ini |while read line
do	
	from=`echo $line |awk '{print $1}'`
    topre=`echo $line |awk '{print $2}'`
    toend=`echo $line |awk '{print $3}'`
	filename=`echo $line |awk '{print $4}'`
	
	if [ -e $base_path/$topre/$date_dt/$toend$filename  ]
		then
		rm $base_path/$topre/$date_dt/$toend$filename
	fi
	transSqlTemplate $base_path/$from  $base_path/$topre/$date_dt/$toend  $filename   
done
