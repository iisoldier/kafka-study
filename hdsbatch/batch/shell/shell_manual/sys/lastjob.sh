#!bin/bash

cur_path="/ztapp/usr/hdsapp/deploy/batch/shell/sys"
last_dt=`sh $cur_path/get_exec_dt.sh`
echo "跑批日期 $last_dt 执行完毕"
mysql -N -e "update t_dtpara set status=1,end_time=sysdate() where exec_dt=$last_dt"
ret=$?
if [ $ret -ne 0 ];then
	echo "RC=128"
    echo "RDS执行错误，请检查"
    exit 128
fi
next_dt=`date -d ""$last_dt" +1 day" +"%Y%m%d"`

exist=$(mysql -N -e "update t_dtpara set status=1 where exec_dt=$last_dt")
ret=$?
if [ $ret -ne 0 ];then
	echo "RC=128"
    echo "RDS执行错误，请检查"
    exit 128
fi
mysql -N -e "insert into t_dtpara values($next_dt,'0',sysdate(),sysdate())"
ret=$?
if [ $ret -ne 0 ];then
	echo "RC=128"
    echo "RDS执行错误，请检查"
    exit 128
fi

echo "下一次跑批日期为 $next_dt"