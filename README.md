# creditcard-processor

The CreditCard Processor is a micro-service that allows to add new or view existing credit card accounts.

# Technology Stack
Java 11
Spring Boot 2.4.5 Release
Gradle Wrapper and Dependencies
Lombok Plugin
H2 Database Runtime (Memory)
Spring BOOT JPA
SpringFox Swagger
SpringFox UI
Junit Mockito
Flyway

## Starting the application

#### Activate the profile
* Modify the file application.yml accordingly

```
 active: base | local | test | qa | prod

```

### Backend

In order to start the application, you just have to run the gradle task `bootRun`:

```bash
$ ./gradlew bootRun
```

## Useful URLs

| URL | Description |
|-----|-------------|
http://localhost:9090/swagger-ui.html | Swagger UI
http://localhost:9090/h2/   | H2 Database UI 

### Project Structure

Endpoints are defined in Controller classes and can be accessed by Swagger UI. 
(com.ps.credit.card.res*)