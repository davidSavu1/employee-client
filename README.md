# employee-client
# Employee Client Service (Application 1)

This is a Spring Boot application built with Java 21 that acts as a client for the `employee-adapter` service.  
It sends REST calls to retrieve employee data by ID using a non-blocking, asynchronous WebClient.

---

## Features

- Asynchronous REST call using `WebClient`
- Calls Application 2 (`employee-adapter`) to fetch employee data
- Handles 404, 5xx errors and service unavailability gracefully
- Can be tested independently via main or controller/test class

---

## Technologies

- Java 21
- Spring Boot
- Spring WebFlux (WebClient)
- Maven
