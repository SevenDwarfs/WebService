export MAVEN_HOME=/opt/maven
export JAVA_HOME=/opt/java/jdk1.8.0_112
export JENKINS_HOME=/jenkins

mvn package && java -jar target/movie-booking.jar
docker build -t kinpzz/movie-booking
docker run -d -p 8082:8082 --name movie-server kinpzz/movie-booking
