FROM  maven:3-jdk-11-openj9 AS mvn-build
WORKDIR /build
COPY ./api /build
RUN mvn clean install -q

FROM openjdk:11.0.5-jre-slim
COPY --from=mvn-build /build/target/QualityCompendium-*.jar /app/QualityCompendium.jar
ENTRYPOINT ["java", "-jar", "/app/QualityCompendium.jar"]

