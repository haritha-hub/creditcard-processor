## creditcard-processor

The CreditCard Processor is a spring boot micro-service that allows to add new or view existing credit card accounts.

# Technology Stack
•	Java 11
•	Spring Boot 2.4.5 Release
•	Gradle Wrapper and Dependencies
•	Lombok Plugin
•	H2 Database Runtime (Memory)
•	Spring BOOT JPA
•	SpringFox Swagger
•	SpringFox UI
•	Junit Mockito
•	Flyway

#### Activate the profile

Modify application.yml accordingly

```
 active: profile

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

<img width="891" alt="Screenshot 2021-05-16 at 16 32 19" src="https://user-images.githubusercontent.com/84222668/118401048-6dcbf500-b664-11eb-91e1-40905e27fab3.png">

## Test Results

<img width="1061" alt="Screenshot 2021-05-16 at 16 45 56" src="https://user-images.githubusercontent.com/84222668/118401514-48d88180-b666-11eb-8c67-15d6ad45ff39.png">

