#!bin/bash
# jinmeng 20160423
# paraNo 		3
# para1  		tablename
# para2			begindate
# para3			enddate
#
#
hdstool_path=`cd ~/deploy/hds_tool`
xml_path=`cd ~/deploy/batch/shell/xml`

tablename=$1
begindate=$2
enddate=$3

java -jar $hdstool_path/hds_tool.jar -deleteTable ${tablename} "XXX"
java -jar $hdstool_path/hds_tool.jar -deleteTable ${tablename}_index "XXX"
java -jar $hdstool_path/hds_tool.jar -createTable $xml_path/${tablename}.xml
java -jar $hdstool_path/hds_tool.jar -createTable $xml_path/${tablename}_index.xml

mysql -e "update dtPara set ${tablename}_state = 0 where exec_dt >= begindate;" > log/rerun/mysql/${tablename}_${exec_dt}.log
	



[ ! -d log/rerun/mysql ] && mkdir -p log/rerun/mysql
[ ! -d log/rerun/mysql/ok ] && mkdir -p log/rerun/mysql/ok


exec_dt=`sh get_exec_dt.sh`
while ( [ exec_dt != enddate ] )
do
	sh run.sh ${tablename}
	mysql -e "update dtPara set ${tablename}_state = 1 where exec_dt = $exec_dt;" > log/rerun/mysql/${tablename}_${exec_dt}.log
done

mv log/rerun/mysql/*.log log/rerun/mysql/ok

