#FROM openjdk:17
#RUN mkdir /opt/app
#COPY target/locations-0.0.1-SNAPSHOT.jar /opt/app/locations.jar
#CMD ["java", "-jar", "/opt/app/locations.jar"]

FROM openjdk:17 as builder
WORKDIR application
COPY target/locations-0.0.1-SNAPSHOT.jar locations.jar
RUN java -Djarmode=layertools -jar locations.jar extract

FROM openjdk:17
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.launch.JarLauncher"]