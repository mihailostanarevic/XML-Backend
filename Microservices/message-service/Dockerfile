FROM openjdk:8-jdk-alpine
EXPOSE 8085
COPY entrypoint.sh /entrypoint.sh
COPY target/message-service-0.0.1-SNAPSHOT.jar messageservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]