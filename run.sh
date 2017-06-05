export MAVEN_HOME=/opt/maven
export JAVA_HOME=/opt/java/jdk1.8.0_112
export JENKINS_HOME=/jenkins

mvn package
docker build -t kinpzz/movie-booking .
docker stop movie-server
docker rm movie-server
docker run -d -p 8082:8082 --name movie-server kinpzz/movie-booking
