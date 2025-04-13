# Receipt Processor Challenge (Java SpringBoot Maven Docker)

A simple receipt processing API that awards points based on a set of rules defined (https://github.com/fetch-rewards/receipt-processor-challenge#rules). Built with Java, Spring Boot, and Docker.

**Note:**
- The code related to awarding points based on a set of rules is in `ReceiptService.java`

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
- **Spring Boot** 👉 [Download Here](https://start.spring.io)  
- **Docker** 👉 [Download Docker Desktop](https://www.docker.com/products/docker-desktop/)
- **VSCode** 👉 [Download Here](https://code.visualstudio.com/download)

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
Verify that you see the Docker container running in the _Docker Desktop_ app

The service will be available at `http://localhost:8080`.


### Testing Sample Requests (use `sample-request.http` file provided)

You can use **VS Code REST Client** extension to test this.  
The `sample-request.http` file includes examples for the two endpoints:

- `POST /receipts/process` – To submit a receipt and get a unique ID.
- `GET /receipts/{id}/points` – To retrieve the points awarded for that receipt.

### Testing Sample Requests using Curl

**1. POST a Receipt:**
Use this curl command to submit a sample receipt:
```bash
curl -v -H "Content-Type: application/json" -d '{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    { "shortDescription": "Mountain Dew 12PK", "price": "6.49" },
    { "shortDescription": "Emils Cheese Pizza", "price": "12.25" },
    { "shortDescription": "Knorr Creamy Chicken", "price": "1.26" },
    { "shortDescription": "Doritos Nacho Cheese", "price": "3.35" },
    { "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ", "price": "12.00" }
  ],
  "total": "35.35"
}' -X POST http://localhost:8080/receipts/process
```
**Expected Response in json**
```{ "id": "1c23395b-7b6e-47bf-887c-f8e7608c809c" }```

**2. GET Points for a Receipt:**
Use the ID received in the POST response in the URL path below:
```bash
curl -v -X GET http://localhost:8080/receipts/1c23395b-7b6e-47bf-887c-f8e7608c809c/points
```
**Expected Response in json**
```{ "points": "28" }```
