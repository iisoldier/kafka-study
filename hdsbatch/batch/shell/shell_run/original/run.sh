#!bin/bash
table_name= $1
exec_date= $2
sh hds_odps.sh $table_name $exec_date
sh hds_cdp.sh $table_name $exec_date
