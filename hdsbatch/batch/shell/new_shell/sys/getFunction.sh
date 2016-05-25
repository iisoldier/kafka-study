templateNotExist=120
templateNotNormal=121
templateCantRead=122
directoryNotExist=123
pathNotDirectory=124
fileIsExist=125
cantWrite=126

#Usage: transSqlTemplate $1 $2 $3
#$1: Sql template path
#$2: Sql directory
#$3: Sql file name

transSqlTemplate () {
  if [ ! -e $1 ]
  then
    echo "FileNotExist"
    echo "RC=$templateNotExist"
    echo "meaages=文件$1不存在，请检查！"
    exit $templateNotExist
  elif [ ! -s $1 ]
  then
    echo "FileNotNormal"
    echo "RC=$templateNotNormal"
    echo "meaages=文件$1异常，请检查！"
    exit $templateNotNormal
  elif [ ! -r $1 ]
  then
    echo "FileCannotRead"
    echo "RC=$templateCantRead"
    echo "meaages=文件$1不可读，请检查！"
    exit $templateCantRead
  elif [ ! -e $2 ]
  then
    echo "$2NotExist"
    echo "RC=$directoryNotExist"
    echo "meaages=目录$2不存在，请检查！"
    exit $directoryNotExist
  elif [ ! -d $2 ]
  then
    echo "$2NotDirectory"
    echo "RC=$pathNotDirectory"
    echo "meaages=路径$2所指非目录，请检查！"
    exit $pathNotDirectory
  elif [ -f $3 ]
  then
    echo "$2IsExist$3"
    echo "RC=$fileIsExist"
    echo "meaages=文件$2/$3已存在，请检查！"
    mv $3 $3.bak
    exit $fileIsExist
  elif [ ! -w $2 ]
  then   
    echo "$3CantWrite"
    echo "RC=$templateNotNormal"
    echo "meaages=文件$2/$3不可读，请检查！"
    exit $cantWrite
  else
    IFS_OLD=$IFS
    IFS="@@@"
    sqlTemplate=`cat $1`
    correctSqlTemplate=${sqlTemplate}
    if tempStr="echo \"$correctSqlTemplate\""
    then
      sql=`eval "$tempStr"`
      echo ${sql} > $2/$3
      echo "BuildSQL$2/$3"
    else
      echo "As$1Build$SQLFail"
    fi
    IFS=$IFS_OLD
  fi
}

getExitcode()
{
	ret=$1
	if [ $ret -ne 0 ]
		then 
		echo "$ret###"
		exit $ret
		
	fi
}