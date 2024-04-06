FROM openjdk:11.0.15
MAINTAINER gjangid gjangid.lnmiit@gmail.com
EXPOSE 8098
WORKDIR /app
ADD target/offer-app-service-docker.jar offer-app-service-docker.jar
ENTRYPOINT ["java", "-jar", "offer-app-service-docker.jar"]
