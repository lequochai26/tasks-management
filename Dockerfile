FROM tomcat:9.0-jdk11-openjdk

ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"

ADD target/tasks-management-1.0.0.war /usr/local/tomcat/webapps/tasks-management.war

EXPOSE 8080

CMD ["catalina.sh", "run"]