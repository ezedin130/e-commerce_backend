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
📁 Project Structure
src/
├── main/
│   ├── java/com/ezedin/ecommerce/
│   │   ├── controller/     # REST controllers
│   │   ├── model/          # Entity classes
│   │   ├── repository/     # JPA repositories
│   │   ├── service/        # Business logic
│   │   └── config/         # Security & app configuration
│   └── resources/
│       ├── application.properties
│       └── application.yml
├── test/
├── pom.xml
└── README.md

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

📡 API Endpoints
🔐 Authentication
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Login and receive JWT
GET	/api/auth/me	Get current authenticated user
👤 Users
Method	Endpoint	Description
GET	/api/users	Get all users (Admin)
GET	/api/users/{id}	Get user by ID
PUT	/api/users/{id}	Update user
DELETE	/api/users/{id}	Delete user (Admin)
📦 Products
Method	Endpoint	Description
GET	/api/products	Get all products
GET	/api/products/{id}	Get product by ID
POST	/api/products	Create product (Admin)
PUT	/api/products/{id}	Update product (Admin)
DELETE	/api/products/{id}	Delete product (Admin)
🗂 Categories
Method	Endpoint	Description
GET	/api/categories	Get all categories
POST	/api/categories	Create category (Admin)
PUT	/api/categories/{id}	Update category
DELETE	/api/categories/{id}	Delete category
🛒 Cart
Method	Endpoint	Description
POST	/api/cart/add	Add product to cart
GET	/api/cart	Get current user cart
PUT	/api/cart/update	Update cart item quantity
DELETE	/api/cart/remove/{id}	Remove item from cart
DELETE	/api/cart/clear	Clear cart
📑 Orders
Method	Endpoint	Description
POST	/api/orders	Place a new order
GET	/api/orders/{id}	Get order by ID
GET	/api/orders/user/{userId}	Get user orders
GET	/api/orders	Get all orders (Admin only)
PUT	/api/orders/{id}/status	Update order status (Admin)
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