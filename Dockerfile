FROM openjdk:17
VOLUME /tmp
ADD build/libs/*.jar takehome.jar
EXPOSE 8080
RUN bash -c 'touch /takehome.jar'
ENTRYPOINT ["java", "-jar", "takehome.jar"]