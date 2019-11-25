FROM openjdk:8-jre-alpine
VOLUME /tmp
# 安装字体文件
RUN apk add --no-cache ttf-dejavu
COPY target/*.jar tools-server.jar
# 为了缩短 Tomcat 启动时间，添加一个系统属性指向 “/dev/urandom” 作为 Entropy Source
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar tools-server.jar
