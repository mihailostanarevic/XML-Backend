FROM openjdk:8-jdk-alpine
EXPOSE 8083
COPY entrypoint.sh /entrypoint.sh
COPY target/Rent-Service-1.0.0.jar rentservice-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]