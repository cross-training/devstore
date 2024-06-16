
FROM khipu/openjdk17-alpine
LABEL maintainer="Flavio Lionel Rita <flaviolrita@proton.me>"
LABEL description="Rating service for devstore"

WORKDIR /app
COPY app.jar app.jar
CMD ["sh", "-c", "java -jar app.jar"]