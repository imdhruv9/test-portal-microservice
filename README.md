# 🧪 Online Test Portal – Microservice Project (Spring Boot)

A fully functional backend for an Online Test Portal with randomized MCQs, user and admin modules, JWT-based authentication, and microservice architecture using Spring Boot.

This project was developed as part of a backend internship assignment to demonstrate the implementation of RESTful APIs,  file upload, exam generation, and secure data handling.

---

## 🗂️ Project Structure (Microservices)

### 🧩 Microservice Architecture
- **Gateway Service** – Routes and secures requests
- **Auth Service** – Manages login, register, JWT issue/refresh
- **User Service** – Manages user profile and password
- **Exam Service** – Manages topics, questions, exams, results
- > Note: All requests go through **Spring Cloud Gateway**, enforcing centralized JWT validation.
  

## ✅ Features Implemented

## 🔐 Authentication (JWT + Spring Security)
- **Register (User/Admin)**
- **Login** – Returns JWT token (Access & Refresh)
- **Protected Routes** – Secured using Bearer token
- **Password Security** – Encrypted using BCrypt


## 🧑‍💼 Admin Features

- Upload MCQ questions via:
  - CSV file
  - Excel file
  - JSON  API
- Manage Topics
- Create Exams by topic (with randomization)
- > Note: Questions are randomized and options are shuffled each time user start the exam

## 👨‍🎓 User Features

- Register/Login
- Update profile or change password
- View available exams
- Start exams (randomized questions & shuffled options)
- Review and update answers before submission
- Submit and view result with score and correct answers
- View Exam Results

## 🧪 API Testing (Postman)

- Import the provided Postman Collection: `test-portal-microservice.postman_collection.json`
- Set environment variable: `{{base_url}} = http://localhost:8080`
- For authenticated routes, include JWT as token

> 🔒 Use the `/auth/login` endpoint to generate tokens.

## ⚙️ Setup Instructions

### Prerequisites
- Java 21
- Maven
- MySQL
- Postman

### Steps
1. Clone the repository
2. Configure each service’s `application.properties`:
  - DB connection
  - Port
  - JWT secret (in Auth Service)
3. Run all services in this order:
  - Auth Service
  - User Service
  - Exam Service
  - Gateway Service
4. Access Gateway at: `http://localhost:8080`

## 🛠 Technologies Used

- **Java 21**, **Spring Boot**
- **Spring Cloud Gateway**, **Spring Security**
- **Spring Data JPA**, **MySQL**
- **JWT** for secure stateless authentication
- **Postman** for testing
- **Lombok**

---


## 🙋 Author

**Dhruv Kumar**  
Backend Developer Intern  
📧 imdhruv2209@gmail.com







