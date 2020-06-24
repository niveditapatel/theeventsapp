FROM openjdk:8
ADD target/user-mysql.jar user-mysql.jar
EXPOSE 8089
RUN useradd newuser
USER newuser
CMD ["newuser"]
ENTRYPOINT ["java", "-jar", "user-mysql.jar"]