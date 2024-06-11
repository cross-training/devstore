# Config Service

## Spring Initializer

- [start](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.6&packaging=jar&jvmVersion=17&groupId=cloud.crosstraining.devstore&artifactId=config&name=config&description=Demo%20project%20for%20Spring%20Boot&packageName=cloud.crosstraining.devstore.config&dependencies=cloud-config-server,security)

## Gradle

**Build:**

```shell
./gradlew clean build
```

**Run:**

```shell
./gradlew bootRun
```

**Test:**

```shell
curl -u devstore:secr3t  http://localhost:8888/catalog-service/docker
curl http://devstore:secr3t@localhost:8888/catalog-service/local
```

## Docker

**Build & Push:**

```shell
docker build -t flaviorita/devstore-config:0.0.10 .
docker push flaviorita/devstore-config:0.0.10
```

**Run:**

```shell
docker run -p 8888:8080 -e CONFIG_SERVICE_USERNAME=devstore -e CONFIG_SERVICE_PASSWORD=secr3t flaviorita/devstore-config:0.0.10
```

## References

- [tutorial](https://www.youtube.com/watch?v=ydtswONk9TE&list=PLxy6jHplP3Hi_W8iuYSbAeeMfaTZt49PW&index=12)
  - [github](https://github.com/digitallab-academy/ms-course-youtube)
