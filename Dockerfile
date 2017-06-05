FROM java:8
VOLUME /tmp
COPY target/movie-booking.war .
CMD ["java", "-jar", "movie-booking.war"]


