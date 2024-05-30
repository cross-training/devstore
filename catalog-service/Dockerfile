
FROM khipu/openjdk17-alpine
WORKDIR /app
COPY app.jar app.jar
EXPOSE 9001
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["sh", "-c", "java -jar -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} app.jar"]