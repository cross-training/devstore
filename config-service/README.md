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
curl -u 'devstore:pa$$w0rd'  http://localhost:9101/catalog-service/dev
```

## References

- [tutorial](https://www.youtube.com/watch?v=ydtswONk9TE&list=PLxy6jHplP3Hi_W8iuYSbAeeMfaTZt49PW&index=12)
