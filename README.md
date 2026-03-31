# Student Course Registration and Management System
 Group Members
- Peter Gituya Ndono** - CS/MK/0792/09/23 (Team Lead)
- Rebecca Omoro** - CS/M/1387/09/23 (Backend Developer)
- Laura Chebet Langat** - CS/M/1655/09/23 (UI/UX Developer)
## Project Description
This is a comprehensive Spring Boot MVC web application designed to manage student records and course registrations within a university environment. The system provides a modern, responsive user interface using Thymeleaf and Bootstrap 5, implementing a robust layered architecture for scalability and maintainability.

### Project Setup
The application is built using Java 21 with Spring Boot 3.2.4 and follows industry-standard practices for web application development. It uses an in-memory H2 database for data persistence and implements full CRUD operations for all entities.

### Key Features
- **Dashboard**: Real-time statistics including total students, courses, and registrations with visual analytics
- **Student Management**: Full CRUD (Create, Read, Update, Delete) operations for student records with detailed profile views
- **Course Management**: Complete catalog management for university courses with capacity tracking
- **Course Registration**: Seamless enrollment and dropping of courses for students with validation
- **Premium UI**: Responsive design with Bootstrap 5, glassmorphism effects, and intuitive navigation
- **Data Validation**: Robust input validation using Jakarta Validation framework
- **Error Handling**: Global exception handling with user-friendly error messages

### Screenshots
*(Note: Screenshots should be added here showing the dashboard, student management, course listing, and registration interfaces)*

## System Architecture
The application follows the **Spring Boot Layered Architecture** pattern, ensuring separation of concerns and maintainability:

1. **Controller Layer** (`/controller`): Handles HTTP requests, processes user input, and maps responses to appropriate views
   - `MainController`: Dashboard and navigation
   - `StudentController`: Student CRUD operations
   - `CourseController`: Course management operations
   - `RegistrationController`: Course registration logic
   - `GlobalControllerAdvice`: Centralized exception handling

2. **Service Layer** (`/service`): Contains business logic and acts as an intermediary between controllers and repositories
   - Interface-based design with implementation classes
   - Transaction management and business rule enforcement
   - Decoupled from data access layer

3. **Repository Layer** (`/repository`): Manages database access using Spring Data JPA
   - `StudentRepository`, `CourseRepository`, `RegistrationRepository`
   - Automatic CRUD method generation
   - Custom query methods for complex operations

4. **Entity Layer** (`/model`): Represents the database schema
   - `Student`: Student information with validation annotations
   - `Course`: Course details with capacity management
   - `Registration`: Many-to-many relationship between students and courses

5. **View Layer** (`/templates`): Renders the user interface using Thymeleaf templates
   - Modular template structure with reusable fragments
   - Bootstrap 5 integration for responsive design
   - Custom CSS for enhanced aesthetics

## Innovation and Unique Aspects
- **Premium UI/UX Design**: Exceeded basic requirements by implementing glassmorphism effects, smooth transitions, and a modern color scheme
- **Interface-Based Service Layer**: Implemented proper abstraction with service interfaces and implementations for better testability and maintainability
- **Comprehensive Validation**: Multi-layer validation approach using Jakarta Validation annotations and custom business logic
- **Real-time Dashboard**: Dynamic statistics display with visual indicators for system health
- **Responsive Architecture**: Mobile-first design approach ensuring compatibility across all devices
- **Error Resilience**: Global exception handling framework providing consistent error responses

## Challenges Encountered During Development

### Technical Challenges
- **Many-to-Many Relationship Management**: Implementing the student-course registration system required careful handling of bidirectional relationships to avoid infinite loops during JSON serialization
- **Thymeleaf Template Complexity**: Managing complex form submissions and validation error display required deep understanding of Thymeleaf expression language
- **Bootstrap Integration**: Ensuring consistent styling across all templates while maintaining custom CSS enhancements
- **Database Schema Design**: Balancing normalization with performance requirements for the registration system

### Development Process Challenges
- **Team Coordination**: Managing concurrent development on different modules while maintaining code consistency
- **Version Control**: Handling merge conflicts when multiple team members worked on the same templates
- **Testing Strategy**: Implementing comprehensive testing for layered architecture without external dependencies
- **Performance Optimization**: Optimizing database queries for dashboard statistics without N+1 problems

## Lessons Learned

### Technical Lessons
- **Importance of Interface Segregation**: Learned that implementing service interfaces significantly improves code testability and flexibility
- **Validation Best Practices**: Discovered the power of combining framework-level validation with custom business logic validation
- **Template Organization**: Realized the benefits of using Thymeleaf fragments for maintaining consistent UI components
- **Exception Handling**: Understood the value of centralized exception handling for maintaining user experience consistency

### Project Management Lessons
- **Incremental Development**: Breaking down complex features into smaller, manageable tasks improved development velocity
- **Code Review Process**: Regular peer reviews helped maintain code quality and knowledge sharing
- **Documentation**: Comprehensive documentation proved essential for team collaboration and future maintenance
- **Testing Integration**: Learned to write tests concurrently with development rather than as an afterthought

### Personal Growth
- **Spring Boot Ecosystem**: Gained deep understanding of Spring Boot's auto-configuration and dependency injection
- **Modern Frontend Integration**: Learned to effectively integrate modern CSS frameworks with server-side rendering
- **Database Design**: Improved skills in relational database design and JPA entity mapping
- **Team Collaboration**: Enhanced ability to work in a team environment using modern development practices

## Technologies Used
- **Backend**: Java 21, Spring Boot 3.2.4, Spring Data JPA, Spring Validation
- **Persistence**: H2 Database (In-Memory with web console)
- **Frontend**: Thymeleaf, Bootstrap 5, HTML5/CSS3, JavaScript
- **Build Tool**: Maven 3.8+
- **Development Tools**: Spring Boot DevTools, H2 Console

## How to Run
1. Ensure you have **Java 21** and **Maven** installed
2. Navigate to the project root directory: `cd student-management-system`
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at: [http://localhost:8080](http://localhost:8080)
5. To view the database console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console) 
   - JDBC URL: `jdbc:h2:mem:studentdb`
   - Username: `sa`
   - Password: `[empty]`

## Group Members
- **Peter Gituya Ndono** - CS/MK/0792/09/23 (Team Lead)
- **Rebecca Omoro** - CS/M/1387/09/23 (Backend Developer)
- **Laura Chebet Langat** - CS/M/1655/09/23 (UI/UX Developer)

---

*Developed for Advanced Web Development Coursework - Spring 2026*
