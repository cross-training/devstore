# Catalog Service

## Spring Initializer

- [start](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.6&packaging=jar&jvmVersion=17&groupId=cloud.crosstraining.devstore&artifactId=catalog&name=catalog&description=Demo%20project%20for%20Spring%20Boot&packageName=cloud.crosstraining.devstore.catalog&dependencies=data-jpa,actuator,lombok,data-rest,cloud-config-client)

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

**Run with dev profile:**

```shell
ENV_FILE=.dev.envc  ./gradlew bootRun --args='--spring.profiles.active=dev' 
```

**Run with prod profile:**

```shell
ENV_FILE=.prod.env ./gradlew bootRun --args='--spring.profiles.active=prod' 
```

**Run jar:**

```shell
export DB_URL=jdbc:postgresql://localhost:5432/devstore  &&
export DB_USER=devstore &&
export DB_PASSWORD=devstore && 
java -jar app.jar --spring.profiles.active=dev 
```

**Run as docker:**

```shell
docker build -t flaviorita/devstore/catalog:0.0.4 .
docker run --network=devstore_backend -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev -e DB_URL=jdbc:postgresql://postgres:5432/devstore -e DB_USER=devstore -e DB_PASSWORD=devstore flaviorita/devstore/catalog:0.0.4
```

Observations:

- since postgres is running from docker compose and on the devstore_backend network, you must set --network=devstore_backend
- since the container is running on the devstore_backend network, in order to consume the service from the host, port -p 8080:8080 must be exposed

**Remove image:**

```shell
docker image rm  flaviorita/devstore/backend:0.0.3 
```

## Endpoints

- actuator:
  - [health](http://localhost:9001/health)
  - [info](http://localhost:9001/info)
  - [metrics](http://localhost:9001/metrics)
    - [example metrics](http://localhost:9001/actuator/metrics?names=http.server.requests,http.server.requests,jvm.memory.used,process.cpu.usage,system.cpu.usage)

## References

- [spring-boot-actuator](https://medium.com/@abhishekranjandev/spring-boot-actuator-in-depth-analysis-and-code-samples-710b3852dee9)
