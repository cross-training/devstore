# Catalog Service

## Spring Initializer

- [start](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.6&packaging=jar&jvmVersion=17&groupId=cloud.crosstraining.devstore&artifactId=catalog&name=catalog&description=Demo%20project%20for%20Spring%20Boot&packageName=cloud.crosstraining.devstore.catalog&dependencies=data-jpa,actuator,lombok,data-rest,cloud-config-client,postgresql,prometheus,webflux,cloud-eureka,flyway,zipkin)

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

**Run with local profile:**

```shell
./gradlew bootRun 
```

**Run jar:**

```shell
export DB_URL=jdbc:postgresql://localhost:5432/devstore  &&
export DB_USER=devstore &&
export DB_PASSWORD=devstore &&
export DB_SCHEMA=catalog &&
export SPRING_PROFILES_ACTIVE=local &&
export CONFIG_SERVICE_URI=http://localhost:8888 &&
export CONFIG_SERVICE_USERNAME=devstore &&
export CONFIG_SERVICE_PASSWORD=secr3t &&
export DISCOVERY_SERVICE_URI=http://localhost:8761/eureka/ &&
export LOGGING_SERVICE_URI=http://localhost:9411 &&
java -jar app.jar
```

**Docker build & push:**

```shell
docker build -t flaviorita/devstore-catalog:0.0.3 .
docker push flaviorita/devstore-catalog:0.0.3

curl http://devstore:secr3t@0.0.0.0:8888/catalog-service/docker
```

**Docker run:**

```shell
docker run --network=devstore_backend -p 9001:8080 -e SPRING_PROFILES_ACTIVE=local -e CONFIG_SERVICE_URI=http://config:8888 -e CONFIG_SERVICE_USERNAME=devstore -e CONFIG_SERVICE_PASSWORD=secr3t -e DISCOVERY_SERVICE_URI=http://discovery:8761/eureka -e LOGGING_SERVICE_URI=http://zipkin:9411 -e DB_URL=jdbc:postgresql://postgres:5432/devstore -e DB_USER=catalog -e DB_PASSWORD=catalog -e DB_SCHEMA=catalog flaviorita/devstore-catalog:0.0.3
```

Observations:

- since postgres is running from docker compose and on the devstore_backend network, you must set --network=devstore_backend
- since the container is running on the devstore_backend network, in order to consume the service from the host, port -p 8080:8080 must be exposed

## Endpoints

- actuator:
  - [health](http://localhost:9001/health)
  - [info](http://localhost:9001/info)
  - [metrics](http://localhost:9001/metrics)
    - [example metrics](http://localhost:9001/actuator/metrics?names=http.server.requests,http.server.requests,jvm.memory.used,process.cpu.usage,system.cpu.usage)

## References

- [spring-boot-actuator](https://medium.com/@abhishekranjandev/spring-boot-actuator-in-depth-analysis-and-code-samples-710b3852dee9)
