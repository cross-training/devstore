# Java Exercises

## Programming Challenges

- [retos de programación](https://retosdeprogramacion.com/ejercicios/)
- [hacker rank](https://www.hackerrank.com/dashboard?h_r=hrw&h_l=confirmation_email&utm_source=hrw&utm_campaign=confirmation_domains&utm_medium=email)

## Spring

- [start](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.5&packaging=jar&jvmVersion=17&groupId=cloud.crosstraining&artifactId=devstore&name=devstore&description=Demo%20project%20for%20Spring%20Boot&packageName=cloud.crosstraining.devstore&dependencies=data-jpa,actuator,lombok,data-rest)

## Gradle

initialize:

```bash
gradle wrapper --gradle-version 7.5
```

Refresh dependencies:

```shell
./gradlew build --refresh-dependencies
```

Build:

```shell
./gradlew clean build
```

Run with dev profile:

```shell
ENV_FILE=.dev.env ./gradlew bootRun --args='--spring.profiles.active=dev'
```

Run with prod profile:

```shell
ENV_FILE=.prod.env ./gradlew bootRun --args='--spring.profiles.active=prod' 
```

Run jar

```shell
export DB_DRIVER=org.h2.Driver && 
export DB_URL=jdbc:h2:mem:testdb  &&
export DB_PLATFORM=org.hibernate.dialect.H2Dialect &&
export DB_USER=sa &&
export DB_PASSWORD=password && 
java -jar app.jar --spring.profiles.active=dev 
```

Run as docker

```shell
docker build -t flaviorita/devstore/backend:last .
docker run -e SPRING_PROFILES_ACTIVE=dev -e DB_URL=jdbch2:mem:testdb -e DB_DRIVER=org.h2.Driver -e DB_PLATFORM=org.hibernate.dialect.H2Dialect -e DB_USER=sa -e DB_PASSWORD=password flaviorita/devstore/backend:last
```

## Endpoints

- actuator:
  - [health](http://localhost:9001/actuator/health)
  - [info](http://localhost:9001/actuator/info)
  - [prometheus](http://localhost:9001/actuator/prometheus)
  - [metrics](http://localhost:9001/actuator/metrics)
    - [example metrics](http://localhost:9001/actuator/metrics?names=http.server.requests,http.server.requests,jvm.memory.used,process.cpu.usage,system.cpu.usage)


## References

- [spring-boot-actuator](https://medium.com/@abhishekranjandev/spring-boot-actuator-in-depth-analysis-and-code-samples-710b3852dee9)
