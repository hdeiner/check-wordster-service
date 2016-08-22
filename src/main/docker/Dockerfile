# Pull base image.
FROM ubuntu:14.04
MAINTAINER Howard Deiner

# Install Java.
RUN apt-get update
RUN apt-get install -y wget
RUN wget --progress=bar:force --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u102-b14/server-jre-8u102-linux-x64.tar.gz
RUN mkdir /opt/jdk
RUN tar -zxf server-jre-8u102-linux-x64.tar.gz -C /opt/jdk
RUN rm server-jre-8u102-linux-x64.tar.gz
RUN update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.8.0_102/jre/bin/java 100
ENV JAVA_HOME /usr/bin/java

# Install application.
COPY check-wordster-service-0.0.1-server.jar check-wordster-service-0.0.1-server.jar
RUN chmod +x check-wordster-service-0.0.1-server.jar
RUN mkdir dependency-jars
COPY dependency-jars/ dependency-jars/

# Allow port 80 communication.  Still need -port 9000:9000 to operate checkwordster service.
EXPOSE 80

# Default service
CMD ["java", "-jar", "check-wordster-service-0.0.1-server.jar"]