# Receipt Processor Challenge (Java SpringBoot Maven Docker)

A simple receipt processing API that awards points based on a set of rules defined (https://github.com/fetch-rewards/receipt-processor-challenge#rules). Built with Java, Spring Boot, and Docker.

---

## Features

- Submit receipts and get an `id` in response.
- Fetch points awarded for a given receipt using its `id`.
- Containerized using Docker for easy deployment.
- `sample-request.http` provided for quick testing.

## Prerequisites

Make sure the following are installed on your machine:

- **Java 17** 👉 [Download Here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- **Maven** 👉 [Download Here](https://maven.apache.org/download.cgi)  
- **Spring Boot** 👉 Generate project via [Spring Initializr](https://start.spring.io)  
- **Docker** 👉 [Download Docker Desktop](https://www.docker.com/products/docker-desktop/)

## How to Build & Run using Docker

### 1. Clone the repository

```bash
git clone https://github.com/vineethreddybh/receipt-processor-java-springboot.git
cd receipt-processor-java-springboot
```

### 2. Build the Docker image

```bash
docker build -t receipt-processor .
```

### 3. Run the application

```bash
docker run -p 8080:8080 receipt-processor
```

The service will be available at `http://localhost:8080`.


### Testing Sample Requests (use `sample-request.http` file provided)

You can use **IntelliJ HTTP Client** or **VS Code REST Client** extension to test this.  
The `sample-request.http` file includes examples for the two endpoints:

- `POST /receipts/process` – To submit a receipt and get a unique ID.
- `GET /receipts/{id}/points` – To retrieve the points awarded for that receipt.

