# Rating Service

## Create

- [Spring Initializer](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.6&packaging=jar&jvmVersion=17&groupId=cloud.crosstraining.devstore&artifactId=rating&name=rating&description=Exercice%20devstore&packageName=cloud.crosstraining.devstore.rating&dependencies=data-jpa,actuator,lombok,data-rest,webflux,prometheus,postgresql,flyway,zipkin,oauth2-authorization-server)
- [Create Database](./src/main/resources/sql/README.md)

## Gradle

**initialize:**

```bash
gradle wrapper --gradle-version 7.5
```

**Refresh dependencies:**

```shell
./gradlew build --refresh-dependencies
```

**Build:**

```shell
./gradlew clean build
```

**Run:**

```shell
ENV_FILE=.dev.envc  ./gradlew bootRun 
```
