FROM tomcat:jre8

COPY cookbook.war /usr/local/tomcat/webapps/cookbook.war

CMD ["catalina.sh", "run"]

EXPOSE 8080