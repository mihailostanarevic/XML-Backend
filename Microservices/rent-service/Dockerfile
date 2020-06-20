FROM openjdk:8-jdk-alpine
EXPOSE 8083
COPY entrypoint.sh /entrypoint.sh
COPY target/rent-service-0.0.1-SNAPSHOT.jar rentservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]