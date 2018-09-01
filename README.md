# microservices
A mini project to understand micro-service architecture. This is a simple user registration system which sends a 
registration confirmation email to the registered user.
Underlyingly uses two different micro-services built on Spring Boot and Spring Cloud one to register a new user and push a
message to kafka acting as a kafka producer and other acting as a kafka consumer to consume the meassage and send an email.

#TODO
Authentication and Authorization

#### Prerequisite
- Java 1.8
- Kafka
- Zookeeper
#### Service registry (Eureka)
- Build/Run
  - mvn clean install
  - java -jar target/demo-0.0.1-SNAPSHOT.jar
- Check
  - http://localhost:8761/
#### Config server (Spring Cloud Config)
- native profile is active reading propertes from local folder
- Update properties 
  - SET **PATH_TO_MICROSERVICES_PROJECT** in /microservices/config-server/src/main/resources/application.yml
  - SET **YOUR_GMAIL_USERNAME** in /microservices/config-properties/mail/dev/mail.yml
  - SET **YOUR_GMAIL_PASSWORD** in /microservices/config-properties/mail/dev/mail.yml
- Run
  - mvn clean install
  - java -jar target/demo-0.0.1-SNAPSHOT.jar
- Check
  - http://localhost:8888/user/dev -- should return the properties
#### User service
- Run
  - mvn clean install
  - java -jar target/demo-0.0.1-SNAPSHOT.jar
- Check
  - localhost:8081/api/user/members -- expect 0 records returned
#### Email service
- Run
  - mvn clean install
  - java -jar target/demo-0.0.1-SNAPSHOT.jar
- Check
#### Gateway (Zuul)
- Run
  - mvn clean install
  - java -jar target/demo-0.0.1-SNAPSHOT.jar
- Check
  - POST: localhost:8765/api/user/register {
	"username": "your_email@example.com",
	"password": "pass"
}
  - GET: localhost:8765/api/user/members -- expect 1 record returned
  - Check email receive at your_email@example.com

This is a combined repo for all the different projects, refer individual repo for complete stuff 
