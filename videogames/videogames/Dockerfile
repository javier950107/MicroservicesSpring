FROM openjdk:17
VOLUME /tmp
EXPOSE 8007
ADD ./target/videogames-0.0.1-SNAPSHOT.jar videogames.jar
ENTRYPOINT [ "java", "-jar", "videogames.jar" ]