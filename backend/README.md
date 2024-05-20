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

Build and Run:

```shell
./gradlew clean build
./gradlew bootRun
```

## Endpoints

- [health](http://localhost:9001/api/health)
