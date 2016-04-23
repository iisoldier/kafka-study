#!bin/bash


last_dt=`sh get_exec_dt.sh`

echo $last_dt
#获取跑批日期
mysql  <<EOF
	update para_dtt set state=1 where exec_dt=$last_dt;	
EOF


#获取下一天跑批日期
next_dt=`date -d ""$last_dt" +1 day" +"%Y%m%d"`

#判断下一天跑批日期是否存在
exist=$(mysql -N <<EOF
	select count(*) from para_dtt where exec_dt=$next_dt;	
EOF
)


if [ $exist -eq 0 ] ;then
	mysql  <<EOF
	insert into para_dtt (exec_dt, state) values ($next_dt,'0');	
EOF
fi
