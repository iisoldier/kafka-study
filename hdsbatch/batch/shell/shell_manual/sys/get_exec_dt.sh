#!bin/bash

date_dt=$(mysql -N -e "select Min(exec_dt) from t_dtpara where status=0")

ret=$?
if [ $ret -ne 0 ];then
	echo "RC=128"
    echo "RDSִ�д�������"
    exit 128
fi

echo $date_dt

