#!bin/bash
cur_path="/ztapp/usr/hdsapp/deploy/batch/shell/ccs"
base_path="/ztapp/usr/hdsapp/deploy/batch"
sys_path=$base_path/shell/sys

source $sys_path/getFunction.sh

	sh $sys_path/firstjob.sh
	getExitcode $?
	echo "RC=0"
	echo "message=JOB firstjob FINISEHD SUCCESSFULLY"
	sh $cur_path/ccs_load_odps.sh
	getExitcode $?
	echo "RC=0"
	echo "message=JOB ccs_load_odps FINISEHD SUCCESSFULLY"
	sh $cur_path/ccs_model_odps.sh
	getExitcode $?
	echo "RC=0"
	echo "message=JOB ccs_model_odps FINISEHD SUCCESSFULLY"
	sh $cur_path/ccs_hds_cdp.sh
	getExitcode $?
	echo "RC=0"
	echo "message=JOB ccs_hds_cdp FINISEHD SUCCESSFULLY"
	sh $cur_path/ccs_30before_clean.sh
	getExitcode $?
	echo "RC=0"
	echo "message=JOB ccs_30before_clean FINISEHD SUCCESSFULLY"
	sh $sys_path/lastjob.sh
	getExitcode $?
	echo "RC=0"
	echo "message=JOB lastjob FINISEHD SUCCESSFULLY"

	echo "RC=0"
	echo "message=JOB CCS FINISEHD SUCCESSFULLY"
