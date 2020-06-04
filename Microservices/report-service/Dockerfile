FROM openjdk:8-jdk-alpine
EXPOSE 8087
COPY entrypoint.sh /entrypoint.sh
COPY target/report-service-0.0.1-SNAPSHOT.jar reportservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]