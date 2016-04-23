#!bin/bash


last_dt=`sh get_exec_dt.sh`

echo $last_dt

mysql  <<EOF
	update dtPara set state=1,his_ccs_fee_real_union_state=1,his_ccs_fenrun_jour_state=1,his_ccs_fee_jour_state=1,his_ccs_fee_jour_union_state=1,his_ccs_fee_settle_order_state=1 where exec_dt=$last_dt;	
EOF


#获取下一天跑批日期
next_dt=`date -d ""$last_dt" +1 day" +"%Y%m%d"`

#判断下一天跑批日期是否存在
exist=$(mysql -N <<EOF
	select count(*) from dtPara where exec_dt=$next_dt;	
EOF
)


if [ $exist -eq 0 ] ;then
	mysql  <<EOF
	insert into dtPara (exec_dt, state,his_ccs_fee_real_union_state,his_ccs_fenrun_jour_state,his_ccs_fee_jour_state,his_ccs_fee_jour_union_state,his_ccs_fee_settle_order_state) values ($next_dt,'0','0','0','0','0','0');	
EOF
fi
