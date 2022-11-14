# Developer Service



### Run application via Maven
 
- mvn spring-boot:run

Data is loaded from GitHub on start up and will be saved in an in-memory H2 database.

### Task 1

- GET http://localhost:8080/api/v1/developers?language=java

### Task 2

- GET http://localhost:8080/api/v1/developers/languages/overview
