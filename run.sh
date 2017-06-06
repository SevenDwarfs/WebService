export MAVEN_HOME=/opt/maven
export JAVA_HOME=/opt/java/jdk1.8.0_112
export JENKINS_HOME=/jenkins

mvn package
docker stop movie-server
docker rm movie-server
docker rmi kinpzz/movie-booking
docker build -t kinpzz/movie-booking .
docker run -d -p 127.0.0.1:8082:8082 --name movie-server kinpzz/movie-booking
