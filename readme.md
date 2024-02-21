# Vidathya Higher Education Center Exam Web Application

Welcome to the Vidathya Higher Education Center Exam Web Application backend project! This project serves as the backend for managing exams and users for the online exam platform. Students can register, attempt exams online, and view their marks. Additionally, users can update their details, view teachers of the institute, and access various statistics via the dashboard.

## Features

- **User Management**: CRUD operations for managing users. Users are stored in a MySQL database.
- **Exam Management**: CRUD operations for managing exams. Exam details are stored in a MongoDB database.
- **Online Exam System**: Students can attempt exams online, and the system calculates the time taken. Once the time is up, the exam is automatically closed.
- **Dashboard Statistics**: The dashboard displays statistics such as registered student count, teacher count, exam count, and done exams count by relevant users.
- **JWT Authentication**: Secure endpoints using JSON Web Token (JWT) authentication.

## API Endpoints

### Users

- **GET /api/v1/user/all**: Get all users.
- **GET /api/v1/user/get**: Get a specific user by ID.
- **POST /api/v1/user/add**: Create a new user.
- **PUT /api/v1/user/update**: Update an existing user.
- **DELETE /api/v1/user/delete**: Delete a user by ID.

### Exams

- **GET exam/api/v1/paper/getAll**: Get all papers.
- **GET exam/api/v1/paper/get/{id}**: Get a specific paper by ID.
- **POST exam/api/paper/add**: Create a new paper.
- **PUT exam/api/paper/update**: Update an existing paper.
- **DELETE exam/api/paper/delete/{id}**: Delete a paper by ID.
- **GET exam/api/paper/count**: Get Paper count.

### My Exams

- **GET exam/api/v1/myexam/getAll/{nic}**: Get all my exams.
- **GET exam/api/v1/myexam/get/{paperId}/{nic}**: Get a specific my exam by ID.
- **POST exam/api/myexam/add**: Create a new my exam.
- **GET exam/api/myexam/count/{nic}**: Get all my paper count.


### Authentication

- **POST /api/v1/user/auth**: Authenticate user and generate JWT token.

## Database

- **MySQL**: Stores user data.
- **MongoDB**: Stores exam data.

## Tech Stack 

- **Spring Boot**: Backend framework.
- **Node.js**: JavaScript runtime.
- **Express.js**: Web application framework.
- **MySQL**: Relational database.
- **MongoDB**: NoSQL database.
- **JWT**: Authentication.
- **Maven**: Dependency management.
- **Git**: Version control.
- **GitHub**: Repository hosting.
- **Postman**: API testing.
- **IntelliJ IDEA**: IDE.
    - 

## Usage

1. Clone the backend repository:

```bash
git clone https://github.com/Ruvini-Rangathara/Vidathya-Web-Exam-Backend.git
```

1. Clone the frontend repository:

```bash
git clone https://github.com/Ruvini-Rangathara/Vidathya-Web-Exam-Frontend.git
