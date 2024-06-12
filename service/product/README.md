# Product Service

## Create

- [Spring Initializer](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.6&packaging=jar&jvmVersion=17&groupId=cloud.crosstraining.devstore&artifactId=product&name=product&description=Exercice%20devstore&packageName=cloud.crosstraining.devstore.product&dependencies=data-jpa,actuator,lombok,data-rest,webflux,prometheus,postgresql,zipkin,oauth2-authorization-server,cloud-config-client,cloud-eureka,web)

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
./gradlew bootRun 
```

**Run jar:**

```shell
export DB_URL=jdbc:postgresql://localhost:5432/devstore  &&
export DB_USER=rating &&
export DB_PASSWORD=rating &&
export DB_SCHEMA=rating &&
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
docker build -t flaviorita/devstore-rating:0.0.1 .
docker push flaviorita/devstore-rating:0.0.1
```

**Docker run:**

```shell
docker run --network=devstore_backend -p 9002:8080 -e SPRING_PROFILES_ACTIVE=local -e CONFIG_SERVICE_URI=http://config:8888 -e CONFIG_SERVICE_USERNAME=devstore -e CONFIG_SERVICE_PASSWORD=secr3t -e DISCOVERY_SERVICE_URI=http://discovery:8761/eureka -e LOGGING_SERVICE_URI=http://zipkin:9411 -e DB_URL=jdbc:postgresql://postgres:5432/devstore -e DB_USER=rating -e DB_PASSWORD=rating -e DB_SCHEMA=rating flaviorita/devstore-rating:0.0.1
```