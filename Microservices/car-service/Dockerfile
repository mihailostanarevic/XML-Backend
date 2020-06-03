FROM openjdk:8-jdk-alpine
EXPOSE 8082
COPY entrypoint.sh /entrypoint.sh
COPY target/car-service-0.0.1-SNAPSHOT.jar carservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]