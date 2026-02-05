🛒 E-Commerce Backend

A Java Spring Boot backend application for an e-commerce platform.
This project provides RESTful APIs for managing users, products, categories, carts, and orders, following clean architecture and best practices.

📌 Project Overview

This backend serves as the server-side foundation of an online shopping system.
It handles business logic, data persistence, and secure access for frontend or mobile applications.

Built mainly for learning, practice, and demonstration of backend development using Spring Boot.

🚀 Features

User registration and authentication

Role-based authorization (USER / ADMIN)

Product management (CRUD)

Category management

Shopping cart functionality

Order creation and tracking

Secure APIs using JWT

RESTful architecture

Clean and modular code structure

🛠 Tech Stack
Layer	Technology
Language	Java
Framework	Spring Boot
Build Tool	Maven
Security	Spring Security, JWT
Persistence	Spring Data JPA
Database	Configurable (MySQL / PostgreSQL / H2)
API Style	REST

⚙️ Installation & Setup
Prerequisites

Java 17+

Maven

Database (MySQL, PostgreSQL, or H2)

Steps

Clone the repository:

git clone https://github.com/ezedin130/e-commerce_backend.git
cd e-commerce_backend


Configure application properties:

spring.datasource.url=jdbc:your_database_url
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret
server.port=8080


Build and run:

mvn clean install
mvn spring-boot:run


Server runs at:

http://localhost:8080

🔒 Authorization

Protected endpoints require JWT token

Include token in request headers:

Authorization: Bearer <your_token>


Admin-only endpoints require ADMIN role

📘 API Response Format
{
"success": true,
"message": "Operation completed successfully",
"data": {}
}

🧪 Testing

Test the API using:

Postman

Insomnia

Hoppscotch

Use JWT for protected routes.

🌱 Future Improvements

Swagger / OpenAPI documentation

Payment gateway integration

Email notifications

Admin dashboard

Unit & integration tests

👤 Author

Ezedin
GitHub: https://github.com/ezedin130