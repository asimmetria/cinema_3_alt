FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn -f /app/pom.xml clean package -DskipTests


FROM openjdk:11.0.12-jdk
WORKDIR /app
COPY --from=build /app/target/cinema_3*.jar app.jar

#RUN apt-get update && \
#    apt-get install -y postgresql postgresql-contrib && \
#    service postgresql start && \
#    su postgres -c "psql -c 'CREATE DATABASE cinema_base;'" && \
#    su postgres -c "psql -c 'CREATE USER cinema_user WITH PASSWORD '\''root'\'';'" && \
#    su postgres -c "psql -c 'GRANT ALL PRIVILEGES ON DATABASE cinema_base TO cinema_user;'"

COPY src/main/java/com/kata/cinema/base/webapp/controller/admin/test/ /app


#ENV PSQL_SERVER=localhost
#ENV PSQL_PORT=5432
#ENV PSQL_DB=cinema_base
#ENV PSQL_USERNAME=cinema_user
#ENV PSQL_PASSWORD=root

CMD java -jar app.jar