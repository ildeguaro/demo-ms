FROM java:8
MAINTAINER Ildemaro Medina <imedina@gsgtech.com>
VOLUME /tmp
ADD target/demo-ms-api-1.0.0-RELASE.jar app.jar 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]