#!bin/bash

exec_dt=$(mysql -N -e "select Min(exec_dt) from para_dtt where state=0")
echo $exec_dt

