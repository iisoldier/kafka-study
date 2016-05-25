#!bin/bash

cur_path="/ztapp/usr/hdsapp/deploy/batch/shell/sys"
last_dt=`sh $cur_path/get_exec_dt.sh`
echo "跑批日期 $last_dt 执行完毕"
mysql -N -e "update t_dtpara set status=1,end_time=sysdate() where exec_dt=$last_dt"

next_dt=`date -d ""$last_dt" +1 day" +"%Y%m%d"`

exist=$(mysql -N -e "update t_dtpara set status=1 where exec_dt=$last_dt")
mysql -N -e "insert into t_dtpara values($next_dt,'0')"


echo "下一次跑批日期为 $next_dt"