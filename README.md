# Spring Boot REST API with JWT Authentication & Role-based Access

A secure REST API built with **Spring Boot, Spring Security, and JWT** implementing **Role-based and Permission-based access control (RBAC + PBAC)**.  
Supports **access & refresh tokens**, **global exception handling**, and **MySQL integration**.

---

## Features
- User Authentication with **JWT (Access & Refresh Tokens)**
- Role-based access (`USER`, `ADMIN`)
- Fine-grained **permissions** (`POST_CREATE`, `POST_DELETE`, `USER_VIEW`, etc.)
- CRUD APIs for Posts (with pagination, sorting, and search)
- User management APIs
- **Global Exception Handling**
- API Documentation with **Swagger UI**

---

## 🛠️ Tech Stack
- Java 21
- Spring Boot 3.x
- Spring Security 6
- JWT
- MySQL + Hibernate/JPA
- Swagger / OpenAPI 3.1

---

## API Endpoints

### Authentication
- `POST /auth/signup` – Register new user
- `POST /auth/login` – Login & get access token
- `POST /auth/refresh` – Refresh access token
- `DELETE /auth/logout` – Logout user

### Posts
- `GET /posts` – Get all posts (with pagination & sorting)
- `GET /posts/{id}` – Get post by ID
- `POST /posts` – Create post
- `PUT /posts/{id}` – Update post
- `DELETE /posts/{id}` – Delete post
- `GET /posts/search` – Search posts

### Users
- `GET /api/users` – Get all users (with pagination & sorting)
- `PUT /api/users/{id}` – Update user profile

---

## Setup & Run
Open Swagger UI:
👉 http://localhost:8080/swagger-ui.html

```bash
# Clone repo
git clone https://github.com/your-username/springboot-security-jwt.git
cd springboot-security-jwt

# Configure DB in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=yourpassword

#jwt.secretKey=yoursecreatkey

# Run
mvn spring-boot:run
```
---

### 📸 Screenshots
<img width="915" height="748" alt="image" src="https://github.com/user-attachments/assets/e769d609-db36-48dc-82f8-0c462b63bc50" />
<img width="915" height="478" alt="image" src="https://github.com/user-attachments/assets/7b950cd9-ae3a-4104-a3ec-cc58b850c7e1" />

