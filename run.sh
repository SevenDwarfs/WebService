export MAVEN_HOME=/opt/maven
export JAVA_HOME=/opt/java/jdk1.8.0_112
export JENKINS_HOME=/jenkins

mvn package
docker stop restful-server
docker rm restful-server
docker rmi kinpzz/restful-server
docker build -t kinpzz/restful-server .
docker run -d -p 127.0.0.1:8082:8082 --name restful-server --link db:db-server kinpzz/restful-server
