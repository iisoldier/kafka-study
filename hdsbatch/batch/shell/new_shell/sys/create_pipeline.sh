#!/bin/bash
export now_date=`date +%Y%m%d`
export taskname="pipeline"
export jobname="pipeline"
export curr_path="/ztapp/usr/hdsapp/deploy/cdp-client/bin"
export cdp_path="/ztapp/usr/hdsapp/deploy/cdp-client/bin"
export template_path="/ztapp/usr/hdsapp/deploy/cdp-client/template"
export temp_path="/ztapp/usr/hdsapp/deploy/batch/tmp"
export pipeline_path="$temp_path/cdp_pipeline"
export common_sh_path="/ztapp/usr/hdsapp/deploy/batch/shell/sys"
export pipeline_name="hds_test_pipeline"

#���ɻ���Ŀ¼
mkdir -p  $temp_path
mkdir -p  $pipeline_path
#���غ�����Ĭ�ϲ���
source $common_sh_path/getFunction.sh
source $common_sh_path/hds_env.sh

if [ ! -e $temp_path ]
then
        echo "RC=100"
        echo "message=${taskname}������Ŀ¼$temp_path������"
        exit 123
elif [ ! -d $temp_path ]
then
        echo "RC=101"
        echo "message=${taskname}·��$temp_path��ָ��Ŀ¼"
        exit 124
elif [ ! -w $temp_path ]
then
        echo "RC=102"
        echo "message=${taskname}û��$temp_path��дȨ��"
        exit 126
fi
# ת����̬����
if [ -e $pipeline_path/pipeline.json ]
then
rm $pipeline_path/pipeline.json
fi
transSqlTemplate $template_path/pipeline.json  $pipeline_path pipeline.json
#���� pipeline
echo "----------------------------------------------"
echo "        ��֤ pipeline ${pipeline_name} !"
pp_list=`sh ${cdp_path}/cdp pipeline -list |grep -n name|awk '{print $3}'`
if [ `echo "$pp_list" |grep -w ${pipeline_name}` ]
then
echo "----------------------------------------------"
echo "                 �Ѵ���";
echo "----------------------------------------------"
else
echo "----------------------------------------------"
echo "          ${pipeline_name} �����ڣ���ʼ����"
echo "----------------------------------------------"
sh ${cdp_path}/cdp pipeline -create ${pipeline_name} -f ${pipeline_path}/pipeline.json
echo "----------------------------------------------"
echo "�����ɹ�"
echo "----------------------------------------------"
fi
echo "----------------------------------------------"
echo "            ���Թرղ����¿���";
echo "----------------------------------------------"
sh ${cdp_path}/cdp pipeline -close ${pipeline_name}
echo "----------------------------------------------"
echo "            ${pipeline_name} �ѹر�"
echo "----------------------------------------------"
echo "            ���� pipeline ${pipeline_name}"
echo "----------------------------------------------"
sh ${cdp_path}/cdp pipeline -open ${pipeline_name}
echo "----------------------------------------------"
echo "            ����${pipeline_name}�ɹ�"
echo "----------------------------------------------"