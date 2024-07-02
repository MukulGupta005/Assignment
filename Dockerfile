FROM amazoncorretto:22-alpine-jdk
ADD target/advaitassignment-0.0.1-SNAPSHOT.jar advaitassignment.jar
ENTRYPOINT ["java", "-jar", "advaitassignment.jar"]
