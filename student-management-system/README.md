# Student Course Registration and Management System

## Project Overview
This is a Spring Boot MVC web application designed to manage student records and course registrations within a university environment. The system implements a layered architecture and provides a modern, responsive user interface using Thymeleaf and Bootstrap 5.

## Key Features
- **Dashboard**: Real-time statistics including total students, courses, and registrations.
- **Student Management**: Full CRUD (Create, Read, Update, Delete) operations for student records and detailed profile views.
- **Course Management**: Complete catalog management for university courses.
- **Course Registration**: Seamless enrollment and dropping of courses for students.
- **Premium UI**: Responsive design with Bootstrap 5, glassmorphism effects, and intuitive navigation.

## System Architecture
The application follows the **Spring Boot Layered Architecture**:
1. **Controller Layer**: Handles HTTP requests and maps them to appropriate views.
2. **Service Layer**: Contains business logic and acts as an intermediary between controllers and repositories.
3. **Repository Layer**: Manages database access using Spring Data JPA.
4. **Entity Layer**: Represents the database schema (Student, Course, Registration tables).
5. **View Layer**: Renders the user interface using Thymeleaf templates.

## Technologies Used
- **Backend**: Java 21, Spring Boot 3.2.4
- **Persistence**: Spring Data JPA, H2 Database (In-Memory)
- **Frontend**: Thymeleaf, Bootstrap 5, HTML/CSS
- **Build Tool**: Maven

## How to Run
1. Ensure you have **Java 21** and **Maven** installed.
2. Navigate to the project root directory: `cd student-management-system`
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at: [http://localhost:8080](http://localhost:8080)
5. To view the database console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:studentdb`, Username: `sa`, Password: `[empty]`)

## Implementation Details & Innovation
- **Validation**: Implemented Jakarta Validation for robust data entry.
- **UI/UX**: Custom CSS enhancements for a "Premium" aesthetic, exceeding basic project requirements.
- **Modular Services**: Decoupled business logic for high maintainability.

## Group Members
- Peter Gituya Ndono - CS/MK/0792/09/23
- Rebecca Omoro -CS/M/1387/09/23
- Laura Chebet Langat -CS/M/1655/09/23


---
*Developed for Advanced Web Development Coursework.*
