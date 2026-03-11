# Food Delivery Microservices System

## Project Overview

This project is a **Food Delivery Platform built using Spring Boot Microservices Architecture**.  
The system demonstrates how independent services communicate with each other using REST APIs and Spring Cloud components.

The application simulates a real-world online food ordering system where users can:

• Register and authenticate  
• Browse restaurants  
• Place food orders  
• Process payments  
• Receive order notifications  

The system is designed using **loosely coupled microservices**, making it scalable, maintainable, and independently deployable.

---

# Architecture

The project follows **Microservices Architecture Pattern** with Spring Cloud components.

Key architectural components:

• API Gateway Pattern  
• Service Discovery using Eureka  
• Centralized Configuration using Config Server  
• RESTful Inter-Service Communication  
• Layered Architecture (Controller → Service → Repository)

---

# Microservices in the Project

| Service | Port | Description |
|------|------|-------------|
| Config Server | 8888 | Centralized configuration management |
| Eureka Discovery Server | 8761 | Service registry for microservices |
| API Gateway | 8080 | Entry point for all client requests |
| Auth Service | 8081 | Handles authentication and JWT |
| User Service | 8082 | Manages user profiles |
| Restaurant Service | 8083 | Manages restaurants and menus |
| Order Service | 8084 | Handles order processing |
| Payment Service | 8085 | Processes payments |
| Notification Service | 8086 | Sends order notifications |

---
| Service        | Port | Description                                                                   |
| -------------- | ---- | ----------------------------------------------------------------------------- |
| Kafka          | 9092 | Event streaming platform used for asynchronous communication between services |
| Zookeeper      | 2181 | Manages Kafka brokers and cluster coordination                                |
| Zipkin         | 9411 | Distributed tracing system used to track requests across microservices        |
| Docker         | -    | Containerizes all services for consistent deployment                          |
| Docker Compose | -    | Orchestrates all containers and runs the system with a single command         |

# Technology Stack

### Backend
• Java  
• Spring Boot  
• Spring Cloud  

### Microservices Infrastructure
• Eureka Service Discovery  
• Config Server  
• API Gateway  

### Build Tool
• Maven

### Database
• MySQL / H2

### Version Control
• Git  
• GitHub

---

# Project Folder Structure
food-delivery-microservices
│
├── backend
│ ├── api-gateway
│ ├── auth-service
│ ├── config-server
│ ├── discovery-server
│ ├── user-service
│ ├── restaurant-service
│ ├── order-service
│ ├── payment-service
│ └── notification-service
│
├── architecture
│
├── docker
│
├── docs
│
├── README.md
└── .gitignore
---

# Order Processing Workflow

1. Client sends request to **API Gateway**
2. API Gateway routes request to **Order Service**
3. Order Service creates order
4. Order Service calls **Payment Service**
5. Payment Service processes payment
6. Notification Service sends confirmation

Example flow:

Client → API Gateway → Order Service → Payment Service → Notification Service

---

# How to Run the Project

Start services in the following order.

## 1 Start Config Server
cd backend/config-server
mvn spring-boot:run

Runs on

http://localhost:8888

---

## 2 Start Eureka Discovery Server
cd backend/discovery-server
mvn spring-boot:run
Dashboard
http://localhost:8761

---

## 3 Start API Gateway
cd backend/api-gateway
mvn spring-boot:run
Runs on
http://localhost:8080

---

## 4 Start Remaining Services

Start the following services:
auth-service
user-service
restaurant-service
order-service
payment-service
notification-service
Example:
cd backend/order-service
mvn spring-boot:run

---

# Methodologies Used

The project follows **Agile development practices**.

Design principles used:

• Microservices architecture  
• Layered architecture  
• RESTful API design  
• Independent service deployment  

Typical service layers:

Controller Layer  
Service Layer  
Repository Layer  
Database

---

# Advantages of This Architecture

• High scalability  
• Independent service deployment  
• Fault isolation  
• Easy maintenance  
• Faster development cycles  

---

# Future Enhancements

• Docker containerization  
• AWS deployment  
• Kafka event-driven communication  --- inprogress
• Distributed tracing using Zipkin  --- completed
• Circuit breaker using Resilience4j  --- inprogress 

---

# Author

Akhil Sai Alpuri  
Java Microservices Developer
