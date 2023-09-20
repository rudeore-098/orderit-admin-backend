FROM amazoncorretto:17

WORKDIR /app

COPY * /app/
COPY src/ /app/src/
COPY gradle/ /app/gradle/

RUN chmod +x ./gradlew
RUN ./gradlew build

EXPOSE 80

ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul","build/libs/orderit-0.0.1-SNAPSHOT.jar","-Xmx3072m","-Xms3072m"]