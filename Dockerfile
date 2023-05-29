FROM adoptopenjdk/openjdk11:jre-11.0.13_8-alpine

EXPOSE 5500

COPY build/libs/MoneyTransferService-0.0.1-SNAPSHOT.jar mts.jar

CMD ["java", "-jar", "/mts.jar"]