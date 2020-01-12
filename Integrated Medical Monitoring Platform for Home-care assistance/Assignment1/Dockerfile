#FROM openjdk:10-jre-slim
#VOLUME /tmp
#WORKDIR /src
#COPY /target/Assignment1-0.0.1-SNAPSHOT.jar /src/
#EXPOSE 8888
#ENTRYPOINT ["java","-jar","Assignment1-0.0.1-SNAPSHOT.jar"]

#FROM java:8
#VOLUME /tmp
#EXPOSE 8888
#ADD /target/Assignment1-0.0.1-SNAPSHOT.jar Assignment1-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","Assignment1-0.0.1-SNAPSHOT.jar"]


FROM openjdk:8
VOLUME /tmp
ADD /target/Assignment1-0.0.1-SNAPSHOT.jar Assignment1-0.0.1-SNAPSHOT.jar
EXPOSE 8888
EXPOSE 8889
EXPOSE 9999
EXPOSE 62109
ENTRYPOINT ["java","-jar","Assignment1-0.0.1-SNAPSHOT.jar"]