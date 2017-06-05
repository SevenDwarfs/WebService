FROM java:8
VOLUME /tmp
COPY target/movie-booking.war .
CMD ["java -jar ./target/movie-booking.war"]


