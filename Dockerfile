FROM java:8
VOLUME /tmp
COPY target/movie-booking.war /tmp/
CMD ["java -jar movie-booking.war"]


