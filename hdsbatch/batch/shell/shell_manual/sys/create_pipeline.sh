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

#生成缓存目录
mkdir -p  $temp_path
mkdir -p  $pipeline_path
#加载函数及默认参数
source $common_sh_path/getFunction.sh
source $common_sh_path/hds_env.sh

if [ ! -e $temp_path ]
then
        echo "RC=100"
        echo "message=${taskname}任务工作目录$temp_path不存在"
        exit 123
elif [ ! -d $temp_path ]
then
        echo "RC=101"
        echo "message=${taskname}路径$temp_path所指非目录"
        exit 124
elif [ ! -w $temp_path ]
then
        echo "RC=102"
        echo "message=${taskname}没有$temp_path的写权限"
        exit 126
fi
# 转换动态参数
if [ -e $pipeline_path/pipeline.json ]
then
rm $pipeline_path/pipeline.json
fi
transSqlTemplate $template_path/pipeline.json  $pipeline_path pipeline.json
#创建 pipeline
echo "----------------------------------------------"
echo "        验证 pipeline ${pipeline_name} !"
pp_list=`sh ${cdp_path}/cdp pipeline -list |grep -n name|awk '{print $3}'`
if [ `echo "$pp_list" |grep -w ${pipeline_name}` ]
then
echo "----------------------------------------------"
echo "                 已存在";
echo "----------------------------------------------"
else
echo "----------------------------------------------"
echo "          ${pipeline_name} 不存在，开始创建"
echo "----------------------------------------------"
sh ${cdp_path}/cdp pipeline -create ${pipeline_name} -f ${pipeline_path}/pipeline.json
echo "----------------------------------------------"
echo "创建成功"
echo "----------------------------------------------"
fi
echo "----------------------------------------------"
echo "            尝试关闭并重新开启";
echo "----------------------------------------------"
sh ${cdp_path}/cdp pipeline -close ${pipeline_name}
echo "----------------------------------------------"
echo "            ${pipeline_name} 已关闭"
echo "----------------------------------------------"
echo "            开启 pipeline ${pipeline_name}"
echo "----------------------------------------------"
sh ${cdp_path}/cdp pipeline -open ${pipeline_name}
echo "----------------------------------------------"
echo "            开启${pipeline_name}成功"
echo "----------------------------------------------"