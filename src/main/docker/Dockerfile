# A basic apache server. To use either add or bind mount content under /var/www
# FROM ubuntu:12.04

# Pull base image.
FROM ubuntu:14.04

MAINTAINER Howard Deiner

# Install Java.
RUN apt-get update
RUN apt-get install -y wget
RUN wget --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u5-b13/jdk-8u5-linux-x64.tar.gz
RUN mkdir /opt/jdk
RUN tar -zxf jdk-8u5-linux-x64.tar.gz -C /opt/jdk
RUN update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.8.0_05/bin/java 100
RUN update-alternatives --install /usr/bin/javac javac /opt/jdk/jdk1.8.0_05/bin/javac 100
RUN rm jdk-8u5-linux-x64.tar.gz
COPY check-wordster-service-0.0.1-server.jar check-wordster-service-0.0.1-server.jar
RUN chmod +x check-wordster-service-0.0.1-server.jar
RUN mkdir dependency-jars
COPY dependency-jars/ dependency-jars/

ENV JAVA_HOME /usr/bin/java

# Define default command.
# CMD ["bash"]

EXPOSE 80

CMD ["java", "-jar", "check-wordster-service-0.0.1-server.jar"]