# 🏦 KuberX Financial Banking System

A modern **Full Stack Financial Banking System** built using **Spring Boot, Spring Security, JWT Authentication, MySQL, HTML, CSS, Bootstrap, and JavaScript**.

KuberX allows users to securely manage their bank accounts while providing an administrator dashboard to monitor users and banking operations.

---

# 📌 Project Overview

KuberX is a secure banking application that provides authentication, account management, transactions, and an admin panel.

The project follows a clean layered architecture using:

- Controller
- Service
- Repository
- Entity
- DTO
- Security

---

# 🚀 Features

## 👤 User Module

- User Registration
- User Login
- JWT Authentication
- Secure Password Encryption (BCrypt)
- View Profile
- Deposit Money
- Withdraw Money
- Transfer Money
- Check Balance
- Transaction History
- Change Password
- Close Account
- Logout

---

## 🛡️ Admin Module

- Admin Login
- JWT Authentication
- View All Users
- Search Users
- User Management Dashboard
- Total Users
- Total Accounts
- Total Balance

---

# 🔐 Security Features

- Spring Security
- JWT Token Authentication
- BCrypt Password Encryption
- Role Based Authentication
- Stateless Authentication
- Secure REST APIs
- CORS Configuration

---

# 🛠 Tech Stack

## Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- Maven

## Database

- MySQL

## Frontend

- HTML5
- CSS3
- Bootstrap 5
- JavaScript (ES6)

## Tools

- VS Code
- Git
- GitHub
- Postman
- MySQL Workbench

---

# 📂 Project Structure

```
financial-banking-system
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── config
│   │   │   ├── controller
│   │   │   ├── dto
│   │   │   ├── entity
│   │   │   ├── repository
│   │   │   ├── security
│   │   │   └── service
│   │   │
│   │   └── resources
│   │
│   └── frontend
│       ├── css
│       ├── js
│       ├── images
│       ├── pages
│       ├── dashboard.html
│       ├── users.html
│       └── index.html
│
├── pom.xml
└── README.md
```

---

# ⚙ Installation

## Clone Repository

```bash
git clone https://github.com/manojpap8-del/financial-banking-system.git
```

---

## Open Project

```bash
cd financial-banking-system
```

---

## Configure MySQL

Create Database

```sql
CREATE DATABASE financial_banking_system;
```

Update

```
application.properties
```

with your database credentials.

---

## Run Spring Boot

```bash
mvn spring-boot:run
```

Backend

```
http://localhost:8081
```

---

## Run Frontend

Open

```
src/frontend/index.html
```

using **Live Server**.

Frontend

```
http://127.0.0.1:5500
```

---

# 🔑 Authentication

This project uses **JWT Authentication**.

After successful login:

- JWT Token is generated.
- Token is stored in Local Storage.
- Every protected API includes

```
Authorization: Bearer <JWT_TOKEN>
```

---

# 📡 REST APIs

## User

| Method | Endpoint |
|---------|----------|
| POST | /api/users/register |
| POST | /api/users/login |
| GET | /api/users/profile |
| PUT | /api/users/change-password |

---

## Account

| Method | Endpoint |
|---------|----------|
| POST | /api/accounts/deposit |
| POST | /api/accounts/withdraw |
| POST | /api/accounts/transfer |
| GET | /api/accounts/balance |
| GET | /api/accounts/statement |
| PUT | /api/accounts/close |

---

## Admin

| Method | Endpoint |
|---------|----------|
| POST | /api/admin/register |
| POST | /api/admin/login |
| GET | /api/admin/users |
| GET | /api/admin/total-users |
| GET | /api/admin/total-accounts |
| GET | /api/admin/total-balance |

---

# 📸 Screenshots

You can add screenshots here.

```
Landing Page

Login Page

Register Page

Dashboard

Admin Dashboard

Users Management

Deposit

Withdraw

Transfer

Transaction History
```

---

# 🎯 Future Enhancements

- Loan Management
- Insurance Services
- Mobile Recharge
- Travel Ticket Booking
- ATM Card Management
- Email Notifications
- PDF Statements
- SMS Alerts
- OTP Verification
- Two Factor Authentication

---

# 💡 Learning Outcomes

This project helped me understand:

- Spring Boot Architecture
- REST API Development
- JWT Authentication
- Spring Security
- Layered Architecture
- DTO Pattern
- Repository Pattern
- Exception Handling
- CRUD Operations
- MySQL Integration
- Frontend Integration
- Git & GitHub

---

# 👨‍💻 Author

**Manoj Pal**

Java Full Stack Developer

GitHub:
https://github.com/manojpap8-del

LinkedIn:
(www.linkedin.com/in/manoj-pal-7447863)

---

# ⭐ Support

If you found this project useful, please consider giving it a ⭐ on GitHub.

It motivates me to build more projects.

---

# 📄 License

This project is developed for learning, educational, and portfolio purposes.