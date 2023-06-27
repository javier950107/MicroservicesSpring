FROM openjdk:17
VOLUME /tmp
EXPOSE 8002
ADD ./target/user-videogames-0.0.1-SNAPSHOT.jar user-videogames.jar
ENTRYPOINT [ "java", "-jar", "user-videogames.jar" ]