FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/search-service-0.0.1-SNAPSHOT.jar searchservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]