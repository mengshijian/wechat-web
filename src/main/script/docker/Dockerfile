#继承本地镜像
FROM docker_ubuntu16.04lts_jre8:base
#创建者信息
MAINTAINER wangyaohui "wangyaohui@cootf.com"

ADD resim-bos-app-mifi-web.jar /opt/resim-bos-app-mifi-web/app/resim-bos-app-mifi-web.jar
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone

#运行获取环境变量中PROFILE以及DUBBO_SERVER_PORT值，设定Dubbo服务暴露端口
# /opt/jre1.8.0_121 已在基础镜像内集成了
ENTRYPOINT /opt/jre1.8.0_121/bin/java -jar -Dspring.profiles.active=$PROFILE -Dspring.dubbo.port=$DUBBO_SERVER_PORT -Dapp.log.home=$Log_Home  /opt/resim-bos-app-mifi-web/app/resim-bos-app-mifi-web.jar

