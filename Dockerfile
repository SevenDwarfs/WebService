FROM java:8
VOLUME /tmp
ADD target/movie-booking.war server.war
RUN bash -c 'touch /server.war'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-war","/server.war"]
