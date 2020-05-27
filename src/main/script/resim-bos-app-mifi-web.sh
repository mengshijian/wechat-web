#!/bin/bash
### BEGIN INIT INFO
# Provides:          bos_app_mifi_web
# Required-Start:    $local_fs $remote_fs $network $syslog
# Required-Stop:     $local_fs $remote_fs $network $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: starts the BOS.APP.Web
# Description:       starts bos_app_mifi_web using start-stop-daemon
### END INIT INFO

########################################################################################
#bos_app_mifi_web:
#/opt/bos_app_mifi_web
#            |________app
#            |________log
########################################################################################

# 当前目录
Dir=$(cd "$(dirname "$0")"; pwd)
App_File_Name=resim-bos-app-mifi-web
App_Ext_Name=Unknown
#安装文件
JAR_File_Name=${App_File_Name}-${App_Ext_Name}.jar
#启动/停止脚本
JAR_Shell=${App_File_Name}-${App_Ext_Name}.sh
#应用主目录
App_Base_Home=/opt/resim-bos-app-mifi-web
#应用程序安装目录
App_Home=${App_Base_Home}/app
#应用程序日志目录
Log_Home=${App_Base_Home}/log
#应用程序备份目录
Backup_Home=${App_Base_Home}/backup
#系统服务名称
App_Service_Name=${App_File_Name}
#默认SpringBoot激活环境 默认prod
Profile=prod

#休眠等待次数
SleepTime=30

JvmArgs=

DUBBO_SERVER_PORT=20884
