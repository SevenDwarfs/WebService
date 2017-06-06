export MAVEN_HOME=/opt/maven
export JAVA_HOME=/opt/java/jdk1.8.0_112
export JENKINS_HOME=/jenkins

mvn package
docker stop RESTful-server
docker rm RESTful-server
docker rmi kinpzz/RESTful-server
docker build -t kinpzz/RESTful-server .
docker run -d -p 127.0.0.1:8082:8082 --name RESTful-server --link db:db kinpzz/RESTful-server
