FROM openjdk:11
COPY ./app/app.jar .
ENTRYPOINT ["java","-jar","app.jar"]